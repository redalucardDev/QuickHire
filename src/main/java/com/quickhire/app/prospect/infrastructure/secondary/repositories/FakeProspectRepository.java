package com.quickhire.app.prospect.infrastructure.secondary.repositories;

import com.quickhire.app.prospect.domain.Prospect;
import com.quickhire.app.prospect.domain.ProspectId;
import com.quickhire.app.prospect.domain.repositories.ProspectRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeProspectRepository implements ProspectRepository {

  private final Map<ProspectId, Prospect> prospects = new HashMap<>();

  @Override
  public void save(Prospect prospect) {
    prospects.put(prospect.prospectId(), prospect);
  }

  @Override
  public Prospect findById(ProspectId prospectId) {
    return prospects.get(prospectId);
  }
}
