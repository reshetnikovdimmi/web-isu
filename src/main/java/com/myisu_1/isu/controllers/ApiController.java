package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.Authorization_tt;
import com.myisu_1.isu.models.price_promo;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {
    @Autowired
    private PostRepositoriy postRepositoriy;
    @Autowired
    private RemanisSimRepository remanisSimrepository;
    @Autowired
    private SimAndRtkTableRepositoriy simAndRtkTableRepositoriy;
    @Autowired
    private SaleSimModemRepository_6m saleSimModemRepository6m;
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository_1m;
    @Autowired
    private PromoRepositoriy promoRepositoriy;
    @Autowired
    private MatrixRTKRepository matrixRTKRepository;

    List<Authorization_tt> authorization_tt_list;

    @GetMapping("/api")
    public List<Authorization_tt> shop() {
        authorization_tt_list = (List<Authorization_tt>) postRepositoriy.findAll();
        List<Authorization_tt> login = new ArrayList<>();
        for (int i = 0; i < authorization_tt_list.size(); i++) {
            login.add(new Authorization_tt(
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
        System.out.println("login");
        return login;
    }



    @PostMapping(path = "/api/save")
    private List<Authorization_tt> simos(@RequestParam String login, @RequestParam String password) {
        List<Authorization_tt> log = new ArrayList<>();
        Authorization_tt logins = new Authorization_tt();
        for (int i = 0; i < authorization_tt_list.size(); i++) {
            if (login.equals(authorization_tt_list.get(i).getLogin()) && password.equals(authorization_tt_list.get(i).getPasword())) {
                logins.setLogin("success");
            } else {
                logins.setLogin("error");
            }
        }

        log.add(logins);

        return log;
    }

    @GetMapping(path = "/api/promo")
    private List<price_promo> promo() {

        return (List<price_promo>) promoRepositoriy.findAll();
    }


    @PostMapping(path = "/api/test")
    private ResponseEntity<Iterable<Authorization_tt>> simos() {

        return ResponseEntity.notFound().build();
    }

}
