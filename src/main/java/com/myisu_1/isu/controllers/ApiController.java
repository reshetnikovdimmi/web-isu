package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.models.price_promo;
import com.myisu_1.isu.repo.PostRepositoriy;
import com.myisu_1.isu.repo.PromoRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {
    @Autowired
    private PostRepositoriy postRepositoriy;
    @Autowired
    private PromoRepositoriy promoRepositoriy;
    List<authorization_tt> authorization_tt_list;
    @GetMapping("/api")
       public List<authorization_tt> shop(){
        authorization_tt_list = (List<authorization_tt>) postRepositoriy.findAll();
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
        for (int i=0;i<authorization_tt_list.size();i++){
          if (login.equals(authorization_tt_list.get(i).getLogin()) && password.equals(authorization_tt_list.get(i).getPasword())){
              logins.setLogin("success");
          }else{
              logins.setLogin("error");
          }
        }

        log.add(logins);


        return  log;
    }
    @PostMapping(path = "/api/test")
    private ResponseEntity<Iterable<authorization_tt>> simos() {



        return ResponseEntity.notFound().build();
    }
    @GetMapping(path = "/api/promo")
    private List<price_promo> promo() {



        return (List<price_promo>) promoRepositoriy.findAll();
    }
}
