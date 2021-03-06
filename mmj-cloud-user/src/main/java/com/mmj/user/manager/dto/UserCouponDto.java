package com.mmj.user.manager.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description: 用户优惠券
 * @auther: KK
 * @date: 2019/7/11
 */
@Data
@ApiModel(value = "用户优惠券", description = "用户优惠券")
public class UserCouponDto {
    @ApiModelProperty(value = "优惠券编码")
    private Integer couponCode;

    @ApiModelProperty(value = "优惠券ID")
    private Integer couponId;

    @ApiModelProperty(value = "优惠券来源 SYSTEM：系统发送，TOPIC：专题页领取，BRUSH：刷一刷，INDEX：弹出优惠券领用")
    private String couponSource;

    @ApiModelProperty(value = "是否使用 0未使用 1已使用")
    private Integer usedFlag;

    @ApiModelProperty(value = "是否有效 0有效 1无效 2未生效")
    private Integer validStatus;

    @ApiModelProperty(value = "优惠券信息")
    private CouponInfoDataDto couponInfo;

    @ApiModelProperty(value = "有效期开始时间")
    private Date startTime;

    @ApiModelProperty(value = "有效期结束时间")
    private Date endTime;

}
