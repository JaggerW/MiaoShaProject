package com.nju.miaosha.controller;

import com.nju.miaosha.domain.MiaoshaOrderDO;
import com.nju.miaosha.domain.OrderInfoDO;
import com.nju.miaosha.domain.UserDO;
import com.nju.miaosha.exception.CodeMsg;
import com.nju.miaosha.service.GoodsService;
import com.nju.miaosha.service.MiaoshaService;
import com.nju.miaosha.service.OrderService;
import com.nju.miaosha.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: demo
 * @description: 秒杀控制器
 * @author: JaggerW
 * @create: 2020-05-24 13:24
 */

@Controller
@RequestMapping(value = "/miaosha")
public class MiaoshaController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MiaoshaService miaoshaService;


    public String addOrder(Model model, UserDO user, @RequestParam("goodsId") long goodsId){
        model.addAttribute("user", user);
        if(user == null){
            return "login";
        }
        //判断库存
        GoodsVO goodsVO = goodsService.getGoodsVOByGoodsId(goodsId);
        int stock = goodsVO.getGoodsStock();
        if(stock <= 0){
            model.addAttribute("errmsg", CodeMsg.SERVER_ERROR.getMsg());
            return "miaosha_fail";
        }
        //判断是否重复下单
        MiaoshaOrderDO order = orderService.getMiaoshaOrderByUserIdAndGoodsId(user.getUserId(),goodsId);
        if(order != null){
            model.addAttribute("errmsg", CodeMsg.SERVER_ERROR);
            return "miaosha_fail";
        }
        //减库存，下订单，写入秒杀订单
        OrderInfoDO orderInfoDO = miaoshaService.miaosha(user,goodsVO);
        model.addAttribute("orderInfo", orderInfoDO);
        model.addAttribute("goods",goodsVO);
        return "order_detail";

    }

}
