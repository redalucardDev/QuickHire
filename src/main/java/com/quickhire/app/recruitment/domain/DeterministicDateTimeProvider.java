package com.quickhire.app.recruitment.domain;

import java.time.LocalDateTime;

public class DeterministicDateTimeProvider implements DateTimeProvider {

  LocalDateTime currentDateTime;

  public DeterministicDateTimeProvider(LocalDateTime currentDateTime) {
    this.currentDateTime = currentDateTime;
  }

  @Override
  public LocalDateTime dateTime() {
    return switch (currentDateTime) {
      case null -> throw new IllegalStateException("Current date is not set");
      default -> currentDateTime;
    };
  }

  public DeterministicDateTimeProvider dateTime(LocalDateTime dateTime) {
    this.currentDateTime = dateTime;
    return this;
  }
}
