package com.quickhire.app.prospect.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProspectTest {

  @Test
  void shouldGetProspect() {

    Prospect prospect = createProspect("john.doe@gmail.com", "+33662887766");

    assertThat(prospect.contactInfo()).isEqualTo(
      new ContactInfo(
        new FirstName("John"),
        new LastName("Doe"),
        new Email("john.doe@gmail.com"),
        new PhoneNumber("+33662887766")
      ));
  }


  @Test
  void shouldNotGetProspectWithInvalidEmail() {

    assertThrows(IllegalArgumentException.class,
      () ->
        createProspect("john.doegmail.com", "+33662887766"));
  }

  @Test
  void shouldNotGetProspectWhenEitherEmailAndPhoneNumberAreBlank() {
    assertThrows(IllegalArgumentException.class,
      () ->
        createProspect("", ""));
  }


  private static Prospect createProspect(String emailString, String phoneNumber) {

    return new Prospect(
      new ProspectId(UUID.randomUUID()),
      new ContactInfo(
      new FirstName("John"),
      new LastName("Doe"),
      new Email(emailString),
      new PhoneNumber(phoneNumber)));

  }


}
