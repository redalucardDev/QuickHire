package com.quickhire.app.prospect.application;

import static com.quickhire.app.prospect.providers.ProspectProvider.createProspect;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.quickhire.app.prospect.domain.Prospect;
import com.quickhire.app.prospect.domain.repositories.ProspectRepository;
import com.quickhire.app.prospect.infrastructure.secondary.repositories.FakeProspectRepository;
import com.quickhire.app.shared.contactInfo.domain.ContactInfo;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

public class ProspectServiceTest {

  ProspectRepository prospectRepository = new FakeProspectRepository();
  ProspectApplicationService prospectApplicationService = new ProspectApplicationService(prospectRepository);

  @Test
  void shouldGetProspect() {
    Prospect prospect = createProspect("john.doe@gmail.com", "+33662887766");

    Prospect expectedProspect = new Prospect(
      prospect.prospectId(),
      new ContactInfo(
        new ContactInfo.FirstName("John"),
        new ContactInfo.LastName("Doe"),
        new ContactInfo.Email("john.doe@gmail.com"),
        new ContactInfo.PhoneNumber("+33662887766")
      )
    );

    assertThat(prospectApplicationService.create(prospect)).isEqualTo(expectedProspect);
    assertThat(prospectApplicationService.getProspectBy(prospect.prospectId())).isEqualTo(expectedProspect);
  }

  @Test
  void shouldNotGetProspectWhenHeDoesNotExist() {
    Prospect prospect = createProspect("john.doe@gmail.com", "+33662887766");

    assertThrows(NoSuchElementException.class, () -> prospectApplicationService.getProspectBy(prospect.prospectId()));
  }
}
