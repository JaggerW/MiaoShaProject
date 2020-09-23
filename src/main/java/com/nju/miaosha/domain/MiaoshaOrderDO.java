package com.nju.miaosha.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: demo
 * @description: 秒杀订单POJO类
 * @author: JaggerW
 * @create: 2020-05-26 18:07
 */
@Data
public class MiaoshaOrderDO implements Serializable {
    private static final long serialVersionUID = 746091383162248436L;
    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;

}
