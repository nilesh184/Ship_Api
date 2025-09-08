package com.example.ships.service;

import com.example.ships.model.Ship;
import com.example.ships.dto.ShipRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ShipService {

    private final Map<Long, Ship> store = Collections.synchronizedMap(new LinkedHashMap<>());
    private final AtomicLong idGen = new AtomicLong(1);

    public List<Ship> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Ship> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Ship create(ShipRequest req) {
        long id = idGen.getAndIncrement();
        Ship s = new Ship(id, req.getName(), req.getDescription(), req.getOwnerEmail(), LocalDateTime.now());
        store.put(id, s);
        return s;
    }

    public Optional<Ship> update(Long id, ShipRequest req) {
        Ship existing = store.get(id);
        if (existing == null) return Optional.empty();
        existing.setName(req.getName());
        existing.setDescription(req.getDescription());
        existing.setOwnerEmail(req.getOwnerEmail());
        existing.setTimestamp(LocalDateTime.now());
        return Optional.of(existing);
    }

    public boolean delete(Long id) {
        return store.remove(id) != null;
    }

    // helper to preload some data
//    public void initSample() {
//        if (store.isEmpty()) {
//            create(new ShipRequest(){{ setName("Titanic"); setDescription("Sample classic ship"); setOwnerEmail("owner@example.com"); }});
//            create(new ShipRequest(){{ setName("Black Pearl"); setDescription("Fictional"); setOwnerEmail("captain@sea.com"); }});
//        }
//    }
}
