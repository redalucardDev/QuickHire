package com.quickhire.app.prospection.domain;

import com.quickhire.app.job.domain.JobId;
import com.quickhire.app.message.domain.MessageId;
import com.quickhire.app.prospect.domain.ProspectId;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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


  @Test
  void shouldSendProspectionToProspect() {

    ProspectId prospectId = new ProspectId(UUID.randomUUID());
    ProspectionId prospectionId = new ProspectionId(UUID.randomUUID());
    MessageId messageId = new MessageId(UUID.randomUUID());
    JobId jobId = new JobId(UUID.randomUUID());

    Prospection prospection = new Prospection(prospectionId, jobId, messageId);

    assertThat(prospection.sendTo(List.of(prospectId))).isTrue();

  }


}
