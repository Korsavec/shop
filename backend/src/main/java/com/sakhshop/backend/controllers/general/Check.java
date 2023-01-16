package com.sakhshop.backend.controllers.general;

import com.sakhshop.backend.models.payload.request.seller.person.CheckShopNameRegistrationSellerPersonRequest;
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


    @PostMapping(value = "/checkShopNameRegistrationSellerPersonRequest", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> checkShopName(@RequestBody CheckShopNameRegistrationSellerPersonRequest checkShopNameRegistrationSellerPersonRequest) {

        if (checkShopNameRegistrationSellerPersonRequest.shopName().length() > 30
                || checkShopNameRegistrationSellerPersonRequest.shopName().length() < 3
                || validationRegExp.onlyLettersCyrillicAndNumbersRegExp(checkShopNameRegistrationSellerPersonRequest.shopName())) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),"no"), HttpStatus.OK);

        } else if (Boolean.FALSE.equals(serviceJpa.existsByShopNameSellerPerson(checkShopNameRegistrationSellerPersonRequest.shopName()))) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),"no"), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(),"Ok"), HttpStatus.OK);

        }


    }


}
