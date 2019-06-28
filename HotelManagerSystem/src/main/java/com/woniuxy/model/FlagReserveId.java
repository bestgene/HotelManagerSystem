package com.woniuxy.model;

public class FlagReserveId {
    private Integer flag;
    private Integer reserve_id;

    @Override
    public String toString() {
        return "FlagReserveId{" +
                "flag=" + flag +
                ", reserve_id=" + reserve_id +
                '}';
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getReserve_id() {
        return reserve_id;
    }

    public void setReserve_id(Integer reserve_id) {
        this.reserve_id = reserve_id;
    }
}
