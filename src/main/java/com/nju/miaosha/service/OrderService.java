package com.nju.miaosha.service;

import com.nju.miaosha.dao.OrderMapper;
import com.nju.miaosha.domain.MiaoshaOrderDO;
import com.nju.miaosha.domain.OrderInfoDO;
import com.nju.miaosha.domain.UserDO;
import com.nju.miaosha.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @program: demo
 * @description: 订单服务类
 * @author: JaggerW
 * @create: 2020-05-26 18:25
 */
@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    public MiaoshaOrderDO getMiaoshaOrderByUserIdAndGoodsId(long useId, long goodsId){
        return orderMapper.getMiaoshaOrderByUserIdAndGoodsId(useId,goodsId);
    }

    @Transactional
    public OrderInfoDO createOrder(UserDO user, GoodsVO goodsVO){
        OrderInfoDO orderInfoDO = new OrderInfoDO();
        orderInfoDO.setCreateDate(new Date());
        orderInfoDO.setDeliveryAddrId(0L);
        orderInfoDO.setGoodsCount(1);
        orderInfoDO.setGoodsId(goodsVO.getId());
        orderInfoDO.setGoodsName(goodsVO.getGoodsName());
        orderInfoDO.setGoodsPrice(goodsVO.getMiaoshaPrice());
        orderInfoDO.setOrderChannel(1);
        orderInfoDO.setStatus(0);
        orderInfoDO.setUserId(user.getUserId());
        long orderId = orderMapper.insert(orderInfoDO);
        MiaoshaOrderDO miaoshaOrderDO = new MiaoshaOrderDO();
        miaoshaOrderDO.setGoodsId(goodsVO.getId());
        miaoshaOrderDO.setOrderId(orderId);
        miaoshaOrderDO.setUserId(user.getUserId());
        orderMapper.insertMiaoshaOrder(miaoshaOrderDO);
        return orderInfoDO;
    }

}
