package com.quickhire.app.message.domain.providers;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.MessageSendingMode;

public interface MessageSender {

  boolean send(Message message, MessageSendingMode mode);
}
