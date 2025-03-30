package com.quickhire.app.recruitment.domain;

import com.quickhire.app.shared.error.domain.Assert;
import java.time.LocalDateTime;
import java.time.ZoneId;

public record DateTime(LocalDateTime value) {
  public DateTime {
    Assert.notNull("value", value);
  }

  boolean isMoreThanOneDayApart(LocalDateTime dateTime) {
    long dateTimeInMillis = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    long valueInMillis = value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    return value.isBefore(dateTime) && dateTimeInMillis - valueInMillis > 60 * 60 * 1000;
  }

  public static DateTime now() {
    return new DateTime(LocalDateTime.now());
  }
}
