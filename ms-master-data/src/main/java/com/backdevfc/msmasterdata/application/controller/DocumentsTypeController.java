package com.backdevfc.msmasterdata.application.controller;


import com.backdevfc.msmasterdata.application.service.DocumentsTypeService;
import com.backdevfc.msmasterdata.domain.model.DocumentsType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RequestMapping("/documents")
@RestController
public class DocumentsTypeController {
    private final DocumentsTypeService documentsTypeService;

    @GetMapping
    public ResponseEntity<List<DocumentsType>> findAll(){
        return ResponseEntity.ok(documentsTypeService.findAll());
    }

    @PostMapping
    public ResponseEntity<DocumentsType> create(@RequestBody DocumentsType documentType) {
        DocumentsType created = documentsTypeService.create(documentType);
        return ResponseEntity.status(CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentsType> findById(@PathVariable Long id) {
        return documentsTypeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentsType> update(@PathVariable Long id, @RequestBody DocumentsType documentType) {
        return documentsTypeService.update(id, documentType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DocumentsType> delete(@PathVariable Long id) {
        return documentsTypeService.delete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
