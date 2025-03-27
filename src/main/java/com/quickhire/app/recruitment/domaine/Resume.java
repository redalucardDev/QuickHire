package com.quickhire.app.recruitment.domaine;

import com.quickhire.app.shared.error.domain.Assert;

public record Resume(ResumeId resumeId, String resume) {
  public Resume {
    Assert.notNull("resumeId", resumeId);
    Assert.notBlank("resume", resume);
  }
}
