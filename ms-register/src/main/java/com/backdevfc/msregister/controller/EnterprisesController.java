package com.backdevfc.msregister.controller;


import com.backdevfc.msregister.aggregates.request.RequestEnterprises;
import com.backdevfc.msregister.aggregates.response.ResponseBase;
import com.backdevfc.msregister.service.EnterprisesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/enterprises")
@RestController
public class EnterprisesController {

    private final EnterprisesService enterprisesService;

    @GetMapping
    public ResponseBase getEnterprises() {
        return enterprisesService.findAllEnterprises();
    }

    @PostMapping
    public ResponseBase createEnterprise(@RequestBody RequestEnterprises requestEnterprises) {
        return enterprisesService.createEnterprise(requestEnterprises);
    }

    @GetMapping("/{ruc}")
    public ResponseBase findOneEnterprise(@PathVariable String ruc) {
        return enterprisesService.findOneEnterprise(ruc);
    }

    @PutMapping("/{id}")
    public ResponseBase updateEnterprise(@PathVariable int id, @RequestBody RequestEnterprises requestEnterprises) {
        return enterprisesService.updateEnterprise(id, requestEnterprises);
    }

    @DeleteMapping("/{id}")
    public ResponseBase deleteEnterprise(@PathVariable int id) {
        return enterprisesService.deleteEnterprise(id);
    }

}
