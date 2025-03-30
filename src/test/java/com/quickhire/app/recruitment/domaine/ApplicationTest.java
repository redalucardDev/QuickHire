package com.quickhire.app.recruitment.domaine;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.quickhire.app.recruitment.domaine.application.DeclinedApplication;
import com.quickhire.app.recruitment.domaine.application.PendingApplication;
import com.quickhire.app.recruitment.domaine.interview.InterviewDuration;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

  private final DeterministicDateTimeProvider deterministicDateTimePovider = new DeterministicDateTimeProvider(
    LocalDateTime.of(2025, 3, 25, 0, 0)
  );
  private final PendingApplication pendingApplication = RecruitmentFixture.createApplicaion();

  @Test
  void shouldScheduleFirstAppointmentForInterview() {
    PendingApplication pendingApplicationWithScheduledInterview = pendingApplication.scheduleInterview(
      deterministicDateTimePovider.dateTime()
    );
    assertThat(pendingApplicationWithScheduledInterview.interviews().size()).isEqualTo(1);
    assertThat(pendingApplicationWithScheduledInterview.interviews().values().getFirst().interviewDate().value()).isEqualTo(
      deterministicDateTimePovider.dateTime()
    );
  }

  @Test
  void shoulNotScheduleMoreThan2Interviews() {
    PendingApplication pendingApplicationWithScheduledInterviews = pendingApplication
      .scheduleInterview(deterministicDateTimePovider.dateTime())
      .scheduleInterview(deterministicDateTimePovider.dateTime().plusDays(1));
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> pendingApplicationWithScheduledInterviews.scheduleInterview(deterministicDateTimePovider.dateTime()))
      .withMessage("Maximum number of interviews is 2");
  }

  @Test
  void shouldNotNotSchedule2InterviewsWhenDatesAreOverlapping() {
    PendingApplication pendingApplicationWithScheduledInterviews = pendingApplication
      .scheduleInterview(deterministicDateTimePovider.dateTime())
      .scheduleInterview(deterministicDateTimePovider.dateTime().plusDays(1));

    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> pendingApplicationWithScheduledInterviews.scheduleInterview(deterministicDateTimePovider.dateTime()))
      .withMessage("Maximum number of interviews is 2");
  }

  @Test
  void shouldAddInterviewDuration() {
    InterviewDuration interviewDuration = InterviewDuration.ONE_HOUR_THIRTY_MINUTE;
    InterviewDuration newInterviewDuration = interviewDuration.add(InterviewDuration.THIRTY_MINUTE);
    assertThat(newInterviewDuration).isEqualTo(InterviewDuration.TWO_HOUR);
  }

  @Test
  void acceptApplication() {
    LocalDateTime firstInterviewStartDateTime = deterministicDateTimePovider.dateTime(LocalDateTime.of(2025, 3, 25, 13, 0)).dateTime();
    LocalDateTime secondInterviewStartDateTime = firstInterviewStartDateTime.plusMinutes(30);
    PendingApplication pendingApplicationWithScheduledInterview = pendingApplication.scheduleInterview(
      firstInterviewStartDateTime,
      InterviewDuration.ONE_HOUR
    );

    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> pendingApplicationWithScheduledInterview.scheduleInterview(secondInterviewStartDateTime))
      .withMessage("Cant schedule a second interview because of overlapping with the first interview");
  }

  @Test
  void shouldDeclineApplication() {
    assertThat(pendingApplication.decline(new Message("This is a new decline message"))).isInstanceOf(DeclinedApplication.class);
  }

  @Test
  void shouldNotAcceptApplicationIfInterviewsAreNotScheduled() {
    assertThatExceptionOfType(IllegalStateException.class)
      .isThrownBy(() -> pendingApplication.accept(new Message("This is a new offer")))
      .withMessage("2 interviews must be scheduled for this application to be accepted");
  }
}
