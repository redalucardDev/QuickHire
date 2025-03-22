package com.quickhire.app.recruitment.domaine.events;

public interface EventPublisher {
  void publish(RecruitmentEvent event);
}
