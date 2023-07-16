package com.myisu_1.isu.models.bonuses;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.List;

public interface CheckingLists {
        default List<String> aMinusB (List<String> a, List<String> b){
        Collection<String> aMinusB = CollectionUtils.subtract(a, b);
        return (List<String>) aMinusB;
    }

}
