package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.SIM.RemanisSim;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.PostRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {
    @Autowired
    private PostRepositoriy postRepositoriy;

    @GetMapping("/api")
       public List<authorization_tt> shop(){
        List<authorization_tt> authorization_tt_list = (List<authorization_tt>) postRepositoriy.findAll();
        List<authorization_tt> login = new ArrayList<>();
        for (int i = 0;i<authorization_tt_list.size();i++){
            login.add(new authorization_tt(
                    authorization_tt_list.get(i).getId(),
                    authorization_tt_list.get(i).getLogin(),
                    authorization_tt_list.get(i).getName(),
                    authorization_tt_list.get(i).getClusterT2(),
                    authorization_tt_list.get(i).getClusterRtk(),
                    authorization_tt_list.get(i).getSimT2(),
                    authorization_tt_list.get(i).getSimMts(),
                    authorization_tt_list.get(i).getSimBee(),
                    authorization_tt_list.get(i).getSimMf(),
                    authorization_tt_list.get(i).getShopIskra(),
                    authorization_tt_list.get(i).getShopRarus()));
        }
        System.out.println("kj");
        return login;
    }
   @PostMapping(path = "/api/save")
    private List<authorization_tt> simos(@RequestParam String login, @RequestParam String password) {
        List<authorization_tt> log = new ArrayList<>();
        authorization_tt logins = new authorization_tt();
        logins.setLogin(login);
        log.add(logins);
        System.out.println("requestBody");

        return log;
    }
}
