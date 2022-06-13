package com.myisu_1.isu.models.Storage;

import com.myisu_1.isu.models.Phone_Smart;

import java.awt.print.Book;
import java.util.List;


public class BrendCreationDto {
    private List<Phone_Smart> phones;

    public BrendCreationDto(List<Phone_Smart> phones) {
        this.phones = phones;
    }

    public BrendCreationDto() {
    }


    public void addPhone(Phone_Smart phone) {
        this.phones.add(phone);
    }

    public List<Phone_Smart> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone_Smart> phones) {
        this.phones = phones;
    }
}
