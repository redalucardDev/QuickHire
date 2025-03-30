package com.quickhire.app.recruitment.domain.events;

public interface EventPublisher {
  void publish(RecruitmentEvent event);
}
