package com.quickhire.app.message.application;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.MessageId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MessageApplicationServiceTest {

  private final MessageSender fakeEmailMessageSender = new MessageSenderImpl("test@test.com");

  @Test
  void shouldSendMessageByEmail() {
    UUID id = UUID.randomUUID();

    Message message = new Message(new MessageId(id)
      , "This is a test message");

    MessageApplicationService messageApplicationService = new MessageApplicationService(fakeEmailMessageSender);

    boolean messageIsSent = messageApplicationService.sendMessage(message, MessageSendingMode.EMAIL);

    assertThat(messageIsSent).isTrue();


  }
}
