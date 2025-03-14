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
    Message message = MessageProvider.createMessage(id);
    assertThat(message).isEqualTo(new Message(new MessageId(id)
      , new Template(new Template.Body("This is a test message"), new Template.Signature("This is a test signature"))));

  }


  @Test
  void shouldSendMessage() {
    UUID id = UUID.randomUUID();
    Message message = MessageProvider.createMessage(id);
    assertThat(emailMessageSenderStub.send(message, Message.MessageSendingMode.EMAIL)).isTrue();
  }


}

