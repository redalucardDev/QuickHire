package com.quickhire.app.prospection.application;


import com.quickhire.app.job.application.JobApplicationService;
import com.quickhire.app.job.domain.Job;
import com.quickhire.app.job.domain.repositories.JobRepository;
import com.quickhire.app.job.infrastructure.secondary.repositories.FakeJobRepository;
import com.quickhire.app.job.providers.JobProvider;
import com.quickhire.app.message.application.MessageApplicationService;
import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.repositories.MessageRepository;
import com.quickhire.app.message.infrastructure.secondary.providers.MessageSenderImpl;
import com.quickhire.app.message.infrastructure.secondary.repositories.FakeMessageRepository;
import com.quickhire.app.message.providers.MessageProvider;
import com.quickhire.app.prospect.application.ProspectApplicationService;
import com.quickhire.app.prospect.domain.Prospect;
import com.quickhire.app.prospect.domain.repositories.ProspectRepository;
import com.quickhire.app.prospect.infrastructure.secondary.repositories.FakeProspectRepository;
import com.quickhire.app.prospection.domain.Prospection;
import com.quickhire.app.prospection.domain.ProspectionId;
import com.quickhire.app.prospection.domain.repositories.ProspectionRepository;
import com.quickhire.app.prospection.infrastructure.secondary.repositories.FakeProspectionRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static com.quickhire.app.prospect.providers.ProspectProvider.createProspect;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProspectApplicationServiceTest {


  ProspectRepository prospectRepository = new FakeProspectRepository();
  ProspectApplicationService prospectApplicationService = new ProspectApplicationService(prospectRepository);

  JobRepository jobRepository = new FakeJobRepository();
  JobApplicationService jobApplicationService = new JobApplicationService(jobRepository);


  MessageRepository messageRepository = new FakeMessageRepository();
  MessageApplicationService messageApplicationService = new MessageApplicationService(
    messageRepository, new MessageSenderImpl("sender@test.com", "receiver@test.com"));

  ProspectionRepository prospectionRepository = new FakeProspectionRepository();
  ProspectionApplicationService prospectionApplicationService = new ProspectionApplicationService(prospectionRepository);

  @Test
  @Disabled
  void shouldGetProspection() {

    Prospect prospect = createProspect("john.doe@gmail.com", "+33662887766");
    prospectApplicationService.create(prospect);

    Job job = JobProvider.createJob();
    jobApplicationService.create(job);

    Message message = MessageProvider.createMessage(UUID.randomUUID());
    messageApplicationService.create(message);

    Prospection prospection = new Prospection(new ProspectionId(UUID.randomUUID()), job.jobId(), message.messageId(), new ArrayList<>());

    Prospection expectedProspection = new Prospection(prospection.prospectionId(), job.jobId(), message.messageId(), new ArrayList<>());

    assertThat(prospectionApplicationService.create(prospection)).isEqualTo(expectedProspection);
    assertThat(prospectionRepository.findById(prospection.prospectionId())).isEqualTo(expectedProspection);

  }

}
