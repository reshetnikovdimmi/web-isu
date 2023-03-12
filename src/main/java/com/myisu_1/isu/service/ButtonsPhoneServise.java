package com.myisu_1.isu.service;

import ch.qos.logback.core.joran.action.DefinePropertyAction;
import com.myisu_1.isu.models.Phone.Buttons;
import com.myisu_1.isu.repo.ButtonsPhoneRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ButtonsPhoneServise {
    Buttons button;
    @Autowired
    private ButtonsPhoneRepositoriy buttonsPhoneRepositoriy;


    public List<Buttons> findAllButtonsPhone() {


        return buttonsPhoneRepositoriy.getButtonPhonePrice();
    }

    public Map<String,Map<String,String>> graduationPhone() {
        int[] arrayGraduation = {1000, 2000, 3000, 4000, 5000, 6000};
        List<Buttons> buttonsList = buttonsPhoneRepositoriy.getButtonPhonePrice();
        List<String> modelsGraduation = buttonsPhoneRepositoriy.getModelsGraduation();
        button = new Buttons();

        button.graduationButton = new TreeMap<>();

        for (Integer graduation : arrayGraduation) {
            for (String modelGraduation : modelsGraduation) {
              //  System.out.println(modelGraduation+"--"+graduation);
                List<String> model = new ArrayList<>();

                for (Buttons buttonList : buttonsList) {

                    if (buttonList.getPrice()!=null && modelGraduation.equals(buttonList.getBrend()) && graduation >= Double.parseDouble(buttonList.getPrice())&& graduation - 1000 < Double.parseDouble(buttonList.getPrice())) {
                     //   System.out.println(buttonList.getModel()+"--"+buttonList.getPrice());

                        model.add(buttonList.getModel());
                    }
                }
                button.graduationButton.put(modelGraduation+"--"+graduation,model);
            }
        }
       // network balance
        Map<String,Map<String,String>> networkBalance = new TreeMap<>();
        for (Map.Entry entry: button.graduationButton.entrySet()) {

            //System.out.println(entry.getKey()+"<-->"+entry.getValue());
            networkBalance.put((String)entry.getKey(),networkBalance((List<String>)entry.getValue()));
        }
       //
      //  System.out.println(button.graduationButton);
        return  networkBalance;
    }

    private Map<String, String> networkBalance(List<String> value) {
        Map<String, String> networkBalance = new TreeMap<>();
        networkBalance.put("Remanis",buttonsPhoneRepositoriy.getRemanisButton(value));
        networkBalance.put("Sale 6_3m",buttonsPhoneRepositoriy.getSale6mButton(value));
        networkBalance.put("Sale 1m",buttonsPhoneRepositoriy.getSale1mButton(value));
        return networkBalance;
    }

    public Object tableShopRemanis(String brendPhone) {

        return null;
    }
}
