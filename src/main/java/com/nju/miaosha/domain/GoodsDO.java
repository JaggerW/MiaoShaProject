package com.nju.miaosha.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: demo
 * @description: 商品POJO类
 * @author: JaggerW
 * @create: 2020-05-24 11:24
 */
@Data
public class GoodsDO implements Serializable {

    private static final long serialVersionUID = 4937951187037483159L;
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;

}
