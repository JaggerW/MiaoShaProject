package com.nju.miaosha.vo;

import com.nju.miaosha.domain.GoodsDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @program: demo
 * @description: 商品列表返回参数格式
 * @author: JaggerW
 * @create: 2020-05-24 11:17
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsVO extends GoodsDO {

    private static final long serialVersionUID = 1544203306910090169L;
    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

}
