package com.mmj.user.common.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 订单商品信息
 */
@Data
@EqualsAndHashCode()
@Accessors(chain = true)
public class OrderGoodsDto {
    private Integer goodId;
    private String goodSku;
    private Integer saleId;
    private String goodTitle;
    private String goodImage;
    private Integer goodNum;
    private String modelName; //  规格名称
    private String createTime;


    private String unitPrice;     // 单价
    private String originalPrice;  // 原价
    @JsonProperty("memberprice")
    @JSONField(name = "memberprice")
    private String memberPrice;    // 会员价
    private String goodAmount;   //  商品原价
    private String virtualGoodsFlag;  // 虚拟商品 0 否 1 是
    private String couponAmount;   // 优惠总金额
    private String couponPrice;   //  优惠单件金额（优惠总金/商品数量,）
    private String discountAmount;  // 活动优惠价金额
    private String goodTotalPrice;    // 商品总价

    private String passingData; // 虚拟商品快照


}
