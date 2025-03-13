package com.quickhire.app.message.application;

import com.quickhire.app.message.domain.*;
import com.quickhire.app.message.domain.providers.MessageSender;
import com.quickhire.app.message.infrastructure.secondary.providers.MessageSenderImpl;
import com.quickhire.app.message.providers.MessageProvider;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MessageApplicationServiceTest {

  private final MessageSender emailMessageSenderStub = new MessageSenderImpl(
    "sender@test.com", "receiver@test.com");

  @Test
  void shouldSendMessageByEmail() {
    UUID id = UUID.randomUUID();
    UUID recipientId = UUID.randomUUID();
    Message message = MessageProvider.createMessage(id, recipientId);

    MessageApplicationService messageApplicationService = new MessageApplicationService(emailMessageSenderStub);

    boolean messageIsSent = messageApplicationService.sendMessage(message, MessageSendingMode.EMAIL);

    assertThat(messageIsSent).isTrue();


  }
}
