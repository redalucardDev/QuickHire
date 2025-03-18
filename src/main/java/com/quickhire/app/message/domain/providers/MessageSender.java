package com.quickhire.app.message.domain.providers;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.Message.*;

public interface MessageSender {
  boolean send(Message message, MessageSendingMode mode);
}
