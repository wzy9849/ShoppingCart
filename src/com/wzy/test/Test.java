package com.wzy.test;

import com.wzy.entity.Goods;
import com.wzy.entity.Order;
import com.wzy.entity.OrderItem;
import com.wzy.entity.UserInfo;
import com.wzy.factory.ObjectFactory;
import com.wzy.services.GoodsService;
import com.wzy.services.OrderItemService;
import com.wzy.services.OrderService;
import com.wzy.services.UserInfoService;
import com.wzy.services.impl.GoodsServiceImpl;
import com.wzy.services.impl.OrderItemServiceImpl;
import com.wzy.services.impl.OrderServiceImpl;
import com.wzy.services.impl.UserInfoServiceImpl;
import com.wzy.utils.DBUtils;
import com.wzy.utils.OrderNoUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException, InterruptedException {

//        System.out.println(OrderNoUtils.generateUniqueNo());
//        System.out.println(OrderNoUtils.generateUniqueNo());
        UserInfoService userInfoService = new UserInfoServiceImpl();

        GoodsService goodsService = new GoodsServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        OrderItemService orderItemService = new OrderItemServiceImpl();


//
//        UserInfoService userInfoService = (UserInfoService) ObjectFactory.getObject("userInfoService");
        System.out.println(userInfoService.queryUserInfoByUsername("wzy"));;
        System.out.println(userInfoService);


       /* System.out.println(userInfoService.queryUserInfoById(1));
        System.out.println(userInfoService.queryUserInfoByUsername("wzy"));*/

//        Date date = new Date();
//        System.out.println(date);
//
//        Thread.sleep(5000);
//
//        String dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        System.out.println(dateFormat);


//        Order order = new Order("test1", userInfoService.queryUserInfoByUsername("wzy"), (float) 15.6, new Date());
//        System.out.println(order);

//        System.out.println(orderService.addOrder(order));
//        Order order1 = orderService.queryOrderByOrderNo("test1");
//        System.out.println(order1);

//        Goods goods1 = goodsService.queryGoodsById(1);
//        Goods goods2 = goodsService.queryGoodsById(2);
//
//        OrderItem orderItem1 = new OrderItem(goods1, 2, (float) 9.0);
//        OrderItem orderItem2 = new OrderItem(goods2, 1, (float) 4.5);
//        orderItem1.setOrder(order1);
//        orderItem2.setOrder(order1);
//
//
//        orderItemService.addOrderItem(orderItem1);
//        orderItemService.addOrderItem(orderItem2);

//        System.out.println(orderItemService.queryOrderItemByOrderId(1));

//        System.out.println(orderService.queryOrderByOrderId(1));
//
//        System.out.println(orderItemService.queryOrderItemByOrderId(1));
//
//        UserInfo userInfo = userInfoService.queryUserInfoByUsername("wzy");
//        List<Order> orderList = orderService.queryHistoryOrder(userInfo);
//        System.out.println(orderList);


//
//        System.out.println("queryAllGoods");
//        GoodsService goodsService = new GoodsServiceImpl();
//        List<Goods> goods = goodsService.queryAllGoods();
//        System.out.println(goods);
//
//        System.out.println(goodsService.queryAllGoods());
//        System.out.println(goodsService.queryGoodsById(1));
//        System.out.println(goodsService.queryGoodsById(2));
//        System.out.println(goodsService.queryGoodsById(1));


        /*
        select		o.id										"o.id",
					u.id										"u.id",
					p.id										"p.id",
					i.id										"i.id",
					u.username									"u.username",
			   		o.no										"o.no",
			   		p.name										"p.name",
			   		i.num										"i.num",
			   		i.price										"i.price",
			   		o.price										"o.price",
			   		(select count(1)
			   		 from t_item item
			   		 where item.order_id=o.id)					"o.count"
		from       	t_order										o
		left join	t_user										u
			on		o.user_id=u.id
		left join	t_item										i
			on		i.order_id=o.id
		left join	t_product									p
			on		i.product_id=p.id
		where		o.id=#{id}
		*/

    }


}
