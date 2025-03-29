package com.quickhire.app.recruitment.domaine.application;

import com.quickhire.app.recruitment.domaine.Message;
import com.quickhire.app.recruitment.domaine.ResumeId;
import com.quickhire.app.recruitment.domaine.interview.InterviewDuration;
import com.quickhire.app.recruitment.domaine.interview.Interviews;
import com.quickhire.app.recruitment.domaine.job.JobId;
import java.time.LocalDateTime;

public interface ApplicationState {
  Application scheduleInterview(LocalDateTime localDateTime, InterviewDuration duration, ResumeId resumeId, Interviews interviews);

  Offer accept(Message offerMessage, JobId jobId);

  Application decline(Message declineMessage, ResumeId resumeId);
}
