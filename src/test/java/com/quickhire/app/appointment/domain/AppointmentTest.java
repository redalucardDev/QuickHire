package com.quickhire.app.appointment.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class AppointmentTest {

  @Test
  void shouldGetAppointment() {
    Appointment.AppointmentId appointmentId = new Appointment.AppointmentId(UUID.randomUUID());
    Appointment.CandidatureId candidatureId = new Appointment.CandidatureId(UUID.randomUUID());
    AppointmentStatus appointmentStatus = AppointmentStatus.WAITING;
    AppointmentDate appointmentDate = new AppointmentDate(LocalDateTime.now().plusDays(3));

    AppointmentDuration appointmentDuration = new AppointmentDuration(
      AppointmentDuration.AppointmentHours.ZERO,
      AppointmentDuration.AppointmentMinutes.THIRTY
    );

    Appointment appointment = new Appointment(appointmentId, candidatureId, appointmentDate, appointmentDuration, appointmentStatus);

    assertThat(appointment).isEqualTo(
      new Appointment(appointmentId, candidatureId, appointmentDate, appointmentDuration, appointmentStatus)
    );
  }
}
