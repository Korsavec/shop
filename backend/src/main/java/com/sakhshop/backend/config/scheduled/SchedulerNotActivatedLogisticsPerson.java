package com.sakhshop.backend.config.scheduled;

import com.sakhshop.backend.models.activation.NotActivatedLogisticsPerson;
import com.sakhshop.backend.models.logistics.person.LogisticsPerson;
import com.sakhshop.backend.service.jpa.ServiceJpa;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchedulerNotActivatedLogisticsPerson {

    private final
    ServiceJpa serviceJpa;


    public SchedulerNotActivatedLogisticsPerson(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }



    //        @Scheduled(fixedDelay = 86_400_000) // 1 день 86_400_000
    @Scheduled(cron = "0 0 0/23 * * *") // Каждый день в 23 часа "0 0 0/23 * * *"
    public void deleteNotActivatedLogisticsPerson() {

        // Тут хранятся все записи NotActivatedLogisticsPerson
        List<NotActivatedLogisticsPerson> notActivatedLogisticsPersons = new ArrayList<>();

        // Тут получаем все запись из базы данных NotActivatedLogisticsPerson
        serviceJpa.findAllDateDeletionLogisticsPerson().forEach(notActivatedLogisticsPersons::add);

        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить
        List<NotActivatedLogisticsPerson> entities1 = notActivatedLogisticsPersons
                .stream()
                .filter(NotActivatedLogisticsPerson::isActive).toList();


        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить вместе с аккаунтом
        List<NotActivatedLogisticsPerson> entities2 = notActivatedLogisticsPersons
                .stream()
                .filter(notActivatedLogisticsPerson -> Instant.now().isAfter(notActivatedLogisticsPerson.getDateDeletionLogisticsPerson()))
                .filter(notActivatedLogisticsPerson -> !notActivatedLogisticsPerson.isActive()).toList();

        // Тут хранятся ID аккаунтов которые нужно удалить
        List<Long> longList = new ArrayList<>();

        // Получаем ID
        entities2.forEach(notActivatedLogisticsPerson -> longList.add(notActivatedLogisticsPerson.getLogisticsPerson().getId()));

        // Сюда присваиваем все аккаунты которые нужно удалить
        List<LogisticsPerson> logisticsPersonList =  serviceJpa.findAllLogisticsPersonById(longList);


        if (!entities1.isEmpty()){
            serviceJpa.deleteAllLogisticsPerson(entities1);
        } else if (!entities2.isEmpty()){
            serviceJpa.deleteListLogisticsPerson(logisticsPersonList);
        }



    }

}
