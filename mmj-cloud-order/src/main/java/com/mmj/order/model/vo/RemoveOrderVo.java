package com.mmj.order.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


@Data
@EqualsAndHashCode()
@Accessors(chain = true)
public class RemoveOrderVo {


    private String userId;

    @NotNull
    private String orderNo;
}
