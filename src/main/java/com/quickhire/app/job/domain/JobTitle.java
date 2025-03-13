package com.quickhire.app.job.domain;

record JobTitle(String title) {

  JobTitle {
    if (title.isBlank()) {
      throw new IllegalArgumentException("Job title must not be blank");
    }
  }

}
