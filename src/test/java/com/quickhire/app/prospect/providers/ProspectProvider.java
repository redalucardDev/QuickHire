package com.quickhire.app.prospect.providers;

import com.quickhire.app.prospect.domain.*;
import com.quickhire.app.prospect.domain.ContactInfo.*;

import java.util.UUID;

public class ProspectProvider {

  public static Prospect createProspect(String emailString, String phoneNumber) {

    return new Prospect(
      new ProspectId(UUID.randomUUID()),
      new ContactInfo(
        new FirstName("John"),
        new LastName("Doe"),
        new Email(emailString),
        new PhoneNumber(phoneNumber)));

  }
}
