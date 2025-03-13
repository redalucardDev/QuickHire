package com.quickhire.app.prospect.domain;

import com.quickhire.app.shared.error.domain.Assert;

public record ContactInfo(FirstName firstName, LastName lastName, Email email, PhoneNumber phoneNumber) {

  public ContactInfo {
    if (email.email().isBlank() && phoneNumber.phoneNumber().isBlank()) {
      throw new IllegalArgumentException("Email and phone number must not both be blank");
    }
  }


  public record FirstName(String firstName) {

    public FirstName {
      Assert.notBlank("firstName", firstName);
    }
  }

  public record LastName(String lastName) {

    public LastName {
      Assert.notBlank("lastName", lastName);

    }
  }

  public record PhoneNumber(String phoneNumber) {
  }

  public record Email(String email) {
    public Email {
      if (!email.matches("([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})") && !email.isBlank()) {
        throw new IllegalArgumentException("Invalid email");
      }
    }
  }
}
