package com.quickhire.app.recruitment.domaine.interview;

import com.quickhire.app.recruitment.domaine.DateTime;
import com.quickhire.app.shared.error.domain.Assert;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Interviews(List<Interview> values) {
  public Interviews {
    Assert.notNull("interviews", values);
    if (values.size() > 2) {
      throw new IllegalArgumentException("Maximum number of interviews is 2");
    }
    values = Collections.unmodifiableList(new ArrayList<>(values));
  }

  public Interviews schedule(LocalDateTime localDateTime, InterviewDuration duration) {
    if (areAllInterviewsScheduled()) {
      throw new IllegalArgumentException("Maximum number of interviews is 2");
    }
    if (isFirstInterviewScheduled()) {
      Interview firstInterview = values.getFirst();
      if (isNewInterviewScheduledBeforeOldOne(localDateTime, firstInterview)) {
        throw new IllegalArgumentException("Cant schedule a second interview, it must be scheduled after the first one");
      }
      if (isNewInterviewOverlappingWithOldOne(localDateTime, duration, firstInterview)) {
        throw new IllegalArgumentException("Cant schedule a second interview because of overlapping with the first interview");
      }
    }
    return add(new Interview(InterviewId.newId(), new DateTime(localDateTime), duration));
  }

  private boolean isNewInterviewOverlappingWithOldOne(LocalDateTime localDateTime, InterviewDuration duration, Interview firstInterview) {
    return localDateTime.isBefore(firstInterview.interviewDate().value().plus(duration.duration()));
  }

  private boolean isNewInterviewScheduledBeforeOldOne(LocalDateTime localDateTime, Interview firstInterview) {
    return localDateTime.isBefore(firstInterview.interviewDate().value());
  }

  private boolean areAllInterviewsScheduled() {
    return values.size() == 2;
  }

  private boolean isFirstInterviewScheduled() {
    return values.size() == 1;
  }

  public int size() {
    return values.size();
  }

  public Interviews add(Interview interview) {
    List<Interview> newValues = new ArrayList<>(values);
    newValues.add(interview);
    return new Interviews(newValues);
  }
}
