package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.CollectionSchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollectionScheduleRepository extends JpaRepository<CollectionSchedule, Integer> {
    @Query("SELECT shop FROM CollectionSchedule WHERE thursday = 1")
    List<String> incassationThursday();
    @Query("SELECT shop FROM CollectionSchedule WHERE tuesday = 1")
    List<String> incassationTuesday();
    @Query("SELECT shop FROM CollectionSchedule WHERE wednesday = 1")
    List<String> incassationWednesday();
    @Query("SELECT shop FROM CollectionSchedule WHERE monday = 1")
    List<String> incassationMonday();
    @Query("SELECT shop FROM CollectionSchedule WHERE friday = 1")
    List<String> incassationFriday();
}
