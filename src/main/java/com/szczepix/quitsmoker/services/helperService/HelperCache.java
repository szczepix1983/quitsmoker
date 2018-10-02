package com.szczepix.quitsmoker.services.helperService;

import com.szczepix.quitsmoker.models.HelperModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

@Component
public class HelperCache {

    private final Logger LOG = Logger.getLogger(getClass().getName());

    private Map<Integer, HelperModel> cacheMap = new TreeMap<>();
    private List<HelperModel> cacheList = new ArrayList<>();

    public void update(final List<HelperModel> list) {
        cacheMap.clear();
        list.forEach(model -> cacheMap.put(model.getEntity().getId(), model));
        cacheList = list;
        LOG.info("Helper: " + cacheList.size());
    }

    public HelperModel getById(final Integer value) {
        return cacheMap.get(value);
    }

    public List<HelperModel> getCache() {
        return cacheList;
    }
}