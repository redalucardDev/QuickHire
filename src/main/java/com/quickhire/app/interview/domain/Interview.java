package com.quickhire.app.interview.domain;

import com.quickhire.app.shared.error.domain.Assert;
import java.util.UUID;

public record Interview(InterviewId interviewId, AppointmentId appointmentId, Report report) {
  public Interview {
    Assert.notNull("interviewId", interviewId);
    Assert.notNull("appointmentId", appointmentId);
  }

  public record InterviewId(UUID interviewId) {
    public InterviewId {
      Assert.notNull("interviewId", interviewId);
    }
  }

  public record AppointmentId(UUID appointmentId) {
    public AppointmentId {
      Assert.notNull("appointmentId", appointmentId);
    }
  }
}
