package com.quickhire.app.prospect.application;

import com.quickhire.app.prospect.domain.Prospect;
import com.quickhire.app.prospect.domain.repositories.ProspectRepository;

public class ProspectApplicationService {

  private final ProspectRepository prospectRepository;
  public ProspectApplicationService(ProspectRepository prospectRepository) {
      this.prospectRepository = prospectRepository;
  }

  public Prospect create(Prospect prospect) {
      prospectRepository.save(prospect);
      return prospect;
  }
}
