package com.quickhire.app.message.domain;

public enum MessageStatus {
  PENDING("pending"),
  SENT("sent"),
  FAILED("failed");

  private final String status;

  MessageStatus(String status) {
    this.status = status;
  }

}
