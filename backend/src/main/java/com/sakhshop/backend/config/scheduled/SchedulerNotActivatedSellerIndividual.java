package com.sakhshop.backend.config.scheduled;

import com.sakhshop.backend.models.activation.NotActivatedSellerIndividual;
import com.sakhshop.backend.models.seller.ie.SellerIndividual;
import com.sakhshop.backend.service.jpa.ServiceJpa;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchedulerNotActivatedSellerIndividual implements Serializable {

    @Serial
    private static final long serialVersionUID = 277232319713313016L;

    private final
    ServiceJpa serviceJpa;

    public SchedulerNotActivatedSellerIndividual(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }


    //        @Scheduled(fixedDelay = 86_400_000) // 1 день 86_400_000
    @Scheduled(cron = "0 0 0/23 * * *") // Каждый день в 23 часа "0 0 0/23 * * *"
    public void deleteNotActivatedSellerIndividual() {

        // Тут хранятся все записи NotActivatedPerson
        List<NotActivatedSellerIndividual> notActivatedSellerIndividuals = new ArrayList<>();

        // Тут получаем все запись из базы данных NotActivatedPerson
        serviceJpa.findAllDateDeletionSellerIndividual().forEach(notActivatedSellerIndividuals::add);

        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить
        List<NotActivatedSellerIndividual> entities1 = notActivatedSellerIndividuals
                .stream()
                .filter(NotActivatedSellerIndividual::isActive).toList();


        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить вместе с аккаунтом
        List<NotActivatedSellerIndividual> entities2 = notActivatedSellerIndividuals
                .stream()
                .filter(notActivatedSellerIndividual -> Instant.now().isAfter(notActivatedSellerIndividual.getDateDeletionSellerIndividual()))
                .filter(notActivatedSellerIndividual -> !notActivatedSellerIndividual.isActive()).toList();

        // Тут хранятся ID аккаунтов которые нужно удалить
        List<Long> longList = new ArrayList<>();

        // Получаем ID
        entities2.forEach(notActivatedSellerIndividual -> longList.add(notActivatedSellerIndividual.getSellerIndividual().getId()));

        // Сюда присваиваем все аккаунты которые нужно удалить
        List<SellerIndividual> sellerList =  serviceJpa.findAllByIdSellerIndividual(longList);


        if (!entities1.isEmpty()){
            serviceJpa.deleteAllSellerIndividual(entities1);
        } else if (!entities2.isEmpty()){
            serviceJpa.deleteListSellerIndividual(sellerList);
        }



    }
}
