package com.sakhshop.backend.config.scheduled;

import com.sakhshop.backend.models.activation.NotActivatedSellerPerson;
import com.sakhshop.backend.models.seller.person.SellerPerson;
import com.sakhshop.backend.service.jpa.ServiceJpa;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchedulerNotActivatedSellerPerson {


    private final
    ServiceJpa serviceJpa;

    public SchedulerNotActivatedSellerPerson(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }


    //        @Scheduled(fixedDelay = 86_400_000) // 1 день 86_400_000
    @Scheduled(cron = "0 0 0/23 * * *") // Каждый день в 23 часа "0 0 0/23 * * *"
    public void deleteNotActivatedSellerPerson() {

        // Тут хранятся все записи NotActivatedSellerPerson
        List<NotActivatedSellerPerson> notActivatedSellerPersons = new ArrayList<>();

        // Тут получаем все запись из базы данных NotActivatedSellerPerson
        serviceJpa.findAllDateDeletionSellerPerson().forEach(notActivatedSellerPersons::add);

        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить
        List<NotActivatedSellerPerson> entities1 = notActivatedSellerPersons
                .stream()
                .filter(NotActivatedSellerPerson::isActive).toList();


        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить вместе с аккаунтом
        List<NotActivatedSellerPerson> entities2 = notActivatedSellerPersons
                .stream()
                .filter(notActivatedSellerPerson -> Instant.now().isAfter(notActivatedSellerPerson.getDateDeletionSellerPerson()))
                .filter(notActivatedSellerPerson -> !notActivatedSellerPerson.isActive()).toList();

        // Тут хранятся ID аккаунтов которые нужно удалить
        List<Long> longList = new ArrayList<>();

        // Получаем ID
        entities2.forEach(notActivatedSellerPerson -> longList.add(notActivatedSellerPerson.getSellerPerson().getId()));

        // Сюда присваиваем все аккаунты которые нужно удалить
        List<SellerPerson> sellerList =  serviceJpa.findAllByIdSellerPerson(longList);


        if (!entities1.isEmpty()){
            serviceJpa.deleteAllSellerPerson(entities1);
        } else if (!entities2.isEmpty()){
            serviceJpa.deleteListSellerPerson(sellerList);
        }



    }


}
