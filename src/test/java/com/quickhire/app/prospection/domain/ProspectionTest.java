package com.quickhire.app.prospection.domain;

import com.quickhire.app.job.domain.Job;
import com.quickhire.app.message.domain.Message;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


class ProspectionTest {

  @Test
  void shouldGetProspection() {

    ProspectionId prospectionId = new ProspectionId(UUID.randomUUID());
    Message.MessageId messageId = new Message.MessageId(UUID.randomUUID());
    Job.JobId jobId = new Job.JobId(UUID.randomUUID());
    Prospection prospection = new Prospection(prospectionId, jobId, messageId);

    assertThat(prospection)
      .isEqualTo(new Prospection(prospectionId, jobId, messageId));

  }


}
