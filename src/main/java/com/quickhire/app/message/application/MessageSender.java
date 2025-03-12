package com.quickhire.app.message.application;

import com.quickhire.app.message.domain.Message;

interface MessageSender {

  boolean send(Message message, MessageSendingMode mode);
}
