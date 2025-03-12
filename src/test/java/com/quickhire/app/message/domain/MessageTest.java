package com.quickhire.app.message.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MessageTest {

  @Test
  void shouldGetMessage() {

    UUID id = UUID.randomUUID();
    Message message = new Message(new MessageId(id), "This is a test message");
    assertThat(message).isEqualTo(new Message(new MessageId(id), "This is a test message"));

  }


}

