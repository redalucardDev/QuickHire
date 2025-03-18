package com.quickhire.app.prospect.application;

import com.quickhire.app.prospect.domain.Prospect;
import com.quickhire.app.prospect.domain.repositories.ProspectRepository;

import java.util.NoSuchElementException;

public class ProspectApplicationService {

  private final ProspectRepository prospectRepository;
  public ProspectApplicationService(ProspectRepository prospectRepository) {
      this.prospectRepository = prospectRepository;
  }

  public Prospect create(Prospect prospect) {
      prospectRepository.save(prospect);
      return prospect;
  }

  public Prospect getProspectBy(Prospect.ProspectId prospectId) {
    return prospectRepository.findById(prospectId).orElseThrow(() -> new NoSuchElementException("Prospect not found"));
  }
}
