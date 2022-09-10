package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.PostRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {
    @Autowired
    private PostRepositoriy postRepositoriy;
    @GetMapping("/api")
    public List<authorization_tt> shop(){

        return (List<authorization_tt>) postRepositoriy.findAll();
    }

}
