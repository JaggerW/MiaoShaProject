package com.nju.miaosha.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: demo
 * @description: 订单详细信息
 * @author: JaggerW
 * @create: 2020-05-26 18:11
 */
@Data
public class OrderInfoDO implements Serializable {
    private static final long serialVersionUID = 8967775941056560935L;
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long  deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;

}
