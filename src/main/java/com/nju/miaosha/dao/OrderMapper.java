package com.nju.miaosha.dao;

import com.nju.miaosha.domain.MiaoshaOrderDO;
import com.nju.miaosha.domain.OrderInfoDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderMapper {

    MiaoshaOrderDO getMiaoshaOrderByUserIdAndGoodsId(@Param("userId") long userId,
                                                     @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id, goods_id, goods_price, order_channel, status, create_date)" +
            "value(#{userId},#{goodsId},#{goodsPrice},#{orderChannel},#{status},#{createDate})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
    long insert(OrderInfoDO orderInfoDO);

    @Insert("insert into miaosha_order (user_id, goods_id,order_id)" +
            "values(#{userId},#{goodsId},#{orderId})")
    int insertMiaoshaOrder(MiaoshaOrderDO miaoshaOrderDO);

}
