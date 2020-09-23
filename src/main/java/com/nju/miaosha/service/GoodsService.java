package com.nju.miaosha.service;

import com.nju.miaosha.dao.GoodsMapper;
import com.nju.miaosha.domain.MiaoshaGoodsDO;
import com.nju.miaosha.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: demo
 * @description: 商品服务类
 * @author: JaggerW
 * @create: 2020-05-24 11:21
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<GoodsVO> getGoodsVOList() {
        return goodsMapper.getGoodsVOList();
    }

    public GoodsVO getGoodsVOByGoodsId(long goodsId) {
        return goodsMapper.getGoodsVOByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVO goodsVO){
        MiaoshaGoodsDO miaoshaGoodsDO = new MiaoshaGoodsDO();
        miaoshaGoodsDO.setGoodsId(goodsVO.getId());
        goodsMapper.reduceStock(miaoshaGoodsDO);
    }
}
