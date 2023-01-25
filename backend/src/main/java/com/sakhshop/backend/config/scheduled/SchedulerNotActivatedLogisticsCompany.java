package com.sakhshop.backend.config.scheduled;

import com.sakhshop.backend.models.activation.NotActivatedLogisticsCompany;
import com.sakhshop.backend.models.logistics.company.LogisticsCompany;
import com.sakhshop.backend.service.jpa.ServiceJpa;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchedulerNotActivatedLogisticsCompany {


    private final
    ServiceJpa serviceJpa;


    public SchedulerNotActivatedLogisticsCompany(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }



    //        @Scheduled(fixedDelay = 86_400_000) // 1 день 86_400_000
    @Scheduled(cron = "0 0 0/23 * * *") // Каждый день в 23 часа "0 0 0/23 * * *"
    public void deleteNotActivatedLogisticsCompany() {

        // Тут хранятся все записи NotActivatedLogisticsCompany
        List<NotActivatedLogisticsCompany> notActivatedLogisticsCompanys = new ArrayList<>();

        // Тут получаем все запись из базы данных NotActivatedLogisticsCompany
        serviceJpa.findAllDateDeletionLogisticsCompany().forEach(notActivatedLogisticsCompanys::add);

        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить
        List<NotActivatedLogisticsCompany> entities1 = notActivatedLogisticsCompanys
                .stream()
                .filter(NotActivatedLogisticsCompany::isActive).toList();


        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить вместе с аккаунтом
        List<NotActivatedLogisticsCompany> entities2 = notActivatedLogisticsCompanys
                .stream()
                .filter(notActivatedLogisticsCompany -> Instant.now().isAfter(notActivatedLogisticsCompany.getDateDeletionLogisticsCompany()))
                .filter(notActivatedLogisticsCompany -> !notActivatedLogisticsCompany.isActive()).toList();

        // Тут хранятся ID аккаунтов которые нужно удалить
        List<Long> longList = new ArrayList<>();

        // Получаем ID
        entities2.forEach(notActivatedLogisticsCompany -> longList.add(notActivatedLogisticsCompany.getLogisticsCompany().getId()));

        // Сюда присваиваем все аккаунты которые нужно удалить
        List<LogisticsCompany> logisticsCompanyList =  serviceJpa.findAllLogisticsCompanyById(longList);


        if (!entities1.isEmpty()){
            serviceJpa.deleteAllLogisticsCompany(entities1);
        } else if (!entities2.isEmpty()){
            serviceJpa.deleteListLogisticsCompany(logisticsCompanyList);
        }



    }


}
