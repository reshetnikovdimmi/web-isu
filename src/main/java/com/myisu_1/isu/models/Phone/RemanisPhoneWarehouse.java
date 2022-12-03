package com.myisu_1.isu.models.Phone;

public class RemanisPhoneWarehouse {

    String modelPhone;
    int remanisMainWarehouse;
    int remanisWarehouseT2;

    public RemanisPhoneWarehouse(String modelPhone, int remanisMainWarehouse, int remanisWarehouseT2) {
        this.modelPhone = modelPhone;
        this.remanisMainWarehouse = remanisMainWarehouse;
        this.remanisWarehouseT2 = remanisWarehouseT2;
    }

    public RemanisPhoneWarehouse() {
    }

    public String getModelPhone() {
        return modelPhone;
    }

    public void setModelPhone(String modelPhone) {
        this.modelPhone = modelPhone;
    }

    public int getRemanisMainWarehouse() {
        return remanisMainWarehouse;
    }

    public void setRemanisMainWarehouse(int remanisMainWarehouse) {
        this.remanisMainWarehouse = remanisMainWarehouse;
    }

    public int getRemanisWarehouseT2() {
        return remanisWarehouseT2;
    }

    public void setRemanisWarehouseT2(int remanisWarehouseT2) {
        this.remanisWarehouseT2 = remanisWarehouseT2;
    }
}
