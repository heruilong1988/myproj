package com.hrl.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public  class  OrderDepth {

    private Integer id;
    private Date time;
    private BigDecimal bidPrice_100;
    private BigDecimal bidAmount_100;
    private BigDecimal askPrice_100;
    private BigDecimal askAmount_100;
    private String base;
    private String target;

    private BigDecimal bidPrice_500;
    private BigDecimal bidAmount_500;
    private BigDecimal askPrice_500;
    private BigDecimal askAmount_500;
}
