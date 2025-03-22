package com.quickhire.app.recruitment.domaine;

import java.time.LocalDateTime;

public class DeterministicDateTimeProvider implements DateTimeProvider {

  LocalDateTime currentDateTime;

  public DeterministicDateTimeProvider(LocalDateTime currentDateTime) {
    this.currentDateTime = currentDateTime;
  }

  @Override
  public LocalDateTime now() {
    return switch (currentDateTime) {
      case null -> throw new IllegalStateException("Current date is not set");
      default -> currentDateTime;
    };
  }
}
