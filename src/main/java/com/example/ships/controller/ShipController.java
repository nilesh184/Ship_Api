package com.example.ships.controller;

import com.example.ships.dto.ShipRequest;
import com.example.ships.model.Ship;
import com.example.ships.service.ShipService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ships")
public class ShipController {

    private final ShipService service;

    public ShipController(ShipService service) {
        this.service = service;
    }

//    @PostConstruct
//    public void init() {
//        service.initSample();
//    }

    @GetMapping
    public ResponseEntity<List<Ship>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Ship> create(@Valid @RequestBody ShipRequest req) {
        Ship created = service.create(req);
        return ResponseEntity.created(URI.create("/ships/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ship> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ship> update(@PathVariable Long id, @Valid @RequestBody ShipRequest req) {
        return service.update(id, req).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = service.delete(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
