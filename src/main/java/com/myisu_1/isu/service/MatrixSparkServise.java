package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Phone.MatrixSpark;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.Sales;
import com.myisu_1.isu.repo.MatrixSparkRepository;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.SalesRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MatrixSparkServise {
    @Autowired
    private MatrixSparkRepository matrixSparkRepository;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private SalesRepositoriy salesRepositoriy;

    public Iterable<MatrixSpark> matrixSparktable() {

        return matrixSparkRepository.findAll();
    }

    public Iterable<MatrixSpark> matrixSparkTableUpdate() {
        List<MatrixSpark> matrixSparkList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        List<Sales> matrixSpark = (List<Sales>) salesRepositoriy.findAll();
        List<Phone_Smart> phoneSmartList = phoneRepositoriy.findAll();

        HashSet<String> hashDistinct = new HashSet<>();
        for (int i = 0; i < phoneSmartList.size(); i++) {
            hashDistinct.add(phoneSmartList.get(i).getModel_GB());
        }


        for (int i = 0; i < matrixSpark.size(); i++) {
            list.add(matrixSpark.get(i).getNomenclature());
        }

        Map<String, Long> frequency =
                list.stream().collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()));

        Iterator<String> i = hashDistinct.iterator();
        while (i.hasNext()) {
            long summ = 0;
            String iter = i.next();

            for (int j = 0; j < phoneSmartList.size(); j++) {
                for (Map.Entry<String, Long> item : frequency.entrySet()) {
                    if (phoneSmartList.get(j).getModel().equals(item.getKey()) && iter.equals(phoneSmartList.get(j).getModel_GB())) {
                        summ = summ + item.getValue();
                    }
                }
            }
            matrixSparkList.add(new MatrixSpark(iter, String.valueOf(summ)));
        }
        Collections.sort(matrixSparkList, new Comparator<MatrixSpark>() {
            @Override
            public int compare(MatrixSpark o1, MatrixSpark o2) {
                return Integer.parseInt(o2.getSale()) - Integer.parseInt(o1.getSale()  );
            }

        });

        return matrixSparkList;
    }

    public Object saveSparkSale(List<MatrixSpark> sim) {
        matrixSparkRepository.deleteAll();
        matrixSparkRepository.saveAll(sim);
        return  matrixSparkRepository.findAll();
    }
}
