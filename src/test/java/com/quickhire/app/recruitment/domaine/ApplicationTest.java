package com.quickhire.app.recruitment.domaine;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import com.quickhire.app.recruitment.domaine.application.Application;
import com.quickhire.app.recruitment.domaine.application.Interviews;
import com.quickhire.app.recruitment.domaine.application.Offer;
import com.quickhire.app.recruitment.domaine.events.OfferSendEvent;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

  private final DeterministicDateTimeProvider deterministicDateTimePovider = new DeterministicDateTimeProvider(
    LocalDateTime.of(2025, 03, 25, 0, 0)
  );

  @Test
  void shouldScheduleFirstAppointmentForInterview() {
    Application application = RecruitmentFixture.createApplicaion();
    Interviews interviews = application.scheduleInterview(deterministicDateTimePovider.dateTime());
    assertThat(interviews.size()).isEqualTo(1);
    assertThat(interviews.values().stream().findFirst().get().interviewDate().value()).isEqualTo(deterministicDateTimePovider.dateTime());
  }

  @Test
  void shouldNotScheduleInterviewIfApplicationIsNotPending() {
    Application application = RecruitmentFixture.createApplicaion();
    application.status(ApplicationStatus.ACCEPTED);
    assertThatExceptionOfType(IllegalStateException.class)
      .isThrownBy(() -> application.scheduleInterview(deterministicDateTimePovider.dateTime()))
      .withMessage("Application is not pending");
  }

  @Test
  void shoulNotScheduleMoreThan2Interviews() {
    Application application = RecruitmentFixture.createApplicaion();
    Interviews interviews = application
      .scheduleInterview(deterministicDateTimePovider.dateTime())
      .schedule(deterministicDateTimePovider.dateTime().plusDays(1));
    assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> interviews.schedule(deterministicDateTimePovider.dateTime()))
      .withMessage("Maximum number of interviews is 2");
  }

  @Test
  void acceptApplication() {
    Application application = RecruitmentFixture.createApplicaion();
    application.scheduleInterview(deterministicDateTimePovider.dateTime());
    application.scheduleInterview(deterministicDateTimePovider.dateTime().plusDays(1));
    application.accept(new Message("This is a new offer"));
    assertThat(application.status()).isEqualTo(ApplicationStatus.ACCEPTED);
  }

  @Test
  void shouldRejectApplication() {
    Application application = RecruitmentFixture.createApplicaion();
    assertThat(application.reject().status()).isEqualTo(ApplicationStatus.DECLINED);
  }

  @Test
  void shouldNotAcceptApplicationIfInterviewsAreNotScheduled() {
    Application application = RecruitmentFixture.createApplicaion();
    assertThatExceptionOfType(IllegalStateException.class)
      .isThrownBy(() -> application.accept(new Message("This is a new offer")))
      .withMessage("2 interviews must be scheduled for this application to be accepted");
  }

  @Test
  void shouldEmmitEventWhenApplicationIsAccepted() {
    Application application = RecruitmentFixture.createApplicaion();
    application.scheduleInterview(deterministicDateTimePovider.dateTime());
    application.scheduleInterview(deterministicDateTimePovider.dateTime().plusDays(1));

    Offer offer = application.accept(new Message("This is a new offer"));
    assertThat(application.offerSentEvent()).isEqualTo(new OfferSendEvent(offer.offerId(), offer.offerMessage(), offer.jobId()));
  }
}
