package com.quickhire.app.message.infrastructure.secondary.providers;

import com.quickhire.app.message.domain.Message;

public interface EmailDispatcher {

  boolean send(Message message, String senderEmail, String recipientEmail);
}
