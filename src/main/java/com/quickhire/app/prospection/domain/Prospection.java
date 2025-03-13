package com.quickhire.app.prospection.domain;

import com.quickhire.app.job.domain.JobId;
import com.quickhire.app.message.domain.MessageId;
import com.quickhire.app.prospect.domain.ProspectId;

import java.util.List;

public record Prospection(ProspectionId prospectionId,JobId jobId, MessageId messageId) {

  public boolean sendTo(List<ProspectId> prospectIds) {



    return true;
  }
}
