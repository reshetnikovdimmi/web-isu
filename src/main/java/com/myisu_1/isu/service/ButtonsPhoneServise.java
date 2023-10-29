package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.Phone.Buttons;
import com.myisu_1.isu.models.Phone.ButtonsPhone;
import com.myisu_1.isu.models.distribution.AnalysisDistribution;
import com.myisu_1.isu.repo.ButtonsPhoneRepositoriy;
import com.myisu_1.isu.repo.PostRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ButtonsPhoneServise extends AnalysisDistribution {
    Buttons button = new Buttons();
    @Autowired
    private ButtonsPhoneRepositoriy buttonsPhoneRepositoriy;
    @Autowired
    private PostRepositoriy authorizationRep;
    @Autowired
    public PostRepositoriy authorization_tt;

     public List<Buttons> findAllButtonsPhone() {
        return buttonsPhoneRepositoriy.getButtonPhonePrice();
    }

    public OrderRecommendations remainsCashGroup() {
        or = new OrderRecommendations();
        List<Buttons> buttons = buttonsPhoneRepositoriy.getButtonPhonePrice();

        List<ButtonsPhone> bpList = new ArrayList<>();
        for (Buttons b : buttons) {
            ButtonsPhone bp = new ButtonsPhone();
            bp.setGroup(searchGroup(b.getPrice(), b.getBrend()));
            bp.setBrend(b.getBrend());
            bp.setModel(b.getModel());
            bpList.add(bp);
        }
        buttonsPhoneRepositoriy.deleteAll();
        buttonsPhoneRepositoriy.saveAll(bpList);

        remainsNomenclature = buttonsPhoneRepositoriy.getRemainsShopButton();

        warehouse = authorizationRep.getShopList();
        sale1Nomenclature = buttonsPhoneRepositoriy.getSale1Phone();
        sale6Nomenclature = buttonsPhoneRepositoriy.getSale6Phone();
        phoneSmarts = buttonsPhoneRepositoriy.phoneSmar();
        remainsCashGroup(buttonsPhoneRepositoriy.getGroupView().stream().filter(r -> r.getGroup() != null).collect(Collectors.toList()));
        remainsNomenclatureSach(buttonsPhoneRepositoriy.getModelsGraduation());
        indicatorsPhoneShopGroup(buttonsPhoneRepositoriy.getGroupShop().stream().distinct().collect(Collectors.toList()), null);
        distributionPhone(buttonsPhoneRepositoriy.getGroupView());
        return or;
    }

    private String searchGroup(String price, String brend) {

        if (price != null) {
            if (Double.valueOf(price) <= 1000) return brend + "--" + "1000";
            if (Double.valueOf(price) <= 2000) return brend + "--" + "2000";
            if (Double.valueOf(price) <= 3000) return brend + "--" + "3000";
            if (Double.valueOf(price) <= 4000) return brend + "--" + "4000";
            if (Double.valueOf(price) <= 5000) return brend + "--" + "5000";
            if (Double.valueOf(price) <= 6000) return brend + "--" + "6000";
        }

        return null;
    }

    public Object remanisButtonShop() {

        or.setRemanisPhoneShopMult(remainsSaleShopAll(authorization_tt.getShopList(), null));
        return or.getRemanisPhoneShopMult();
    }


    public List<OrderRecommendations> tableShopRemanis(String shop) {
        return or.getDistributionPhone().stream().filter(r -> r.getShop().equals(shop)).collect(Collectors.toList());
    }

    public OrderRecommendations tableShopRemanisSele(String brendPhone) {
        return or;
    }


    public OrderRecommendations exselDistributionButto() {

        return or;
    }


    public OrderRecommendations distribution(OrderRecommendations order) {
        System.out.println(order);
        distributions(order,authorization_tt.getShopMult(),null);
        return or;
    }
}
