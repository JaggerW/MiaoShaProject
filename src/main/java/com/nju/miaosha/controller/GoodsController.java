package com.nju.miaosha.controller;

import com.nju.miaosha.domain.UserDO;
import com.nju.miaosha.service.GoodsService;
import com.nju.miaosha.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @program: demo
 * @description: 商品列表页
 * @author: JaggerW
 * @create: 2020-05-18 16:46
 */

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {


    @Autowired
    private GoodsService goodsService;

    @GetMapping(value = "/index.view")
    public String goodsList(Model model, UserDO user){
        //拿到request中token参数或者cookie中的token，通过token获取用户信息
        //上一步在ArgumentResolver已处理，因此可以直接拿到user

        model.addAttribute("user", user);
        if(user == null){
            return "login";
        }
        //查询商品列表
        List<GoodsVO> goodsList = goodsService.getGoodsVOList();
        model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }

    @GetMapping("/detail.view/{goodsId}")
    public String detail(Model model, UserDO user, @PathVariable("goodsId") long goodsId){
        model.addAttribute("user",user);

        if(user == null){
            return "login";
        }

        GoodsVO goodsVO = goodsService.getGoodsVOByGoodsId(goodsId);
        model.addAttribute("goods",goodsVO);

        long startAt = goodsVO.getStartDate().getTime();
        long endAt = goodsVO.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
        int remainSeconds = 0;

        if(now < startAt){
            miaoshaStatus = 0; //秒杀尚未开始
            remainSeconds = (int) ((startAt-now) / 1000);
        }else if(now > endAt){
            miaoshaStatus = 2; //秒杀已结束
            remainSeconds = -1;
        }else{
            miaoshaStatus = 1; //秒杀进行中
            remainSeconds = 0;
        }

        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSeconds",remainSeconds);
        return "goods_detail";


    }

}
