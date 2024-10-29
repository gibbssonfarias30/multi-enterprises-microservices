package com.backdevfc.msmasterdata.application.controller;

import com.backdevfc.msmasterdata.application.service.EnterprisesTypeService;
import com.backdevfc.msmasterdata.domain.model.EnterprisesType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RequestMapping("/enterprises")
@RestController
public class EnterprisesTypeController {

    private final EnterprisesTypeService enterprisesTypeService;

    @GetMapping
    public ResponseEntity<List<EnterprisesType>> findAll(){
        return ResponseEntity.ok(enterprisesTypeService.findAll());
    }

    @PostMapping
    public ResponseEntity<EnterprisesType> create(@RequestBody EnterprisesType enterprisesType) {
        EnterprisesType created = enterprisesTypeService.create(enterprisesType);
        return ResponseEntity.status(CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnterprisesType> findById(@PathVariable Long id) {
        return enterprisesTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnterprisesType> update(@PathVariable Long id, @RequestBody EnterprisesType enterprisesType) {
        return enterprisesTypeService.update(id, enterprisesType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnterprisesType> delete(@PathVariable Long id) {
        return enterprisesTypeService.delete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
