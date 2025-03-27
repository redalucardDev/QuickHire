package com.quickhire.app.recruitment.domaine;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.quickhire.app.recruitment.domaine.application.Application;
import com.quickhire.app.recruitment.domaine.application.Offer;
import com.quickhire.app.recruitment.domaine.events.OfferSendEvent;
import com.quickhire.app.recruitment.domaine.interview.InterviewDuration;
import com.quickhire.app.recruitment.domaine.interview.Interviews;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

  private final DeterministicDateTimeProvider deterministicDateTimePovider = new DeterministicDateTimeProvider(
    LocalDateTime.of(2025, 3, 25, 0, 0)
  );
  private final Application application = RecruitmentFixture.createApplicaion();

  @Test
  void shouldScheduleFirstAppointmentForInterview() {
    Interviews interviews = application.scheduleInterview(deterministicDateTimePovider.dateTime());
    assertThat(interviews.size()).isEqualTo(1);
    assertThat(interviews.values().getFirst().interviewDate().value()).isEqualTo(deterministicDateTimePovider.dateTime());
  }

  @Test
  void shouldNotScheduleInterviewIfApplicationIsNotPending() {
    application.status(ApplicationStatus.ACCEPTED);
    assertThatExceptionOfType(IllegalStateException.class)
      .isThrownBy(() -> application.scheduleInterview(deterministicDateTimePovider.dateTime()))
      .withMessage("Application is not pending");
  }

  @Test
  void shoulNotScheduleMoreThan2Interviews() {
    Interviews interviews = application
      .scheduleInterview(deterministicDateTimePovider.dateTime())
      .schedule(deterministicDateTimePovider.dateTime().plusDays(1));
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> interviews.schedule(deterministicDateTimePovider.dateTime()))
      .withMessage("Maximum number of interviews is 2");
  }

  @Test
  void shouldNotNotSchedule2InterviewsWhenDatesAreOverlapping() {
    Interviews interviews = application
      .scheduleInterview(deterministicDateTimePovider.dateTime())
      .schedule(deterministicDateTimePovider.dateTime().plusDays(1));

    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> interviews.schedule(deterministicDateTimePovider.dateTime()))
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
    Interviews interviews = application.scheduleInterview(firstInterviewStartDateTime, InterviewDuration.ONE_HOUR);

    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> interviews.schedule(secondInterviewStartDateTime))
      .withMessage("Cant schedule a second interview because of overlapping with the first interview");
  }

  @Test
  void shouldRejectApplication() {
    assertThat(application.reject().status()).isEqualTo(ApplicationStatus.DECLINED);
  }

  @Test
  void shouldNotAcceptApplicationIfInterviewsAreNotScheduled() {
    assertThatExceptionOfType(IllegalStateException.class)
      .isThrownBy(() -> application.accept(new Message("This is a new offer")))
      .withMessage("2 interviews must be scheduled for this application to be accepted");
  }

  @Test
  void shouldEmmitEventWhenApplicationIsAccepted() {
    application.scheduleInterview(deterministicDateTimePovider.dateTime());
    application.scheduleInterview(deterministicDateTimePovider.dateTime().plusDays(1));

    Offer offer = application.accept(new Message("This is a new offer"));
    assertThat(application.offerSentEvent()).isEqualTo(new OfferSendEvent(offer.offerId(), offer.offerMessage(), offer.jobId()));
  }
}
