package com.quickhire.app.appointment.domain;

import com.quickhire.app.shared.error.domain.Assert;
import java.util.UUID;

public record Appointment(
  AppointmentId appointmentId,
  CandidatureId candidatureId,
  AppointmentDate appointmentDate,
  AppointmentDuration appointmentDuration,
  AppointmentStatus appointmentStatus
) {
  public Appointment {
    Assert.notNull("appointmentDate", appointmentDate);
    Assert.notNull("appointmentStatus", appointmentStatus);
  }

  public record CandidatureId(UUID candidatureId) {
    public CandidatureId {
      Assert.notNull("candidatureId", candidatureId);
    }
  }

  public record AppointmentId(UUID appointmentId) {
    public AppointmentId {
      Assert.notNull("appointmentId", appointmentId);
    }
  }
}
