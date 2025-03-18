package com.quickhire.app.appointment.domain;

import com.quickhire.app.shared.error.domain.Assert;
import java.time.LocalDateTime;

public record AppointmentDate(LocalDateTime appointmentDate) {
  public AppointmentDate {
    Assert.notNull("appointmentDate", appointmentDate);
    if (appointmentDate.isBefore(LocalDateTime.now())) {
      throw new IllegalArgumentException("Appointment date must be in the future");
    }
  }
}
