package com.quickhire.app.prospection.infrastructure.secondary.repositories.jpa;

import com.quickhire.app.prospection.domain.Prospection;
import com.quickhire.app.prospection.domain.ProspectionId;
import com.quickhire.app.prospection.domain.repositories.ProspectionRepository;
import jdk.jfr.Registered;

import java.util.Optional;

@Registered
public class JpaProspectionRepository implements ProspectionRepository {

  @Override
  public void save(Prospection prospection) {

  }

  @Override
  public Optional<Prospection> findById(ProspectionId prospectionId) {
    return Optional.empty();
  }
}
