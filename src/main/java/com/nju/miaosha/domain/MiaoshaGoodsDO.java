package com.nju.miaosha.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: demo
 * @description: 秒杀商品信息
 * @author: JaggerW
 * @create: 2020-05-26 18:13
 */
@Data
public class MiaoshaGoodsDO implements Serializable {

    private static final long serialVersionUID = -4633506475851601599L;
    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

}
