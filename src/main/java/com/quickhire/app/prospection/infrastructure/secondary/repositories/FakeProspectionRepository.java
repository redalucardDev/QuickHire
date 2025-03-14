package com.quickhire.app.prospection.infrastructure.secondary.repositories;

import com.quickhire.app.prospection.domain.Prospection;
import com.quickhire.app.prospection.domain.ProspectionId;
import com.quickhire.app.prospection.domain.repositories.ProspectionRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeProspectionRepository implements ProspectionRepository {

  private final Map<ProspectionId, Prospection> prospections = new HashMap<>();

  @Override
  public void save(Prospection prospection) {
    prospections.put(prospection.prospectionId(), prospection);
  }

  @Override
  public Prospection findById(ProspectionId prospectionId) {
    return prospections.get(prospectionId);
  }


}
