package com.quickhire.app.prospect.domain;

record Email(String email) {
  public Email {
    if (!email.matches("([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})") && !email.isBlank()) {
      throw new IllegalArgumentException("Invalid email");
    }
  }
}

