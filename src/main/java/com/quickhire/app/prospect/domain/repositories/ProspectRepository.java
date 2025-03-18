package com.quickhire.app.prospect.domain.repositories;

import com.quickhire.app.prospect.domain.Prospect;
import java.util.Optional;

public interface ProspectRepository {
  void save(Prospect prospect);

  Optional<Prospect> findById(Prospect.ProspectId prospectId);
}
