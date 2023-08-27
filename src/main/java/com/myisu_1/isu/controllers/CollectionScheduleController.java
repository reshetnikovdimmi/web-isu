package com.myisu_1.isu.controllers;


import com.myisu_1.isu.models.CollectionSchedule;
import com.myisu_1.isu.repo.CollectionScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CollectionScheduleController {
    @Autowired
    private CollectionScheduleRepository collectionScheduleRepository;

    @GetMapping("/CollectionSchedule")
    public String collectionSchedule(Model model) {
        model.addAttribute("CollectionSchedule",  collectionScheduleRepository.findAll());
        return "CollectionSchedule";
    }
    @ResponseBody
    @RequestMapping(value = "update_CollectionSchedule/{id}", method = RequestMethod.GET)
    public Optional<CollectionSchedule> update(@PathVariable("id") int id) {
        return collectionScheduleRepository.findById(id);
    }
    @PostMapping("/add_CollectionSchedule")
    public String add_phone(@RequestParam int IDupdateSHOP,
                            @RequestParam String shop,
                            @RequestParam Boolean monday,
                            @RequestParam Boolean tuesday,
                            @RequestParam Boolean wednesday,
                            @RequestParam Boolean thursday,
                            @RequestParam Boolean friday,

                            Model model) {

        if (IDupdateSHOP != 0) {


            collectionScheduleRepository.save((new CollectionSchedule(IDupdateSHOP, shop, monday, tuesday, wednesday, thursday, friday)));

        } else {
            collectionScheduleRepository.save((new CollectionSchedule(shop, monday, tuesday, wednesday, thursday, friday)));
        }

        model.addAttribute("CollectionSchedule", collectionScheduleRepository.findAll());
        return "CollectionSchedule";
    }
    @PostMapping("/delet_CollectionSchedule")
    public String delet(@RequestParam int IDshop, Model model) {
        collectionScheduleRepository.deleteById(IDshop);
        model.addAttribute("CollectionSchedule", collectionScheduleRepository.findAll());
        return "CollectionSchedule";
    }
}
