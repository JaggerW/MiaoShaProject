package com.nju.miaosha.service;

import com.nju.miaosha.domain.OrderInfoDO;
import com.nju.miaosha.domain.UserDO;
import com.nju.miaosha.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: demo
 * @description: 秒杀服务类
 * @author: JaggerW
 * @create: 2020-05-26 19:21
 */
@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    public OrderInfoDO miaosha(UserDO user, GoodsVO goodsVO){

        return orderService.createOrder(user,goodsVO);
    }

}
