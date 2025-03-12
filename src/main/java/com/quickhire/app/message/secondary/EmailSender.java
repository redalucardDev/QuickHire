package com.quickhire.app.message.secondary;

import com.quickhire.app.message.domain.Message;

public interface EmailSender {

  boolean send(Message message);
}
