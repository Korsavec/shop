
package com.sakhshop.backend.models.payload.request.seller;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public record RegistrationSellerRequest (
        Long phone
        , String email
        , String password
        , String surname
        , String name
        , String middleName
        ,

        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        LocalDate dateBirth

        , Long numberPassport
        , Long inn
        , String shopName
        , String region
        , String city
        , String street
        , String building
        , String house
        , int apartment
        , String bankAccount
        , int beakBank
        , String bankName
        , String correspondentAccount
        , Long innBank
        , int kppBank
) {}
