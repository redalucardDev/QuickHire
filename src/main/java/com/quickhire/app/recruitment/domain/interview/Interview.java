package com.quickhire.app.recruitment.domain.interview;

import com.quickhire.app.recruitment.domain.DateTime;
import com.quickhire.app.recruitment.domain.application.Report;
import com.quickhire.app.shared.error.domain.Assert;

public class Interview {

  private final InterviewId interviewId;
  private final InterviewDuration duration;
  private Report report;
  private final DateTime interviewDateTime;

  public Interview(InterviewId interviewId, DateTime interviewDateTime, InterviewDuration duration) {
    Assert.notNull("interviewId", interviewId);
    this.interviewId = interviewId;
    this.interviewDateTime = interviewDateTime;
    this.duration = duration;
  }

  public InterviewId interviewId() {
    return interviewId;
  }

  public DateTime interviewDate() {
    return interviewDateTime;
  }

  public void report(Report report) {
    this.report = report;
  }

  public Report report() {
    return report;
  }
}
