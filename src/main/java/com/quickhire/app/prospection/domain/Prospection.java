package com.quickhire.app.prospection.domain;

import com.quickhire.app.job.domain.Job;
import com.quickhire.app.message.domain.Message;


public record Prospection(ProspectionId prospectionId, Job.JobId jobId, Message.MessageId messageId) {

}

