package com.quickhire.app.prospection.application;

import com.quickhire.app.prospection.domain.Prospection;
import com.quickhire.app.prospection.domain.repositories.ProspectionRepository;

public class ProspectionApplicationService {

  private final ProspectionRepository prospectionRepository;

  public ProspectionApplicationService(ProspectionRepository prospectionRepository) {
    this.prospectionRepository = prospectionRepository;
  }

  public Prospection create(Prospection prospection) {
    prospectionRepository.save(prospection);
    return prospection;
  }
}
