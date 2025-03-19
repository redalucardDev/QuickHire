package com.quickhire.app.interview.domain;

import java.util.UUID;
import org.junit.jupiter.api.Test;

public class InterviewTest {

  @Test
  void shouldGetInterview() {
    Report report = new Report("");
    Interview interview = new Interview(
      new Interview.InterviewId(UUID.randomUUID()),
      new Interview.AppointmentId(UUID.randomUUID()),
      report
    );
  }
}
