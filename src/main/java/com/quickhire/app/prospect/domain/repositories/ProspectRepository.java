package com.quickhire.app.prospect.domain.repositories;

import com.quickhire.app.prospect.domain.Prospect;
import com.quickhire.app.prospect.domain.ProspectId;

public interface ProspectRepository {

  void save(Prospect prospect);

  Prospect findById(ProspectId prospectId);
}
