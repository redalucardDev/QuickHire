package com.quickhire.app.prospection.domain.event;

import com.quickhire.app.message.domain.MessageId;
import com.quickhire.app.prospect.domain.ProspectId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MessageToProspectEmittedTest {

  @Test
  void shouldGetMessageToProspectEmitted() {

    UUID prospectId = UUID.randomUUID();
    UUID messageId = UUID.randomUUID();

    MessageToProspectEmitted messageToProspectEmitted = new MessageToProspectEmitted(new ProspectId(prospectId), new MessageId(messageId));

    assertThat(messageToProspectEmitted).isEqualTo(
      new MessageToProspectEmitted(new ProspectId(prospectId), new MessageId(messageId)));
  }

}
