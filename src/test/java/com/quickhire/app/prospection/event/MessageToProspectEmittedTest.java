package com.quickhire.app.prospection.event;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.prospect.domain.Prospect;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class MessageToProspectEmittedTest {

  @Test
  void shouldGetMessageToProspectEmitted() {
    UUID prospectId = UUID.randomUUID();
    UUID messageId = UUID.randomUUID();

    MessageToProspectEmitted messageToProspectEmitted = new MessageToProspectEmitted(
      new Prospect.ProspectId(prospectId),
      new Message.MessageId(messageId)
    );

    assertThat(messageToProspectEmitted).isEqualTo(
      new MessageToProspectEmitted(new Prospect.ProspectId(prospectId), new Message.MessageId(messageId))
    );
  }
}
