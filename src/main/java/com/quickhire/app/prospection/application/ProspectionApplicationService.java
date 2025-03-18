package com.quickhire.app.prospection.application;

import com.quickhire.app.prospect.domain.Prospect;
import com.quickhire.app.prospection.domain.Prospection;
import com.quickhire.app.prospection.domain.ProspectionId;
import com.quickhire.app.prospection.domain.repositories.ProspectionRepository;
import com.quickhire.app.prospection.event.MessageToProspectEmitted;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProspectionApplicationService {

  private final ProspectionRepository prospectionRepository;

  private final List<MessageToProspectEmitted> messageToProspectEmittedEvents = new ArrayList<>();

  public ProspectionApplicationService(ProspectionRepository prospectionRepository) {
    this.prospectionRepository = prospectionRepository;
  }

  public Prospection create(Prospection prospection) {
    prospectionRepository.save(prospection);
    return prospection;
  }

  public void sendProspectionToProspects(ProspectionId prospectionId, List<Prospect.ProspectId> prospectIds) {
    Prospection prospection = prospectionRepository
      .findById(prospectionId)
      .orElseThrow(() -> new NoSuchElementException("Prospection not found"));
    prospectIds.forEach(prospectId -> messageToProspectEmittedEvents.add(new MessageToProspectEmitted(prospectId, prospection.messageId()))
    );
  }
}
