package com.quickhire.app.prospection.domain;

import com.quickhire.app.job.domain.JobId;
import com.quickhire.app.message.domain.MessageId;


public record Prospection(ProspectionId prospectionId, JobId jobId, MessageId messageId) {

}

