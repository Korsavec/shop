package com.sakhshop.backend.config.scheduled;

import com.sakhshop.backend.models.activation.NotActivatedSeller;
import com.sakhshop.backend.models.seller.Seller;
import com.sakhshop.backend.service.jpa.ServiceJpa;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchedulerNotActivatedSeller {


    private final
    ServiceJpa serviceJpa;

    public SchedulerNotActivatedSeller(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }


    //        @Scheduled(fixedDelay = 86_400_000) // 1 день 86_400_000
    @Scheduled(cron = "0 0 0/23 * * *") // Каждый день в 23 часа "0 0 0/23 * * *"
    public void deleteNotActivatedSeller() {

        // Тут хранятся все записи NotActivatedSeller
        List<NotActivatedSeller> notActivatedSellers = new ArrayList<>();

        // Тут получаем все запись из базы данных NotActivatedSeller
        serviceJpa.findAllDateDeletionSeller().forEach(notActivatedSellers::add);

        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить
        List<NotActivatedSeller> entities1 = notActivatedSellers
                .stream()
                .filter(NotActivatedSeller::isActive).toList();


        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить вместе с аккаунтом
        List<NotActivatedSeller> entities2 = notActivatedSellers
                .stream()
                .filter(notActivatedSeller -> Instant.now().isAfter(notActivatedSeller.getDateDeletionSeller()))
                .filter(notActivatedSeller -> !notActivatedSeller.isActive()).toList();

        // Тут хранятся ID аккаунтов которые нужно удалить
        List<Long> longList = new ArrayList<>();

        // Получаем ID
        entities2.forEach(notActivatedSeller -> longList.add(notActivatedSeller.getSeller().getId()));

        // Сюда присваиваем все аккаунты которые нужно удалить
        List<Seller> sellerList =  serviceJpa.findAllByIdSeller(longList);


        if (!entities1.isEmpty()){
            serviceJpa.deleteAllSeller(entities1);
        } else if (!entities2.isEmpty()){
            serviceJpa.deleteListSeller(sellerList);
        }



    }


}
