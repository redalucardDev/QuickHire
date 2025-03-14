package com.quickhire.app.message.application;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.providers.MessageSender;
import com.quickhire.app.message.domain.repositories.MessageRepository;
import com.quickhire.app.message.infrastructure.secondary.providers.MessageSenderImpl;
import com.quickhire.app.message.infrastructure.secondary.repositories.FakeMessageRepository;
import com.quickhire.app.message.providers.MessageProvider;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MessageApplicationServiceTest {

  private final MessageRepository messageRepository = new FakeMessageRepository();


  private final MessageSender emailMessageSenderStub = new MessageSenderImpl(
    "sender@test.com", "receiver@test.com");

  private final MessageApplicationService messageApplicationService = new MessageApplicationService(messageRepository, emailMessageSenderStub);


  @Test
  void shouldGetMessage() {

    UUID id = UUID.randomUUID();
    Message message = MessageProvider.createMessage(id);

    assertThat(messageApplicationService.create(message)).isEqualTo(new Message(message.messageId(), message.body()));
    assertThat(messageRepository.findById(message.messageId()).isPresent()).isTrue();
    assertThat(messageRepository.findById(message.messageId()).get()).isEqualTo(new Message(message.messageId(), message.body()));

  }

  @Test
  void shouldSendMessageByEmail() {
    UUID id = UUID.randomUUID();
    Message message = MessageProvider.createMessage(id);

    boolean messageIsSent = messageApplicationService.sendMessage(message, Message.MessageSendingMode.EMAIL);

    assertThat(messageIsSent).isTrue();

  }
}
