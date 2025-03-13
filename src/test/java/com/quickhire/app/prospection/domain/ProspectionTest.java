package com.quickhire.app.prospection.domain;

import com.quickhire.app.job.domain.JobId;
import com.quickhire.app.message.domain.MessageId;
import com.quickhire.app.prospect.domain.ProspectId;
import com.quickhire.app.prospection.domain.event.MessageToProspectEmitted;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


class ProspectionTest {

  @Test
  void shouldGetProspection() {

    ProspectionId prospectionId = new ProspectionId(UUID.randomUUID());
    MessageId messageId = new MessageId(UUID.randomUUID());
    JobId jobId = new JobId(UUID.randomUUID());
    Prospection prospection = new Prospection(prospectionId, jobId, messageId, new ArrayList<>());

    assertThat(prospection)
      .isEqualTo(new Prospection(prospectionId, jobId, messageId, new ArrayList<>()));
  }


  @Test
  void shouldSendProspectionToProspect() {

    ProspectId prospectId = new ProspectId(UUID.randomUUID());
    ProspectionId prospectionId = new ProspectionId(UUID.randomUUID());
    MessageId messageId = new MessageId(UUID.randomUUID());
    JobId jobId = new JobId(UUID.randomUUID());

    Prospection prospection = new Prospection(prospectionId, jobId, messageId, new ArrayList<>());


    assertThat(prospection.prospectionId()).isEqualTo(prospectionId);
    assertThat(prospection.sendMessageTo(List.of(prospectId)).messageId()).isEqualTo(
    messageId);
    assertThat(prospection.messageToProspectEmittedEvents()).containsExactly(
      new MessageToProspectEmitted(prospectId, messageId));


  }


}
