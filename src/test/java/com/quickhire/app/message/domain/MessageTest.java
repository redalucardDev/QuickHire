package com.quickhire.app.message.domain;

import com.quickhire.app.message.domain.providers.MessageSender;
import com.quickhire.app.message.infrastructure.secondary.providers.MessageSenderImpl;
import com.quickhire.app.message.providers.MessageProvider;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MessageTest {

  private final MessageSender emailMessageSenderStub = new MessageSenderImpl(
    "sender@test.com", "receiver@test.com");

  @Test
  void shouldGetMessage() {

    UUID id = UUID.randomUUID();
    UUID recipientId = UUID.randomUUID();
    Message message = MessageProvider.createMessage(id, recipientId);
    assertThat(message).isEqualTo(expectedMessage(id, recipientId));

  }

  private static Message expectedMessage(UUID id, UUID recipientId) {
    return new Message(new MessageId(id)
      , new Template("This is a test message", "This is a test signature"), new RecipientId(recipientId));
  }


  @Test
  void shouldSendMessage() {
    UUID id = UUID.randomUUID();
    Message message = MessageProvider.createMessage(id, UUID.randomUUID());
    assertThat(emailMessageSenderStub.send(message, MessageSendingMode.EMAIL)).isTrue();
  }


}

