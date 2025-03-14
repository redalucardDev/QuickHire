package com.quickhire.app.message.domain;


public record Message(MessageId messageId, Template body) {

  public enum MessageSendingMode {
    EMAIL
  }
}
