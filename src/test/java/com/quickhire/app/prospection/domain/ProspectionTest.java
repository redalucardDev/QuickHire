package com.quickhire.app.prospection.domain;

import com.quickhire.app.job.domain.JobId;
import com.quickhire.app.message.domain.MessageId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


class ProspectionTest {

  @Test
  void shouldGetProspection() {

    ProspectionId prospectionId = new ProspectionId(UUID.randomUUID());
    MessageId messageId = new MessageId(UUID.randomUUID());
    JobId jobId = new JobId(UUID.randomUUID());
    Prospection prospection = new Prospection(prospectionId, jobId, messageId);

    assertThat(prospection)
      .isEqualTo(new Prospection(prospectionId, jobId, messageId));

  }


}
