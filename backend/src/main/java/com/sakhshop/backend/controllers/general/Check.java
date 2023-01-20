package com.sakhshop.backend.controllers.general;

import com.sakhshop.backend.models.payload.request.seller.CheckShopName;
import com.sakhshop.backend.models.payload.response.MessageResponse;
import com.sakhshop.backend.service.jpa.ServiceJpa;
import com.sakhshop.backend.validation.ValidationRegExp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Check {


    private final
    ValidationRegExp validationRegExp;

    private final
    ServiceJpa serviceJpa;

    public Check(ValidationRegExp validationRegExp, ServiceJpa serviceJpa) {
        this.validationRegExp = validationRegExp;
        this.serviceJpa = serviceJpa;
    }


    @PostMapping(value = "/checkShopName", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> checkShopName(@RequestBody CheckShopName checkShopName) {

        if (checkShopName.shopName().length() > 30
                || checkShopName.shopName().length() < 3
                || validationRegExp.onlyLettersCyrillicAndNumbersRegExp(checkShopName.shopName())) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),"no"), HttpStatus.OK);

        } else if (Boolean.FALSE.equals(serviceJpa.existsByShopNameSeller(checkShopName.shopName()))) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),"no"), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),"Ok"), HttpStatus.OK);

        }


    }


}
