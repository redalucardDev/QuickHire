package com.quickhire.app.recruitment.domaine.application;

import com.quickhire.app.recruitment.domaine.Date;
import com.quickhire.app.shared.error.domain.Assert;

public class Interview {

  private final InterviewId interviewId;
  private Report report;
  private final Date interviewDate;

  public Interview(InterviewId interviewId, Date interviewDate) {
    Assert.notNull("interviewId", interviewId);
    this.interviewId = interviewId;
    this.interviewDate = interviewDate;
  }

  public InterviewId interviewId() {
    return interviewId;
  }

  public Date interviewDate() {
    return interviewDate;
  }

  public void report(Report report) {
    this.report = report;
  }

  public Report report() {
    return report;
  }
}
