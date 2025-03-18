package com.quickhire.app.prospection.domain.repositories;

import com.quickhire.app.prospection.domain.Prospection;
import com.quickhire.app.prospection.domain.ProspectionId;
import java.util.Optional;

public interface ProspectionRepository {
  void save(Prospection prospection);
  Optional<Prospection> findById(ProspectionId prospectionId);
}
