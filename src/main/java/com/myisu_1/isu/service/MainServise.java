package com.myisu_1.isu.service;

import com.myisu_1.isu.repo.CollectionScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class MainServise {
    @Autowired
    private CollectionScheduleRepository collectionScheduleRepository;

    List<String> result;

    public List<String> incassationTomorrow() {
        if (result==null) {
            new ArrayList<>();



            LocalDateTime date = LocalDateTime.now(ZoneId.of("+06:00"));
            DayOfWeek day = date.getDayOfWeek();
            System.out.println(day.getValue());

                       switch (day.getValue()) {
                case 1:
                    result = collectionScheduleRepository.incassationTuesday();
                    break;
                case 2:
                    result = collectionScheduleRepository.incassationWednesday();
                    break;
                case 3:
                    result = collectionScheduleRepository.incassationThursday();
                    break;
                case 4:
                    result = collectionScheduleRepository.incassationFriday();
                    break;
                case 5:
                    result = collectionScheduleRepository.incassationMonday();
                    break;
                default:
                    break;
            }
        }
        return result;
    }

}

