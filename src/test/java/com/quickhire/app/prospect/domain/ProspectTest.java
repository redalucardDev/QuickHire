package com.quickhire.app.prospect.domain;

import static com.quickhire.app.prospect.providers.ProspectProvider.createProspect;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.quickhire.app.shared.contactInfo.domain.ContactInfo;
import org.junit.jupiter.api.Test;

class ProspectTest {

  @Test
  void shouldGetProspect() {
    Prospect prospect = createProspect("john.doe@gmail.com", "+33662887766");

    assertThat(prospect.contactInfo()).isEqualTo(
      new ContactInfo(
        new ContactInfo.FirstName("John"),
        new ContactInfo.LastName("Doe"),
        new ContactInfo.Email("john.doe@gmail.com"),
        new ContactInfo.PhoneNumber("+33662887766")
      )
    );
  }

  @Test
  void shouldNotGetProspectWithInvalidEmail() {
    assertThrows(IllegalArgumentException.class, () -> createProspect("john.doegmail.com", "+33662887766"));
  }

  @Test
  void shouldNotGetProspectWhenEitherEmailAndPhoneNumberAreBlank() {
    assertThrows(IllegalArgumentException.class, () -> createProspect("", ""));
  }
}
