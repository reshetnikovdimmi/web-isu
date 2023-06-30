package com.myisu_1.isu.service;

import com.myisu_1.isu.repo.CollectionScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MainServise {
    @Autowired
    private CollectionScheduleRepository collectionScheduleRepository;
    List<String> result;

    public List<String> incassationTomorrow() {
        if (result==null) {
            new ArrayList<>();
            Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);

            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);


            switch (dayOfWeek) {
                case 2:
                    result = collectionScheduleRepository.incassationTuesday();
                    break;

                case 3:
                    result = collectionScheduleRepository.incassationWednesday();
                    break;
                case 4:
                    result = collectionScheduleRepository.incassationThursday();

                    break;
                case 5:
                    result = collectionScheduleRepository.incassationFriday();

                    break;

                case 6:
                    result = collectionScheduleRepository.incassationMonday();

                    break;
                default:
                    break;
            }
        }
        return result;
    }

}

