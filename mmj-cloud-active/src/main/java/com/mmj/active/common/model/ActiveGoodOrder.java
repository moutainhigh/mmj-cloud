package com.mmj.active.common.model;

import java.util.List;

public class ActiveGoodOrder extends ActiveGood {

    private List<String> goodSkus;

    public List<String> getGoodSkus() {
        return goodSkus;
    }

    public void setGoodSkus(List<String> goodSkus) {
        this.goodSkus = goodSkus;
    }
}
