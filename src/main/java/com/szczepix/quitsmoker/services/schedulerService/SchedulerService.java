package com.szczepix.quitsmoker.services.schedulerService;

import com.szczepix.quitsmoker.jobs.IBaseJob;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class SchedulerService extends ConcurrentTaskScheduler {

    private final Logger LOG = Logger.getLogger(getClass().getName());
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private List<IBaseJob> jobs = new ArrayList<>();

    public SchedulerService() {
        super();
    }

    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    public void scheduleTasks() {
        jobs.stream().filter(IBaseJob::isReady).forEach(this::executeJob);
    }

    public void addJob(final IBaseJob baseJob) {
        jobs.add(baseJob);
    }

    private void executeJob(final IBaseJob baseJob) {
        try {
            LOG.info("Execute job (" + dateTimeFormatter.format(LocalDateTime.now()) + ") - " + baseJob.toString());
            baseJob.submit();
        } catch (Exception e) {
            LOG.warning("Execute job exception - " + baseJob.toString());
        }
    }
}