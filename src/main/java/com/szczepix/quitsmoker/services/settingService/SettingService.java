package com.szczepix.quitsmoker.services.settingService;

import com.szczepix.quitsmoker.dao.ISettingRepository;
import com.szczepix.quitsmoker.entities.SettingEntity;
import com.szczepix.quitsmoker.events.MoneyChangeEvent;
import com.szczepix.quitsmoker.jobs.IJobFactory;
import com.szczepix.quitsmoker.models.SettingModel;
import com.szczepix.quitsmoker.services.eventService.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SettingService implements ISettingService {

    private final Integer ID = 1;

    private final ISettingRepository repository;
    private final SettingCache cache;
    private final IJobFactory jobFactory;
    private final EventService eventService;

    @Autowired
    public SettingService(final ISettingRepository repository, final SettingCache cache, final IJobFactory jobFactory, final EventService eventService) {
        this.repository = repository;
        this.cache = cache;
        this.jobFactory = jobFactory;
        this.eventService = eventService;
    }

    @PostConstruct
    public void init() {
        createIfNotExists();
        updateJobs();
    }

    private void createIfNotExists() {
        if (Objects.isNull(repository.findOne(ID))) {
            SettingEntity entity = new SettingEntity();
            entity.setPrice(0d);
            entity.setTimestamp(0L);
            repository.save(entity);
        }
        updateCache();
    }

    private void updateJobs() {
        jobFactory.createJob(getSettings());
    }

    private void updateCache() {
        cache.update(StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(entity -> new SettingModel(entity, eventService))
                .collect(Collectors.toList()));
    }

    @Override
    public SettingModel getSettings() {
        return cache.getById(ID);
    }

    @Override
    public void save() {
        repository.save(getSettings().getEntity());
        eventService.dispatch(new MoneyChangeEvent());
    }
}

