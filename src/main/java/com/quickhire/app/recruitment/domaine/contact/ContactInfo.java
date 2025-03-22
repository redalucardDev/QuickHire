package com.quickhire.app.recruitment.domaine.contact;

public record ContactInfo(Email email, PhoneNumber phoneNumber) {
  public ContactInfo {
    if (email.value().isBlank() && phoneNumber.value().isBlank()) {
      throw new IllegalArgumentException("Email and phone number must not both be blank");
    }
  }
}
