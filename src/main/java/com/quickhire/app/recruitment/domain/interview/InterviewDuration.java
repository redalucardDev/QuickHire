package com.quickhire.app.recruitment.domain.interview;

import com.quickhire.app.shared.error.domain.Assert;
import java.time.Duration;
import java.util.Arrays;

public enum InterviewDuration {
  THIRTY_MINUTE(Duration.ofMinutes(30)),
  ONE_HOUR(Duration.ofHours(1)),
  TWO_HOUR(Duration.ofHours(2)),
  ONE_HOUR_THIRTY_MINUTE(Duration.ofHours(1).plus(Duration.ofMinutes(30)));

  private final Duration duration;

  InterviewDuration(Duration duration) {
    Assert.notNull("duration", duration);
    this.duration = duration;
  }

  public Duration duration() {
    return duration;
  }

  public InterviewDuration add(InterviewDuration interviewDuration) {
    Duration totalDuration = this.duration().plus(interviewDuration.duration());
    return Arrays.stream(values())
      .filter(value -> value.duration().equals(totalDuration))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("Cant add this duration to this interview duration"));
  }
}
