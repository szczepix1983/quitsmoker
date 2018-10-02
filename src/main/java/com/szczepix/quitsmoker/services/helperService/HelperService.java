package com.szczepix.quitsmoker.services.helperService;

import com.szczepix.quitsmoker.dao.IHelperRepository;
import com.szczepix.quitsmoker.models.HelperModel;
import com.szczepix.quitsmoker.services.eventService.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HelperService implements IHelperService {

    private final Integer ID = 1;

    private final IHelperRepository repository;
    private final HelperCache cache;
    private final EventService eventService;

    @Autowired
    public HelperService(final IHelperRepository repository, final HelperCache cache, final EventService eventService) {
        this.repository = repository;
        this.cache = cache;
        this.eventService = eventService;
    }

    @PostConstruct
    public void init() {
        updateCache();
    }

    @Override
    public List<HelperModel> getHelpers() {
        return cache.getCache();
    }

    private void updateCache() {
        cache.update(StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(entity -> new HelperModel(entity, eventService))
                .collect(Collectors.toList()));
    }
}
