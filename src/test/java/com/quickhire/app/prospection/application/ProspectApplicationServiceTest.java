package com.quickhire.app.prospection.application;


import com.quickhire.app.job.application.JobApplicationService;
import com.quickhire.app.job.domain.Job;
import com.quickhire.app.job.domain.JobId;
import com.quickhire.app.job.infrastructure.secondary.repositories.FakeJobRepository;
import com.quickhire.app.job.providers.JobProvider;
import com.quickhire.app.message.application.MessageApplicationService;
import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.MessageId;
import com.quickhire.app.message.infrastructure.secondary.providers.MessageSenderImpl;
import com.quickhire.app.message.infrastructure.secondary.repositories.FakeMessageRepository;
import com.quickhire.app.message.providers.MessageProvider;
import com.quickhire.app.prospect.application.ProspectApplicationService;
import com.quickhire.app.prospect.domain.Prospect;
import com.quickhire.app.prospect.domain.ProspectId;
import com.quickhire.app.prospect.infrastructure.secondary.repositories.FakeProspectRepository;
import com.quickhire.app.prospection.domain.Prospection;
import com.quickhire.app.prospection.domain.ProspectionId;
import com.quickhire.app.prospection.domain.repositories.ProspectionRepository;
import com.quickhire.app.prospection.infrastructure.secondary.repositories.FakeProspectionRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.quickhire.app.prospect.providers.ProspectProvider.createProspect;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProspectApplicationServiceTest {


  private final ProspectApplicationService prospectApplicationService = new ProspectApplicationService(new FakeProspectRepository());

  private final JobApplicationService jobApplicationService = new JobApplicationService(new FakeJobRepository());

  private final MessageApplicationService messageApplicationService = new MessageApplicationService(
    new FakeMessageRepository(), new MessageSenderImpl("sender@test.com", "receiver@test.com"));

  private final ProspectionRepository prospectionRepository = new FakeProspectionRepository();
  private final ProspectionApplicationService prospectionApplicationService = new ProspectionApplicationService(prospectionRepository);

  @Test
  void shouldGetProspection() {

    UUID prospectId = UUID.randomUUID();
    UUID messageId = UUID.randomUUID();
    UUID jobId = UUID.randomUUID();

    Prospection prospection = createProspection(prospectId, jobId, messageId);

    Prospection expectedProspection = new Prospection(prospection.prospectionId(), new JobId(jobId), new MessageId(messageId));

    assertThat(prospectionApplicationService.create(prospection)).isEqualTo(expectedProspection);


    assertThat(prospectionRepository.findById(prospection.prospectionId()).isPresent()).isTrue();
    assertThat(prospectionRepository.findById(prospection.prospectionId()).get()).isEqualTo(expectedProspection);

  }


  @Test
  void shouldSendProspectionToProspect() {

    ProspectId prospectId = new ProspectId(UUID.randomUUID());

    Prospection prospection = createProspection(prospectId.prospectId(), UUID.randomUUID(), UUID.randomUUID());

    prospectionApplicationService.create(prospection);

    prospectionApplicationService.sendProspectionToProspects(prospection.prospectionId(), List.of(prospectId));
  }

  private Prospection createProspection(UUID prospectId, UUID jobId, UUID messageId) {
    Prospect prospect = createProspect("john.doe@gmail.com", "+33662887766");
    prospectApplicationService.create(prospect);

    Job job = JobProvider.createJob();
    jobApplicationService.create(job);

    Message message = MessageProvider.createMessage(UUID.randomUUID());
    messageApplicationService.create(message);

    return new Prospection(new ProspectionId(prospectId), new JobId(jobId), new MessageId(messageId));
  }

}
