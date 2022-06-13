package com.myisu_1.isu.models.Storage;



import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PhoneDAO {

    @Autowired
     PhoneRepositoriy phoneRepositoriy;

    public static List<Phone_Smart> phones;
    {
        initData();
    }
    private void initData() {

        /*Phone_Smart vn = new Phone_Smart(1,  "Vietnam", "niknk", "hjjkvkjvh", "kjguyui");
        Phone_Smart en = new Phone_Smart(2,    "Vietnam1", "niknk1", "hjjkvkjvh1", "kjguyui1");
        Phone_Smart ru = new Phone_Smart(3,    "Vietnam2", "niknk2", "hjjkvkjvh2", "kjguyui2");
        phones.add(en);
        phones.add(vn);
        phones.add(ru);*/

//        phones = (List<Phone_Smart>) phoneRepositoriy.findAll();


    }
    public static List<Phone_Smart> getCountries() {
        return phones;
    }
    public Map<Integer, String> getMapCountries() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (Phone_Smart c : phones) {
            map.put(c.getId(), c.getBrend());
        }
        return map;
    }


}
