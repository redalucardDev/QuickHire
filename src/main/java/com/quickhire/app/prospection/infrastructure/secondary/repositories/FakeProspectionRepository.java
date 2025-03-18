package com.quickhire.app.prospection.infrastructure.secondary.repositories;

import com.quickhire.app.prospection.domain.Prospection;
import com.quickhire.app.prospection.domain.ProspectionId;
import com.quickhire.app.prospection.domain.repositories.ProspectionRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeProspectionRepository implements ProspectionRepository {

  private final Map<ProspectionId, Optional<Prospection>> prospections = new HashMap<>();

  @Override
  public void save(Prospection prospection) {
    prospections.put(prospection.prospectionId(), Optional.of(prospection));
  }

  @Override
  public Optional<Prospection> findById(ProspectionId prospectionId) {
    return prospections.getOrDefault(prospectionId, Optional.empty());
  }
}
