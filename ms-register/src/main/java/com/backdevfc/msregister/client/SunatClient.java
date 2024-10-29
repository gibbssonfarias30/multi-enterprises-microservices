package com.backdevfc.msregister.client;

import com.backdevfc.msregister.aggregates.response.ResponseSunat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sunat-client", url = "${api.sunat}")
public interface SunatClient {

    @GetMapping("/ruc")
    ResponseSunat getInfoSunat(@RequestParam("numero") String numero,
                               @RequestHeader("Authorization") String authorizationHeader);
}
