package com.backdevfc.msregister.controller;


import com.backdevfc.msregister.aggregates.request.RequestPersons;
import com.backdevfc.msregister.aggregates.response.ResponseBase;

import com.backdevfc.msregister.service.PersonsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/persons")
@RestController
public class PersonsController {

    private final PersonsService personsService;


    @GetMapping("/info/{numero}")
    public ResponseBase getInfoReniec(@PathVariable String numero) {
        return personsService.getInfoReniec(numero);
    }

    @PostMapping
    public ResponseBase createPerson(@RequestBody RequestPersons requestPersons) {
        return personsService.createPersons(requestPersons);
    }

    @GetMapping
    public ResponseBase findAllPersons() {
        return personsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseBase findOne(@PathVariable int id) {
        return personsService.findOnePerson(id);
    }

    @PutMapping("/{id}")
    public ResponseBase updatePerson(@PathVariable int id, @RequestBody RequestPersons requestPersons) {
        return personsService.updatePersons(id, requestPersons);
    }

}
