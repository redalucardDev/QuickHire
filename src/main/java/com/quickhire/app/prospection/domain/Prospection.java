package com.quickhire.app.prospection.domain;

import com.quickhire.app.job.domain.JobId;
import com.quickhire.app.message.domain.MessageId;
import com.quickhire.app.prospect.domain.ProspectId;
import com.quickhire.app.prospection.domain.event.MessageToProspectEmitted;

import java.util.List;

public record Prospection(ProspectionId prospectionId, JobId jobId, MessageId messageId, List<MessageToProspectEmitted> messageToProspectEmittedEvents) {


  public Prospection sendMessageTo(List<ProspectId> prospectIds) {

    prospectIds.forEach(prospectId -> messageToProspectEmittedEvents.add(new MessageToProspectEmitted(prospectId, messageId)));

    return this;
  }
}

