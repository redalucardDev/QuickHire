package com.quickhire.app.message.domain;

public record Message(MessageId messageId, String body, MessageStatus pending) {
}
