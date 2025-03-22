package com.quickhire.app.recruitment.domaine.application;

import com.quickhire.app.recruitment.domaine.Date;
import com.quickhire.app.shared.error.domain.Assert;
import java.time.LocalDateTime;
import java.util.List;

public record Interviews(List<Interview> values) {
  public Interviews {
    Assert.notNull("interviews", values);
    if (values.size() > 2) {
      throw new IllegalArgumentException("Maximum number of interviews is 2");
    }
  }

  public Interviews schedule(LocalDateTime localDateTime) {
    if (values.size() == 2) {
      throw new IllegalArgumentException("Maximum number of interviews is 2");
    }
    values.add(new Interview(InterviewId.newId(), new Date(localDateTime)));
    return this;
  }

  public int size() {
    return values.size();
  }
}
