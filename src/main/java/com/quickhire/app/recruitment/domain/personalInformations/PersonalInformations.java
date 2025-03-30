package com.quickhire.app.recruitment.domain.personalInformations;

import com.quickhire.app.shared.error.domain.Assert;

public record PersonalInformations(FirstName firstName, LastName lastName, ContactInfo contactInfo) {
  public PersonalInformations {
    Assert.notNull("firstName", firstName);
    Assert.notNull("lastName", lastName);
    Assert.notNull("contactInfo", contactInfo);
  }
}
