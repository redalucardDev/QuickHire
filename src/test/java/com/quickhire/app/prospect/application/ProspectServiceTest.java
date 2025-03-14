package com.quickhire.app.prospect.application;

import com.quickhire.app.prospect.domain.ContactInfo;
import com.quickhire.app.prospect.domain.Prospect;
import com.quickhire.app.prospect.domain.repositories.ProspectRepository;
import com.quickhire.app.prospect.infrastructure.secondary.repositories.FakeProspectRepository;
import org.junit.jupiter.api.Test;

import static com.quickhire.app.prospect.providers.ProspectProvider.createProspect;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProspectServiceTest {

  ProspectRepository prospectRepository = new FakeProspectRepository();
  ProspectApplicationService prospectApplicationService = new ProspectApplicationService(prospectRepository);

  @Test
  void shouldGetProspect() {

    Prospect prospect = createProspect("john.doe@gmail.com", "+33662887766");
    prospectApplicationService.create(prospect);

    Prospect expectedProspect = new Prospect(prospect.prospectId(), new ContactInfo(new ContactInfo.FirstName("John"), new ContactInfo.LastName("Doe"), new ContactInfo.Email("john.doe@gmail.com"), new ContactInfo.PhoneNumber("+33662887766")));

    assertThat(prospectApplicationService.create(prospect)).isEqualTo(expectedProspect);
    assertThat(prospectRepository.findById(prospect.prospectId())).isEqualTo(new Prospect(prospect.prospectId(), new ContactInfo(new ContactInfo.FirstName("John"), new ContactInfo.LastName("Doe"), new ContactInfo.Email("john.doe@gmail.com"), new ContactInfo.PhoneNumber("+33662887766"))));


  }

}
