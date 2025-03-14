package com.quickhire.app.prospection.domain.repositories;

import com.quickhire.app.prospection.domain.Prospection;
import com.quickhire.app.prospection.domain.ProspectionId;

public interface ProspectionRepository {

  void save(Prospection prospection);
  Prospection findById(ProspectionId prospectionId);


}
