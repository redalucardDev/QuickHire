package com.quickhire.app.prospect.domain;

record ContactInfo(FirstName firstName, LastName lastName, Email email, PhoneNumber phoneNumber) {

  public ContactInfo {
    if (email.email().isBlank() && phoneNumber.phoneNumber().isBlank()) {
      throw new IllegalArgumentException("Email and phone number must not both be blank");
    }
  }

}
