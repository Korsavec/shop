package com.sakhshop.backend.config.scheduled;

import com.sakhshop.backend.models.activation.NotActivatedSellerLimited;
import com.sakhshop.backend.models.seller.llc.SellerLimited;
import com.sakhshop.backend.service.jpa.ServiceJpa;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchedulerNotActivatedSellerLimited {

    private final
    ServiceJpa serviceJpa;

    public SchedulerNotActivatedSellerLimited(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }

    //        @Scheduled(fixedDelay = 86_400_000) // 1 день 86_400_000
    @Scheduled(cron = "0 0 0/23 * * *") // Каждый день в 23 часа "0 0 0/23 * * *"
    public void deleteNotActivatedSellerLimited() {

        // Тут хранятся все записи NotActivatedPerson
        List<NotActivatedSellerLimited> notActivatedSellerLimiteds = new ArrayList<>();

        // Тут получаем все запись из базы данных NotActivatedPerson
        serviceJpa.findAllDateDeletionSellerLimited().forEach(notActivatedSellerLimiteds::add);

        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить
        List<NotActivatedSellerLimited> entities1 = notActivatedSellerLimiteds
                .stream()
                .filter(NotActivatedSellerLimited::isActive).toList();


        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить вместе с аккаунтом
        List<NotActivatedSellerLimited> entities2 = notActivatedSellerLimiteds
                .stream()
                .filter(notActivatedSellerLimited -> Instant.now().isAfter(notActivatedSellerLimited.getDateDeletionSellerLimited()))
                .filter(notActivatedSellerLimited -> !notActivatedSellerLimited.isActive()).toList();

        // Тут хранятся ID аккаунтов которые нужно удалить
        List<Long> longList = new ArrayList<>();

        // Получаем ID
        entities2.forEach(notActivatedSellerLimited -> longList.add(notActivatedSellerLimited.getSellerLimited().getId()));

        // Сюда присваиваем все аккаунты которые нужно удалить
        List<SellerLimited> sellerList =  serviceJpa.findAllByIdSellerLimited(longList);


        if (!entities1.isEmpty()){
            serviceJpa.deleteAllSellerLimited(entities1);
        } else if (!entities2.isEmpty()){
            serviceJpa.deleteListSellerLimited(sellerList);
        }



    }
}
