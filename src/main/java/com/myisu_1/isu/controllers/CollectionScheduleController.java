package com.myisu_1.isu.controllers;


import com.myisu_1.isu.repo.CollectionScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class CollectionScheduleController {
    @Autowired
    private CollectionScheduleRepository collectionScheduleRepository;

    @GetMapping("/CollectionSchedule")
    public String collectionSchedule(Model model) {

        return "CollectionSchedule";
    }
}
