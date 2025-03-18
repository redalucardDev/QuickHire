package com.quickhire.app.appointment.domain;

public record AppointmentDuration(AppointmentHours hours, AppointmentMinutes minutes) {
  public AppointmentDuration {
    if (hours == AppointmentHours.ZERO && minutes == AppointmentMinutes.ZERO) {
      throw new IllegalArgumentException("The minimum appointment duration is 30 minutes");
    }
  }

  enum AppointmentHours {
    ZERO,
    ONE,
    TWO,
  }

  enum AppointmentMinutes {
    ZERO,
    THIRTY,
  }
}
