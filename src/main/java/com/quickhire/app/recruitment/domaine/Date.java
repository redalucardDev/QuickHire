package com.quickhire.app.recruitment.domaine;

import com.quickhire.app.shared.error.domain.Assert;
import java.time.LocalDateTime;
import java.time.ZoneId;

public record Date(LocalDateTime value) {
  public Date {
    Assert.notNull("value", value);
    if (!value.isBefore(LocalDateTime.now())) {
      throw new IllegalArgumentException("Proposition value must be in the past");
    }
  }

  boolean isMoreThanOneDayApart(LocalDateTime dateTime) {
    long dateTimeInMillis = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    long valueInMillis = value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    return value.isBefore(dateTime) && dateTimeInMillis - valueInMillis > 60 * 60 * 1000;
  }
}
