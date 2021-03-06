package cc.mrbird.febs.server.member.service;


import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.member.Order;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 订单表 Service接口
 *
 * @author zlczhou
 * @date 2019-12-23 11:59:20
 */
public interface IOrderService extends IService<Order> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param order order
     * @return IPage<Order>
     */
    IPage<Order> findOrders(QueryRequest request, Order order);

    /**
     * 查询（所有）
     *
     * @param order order
     * @return List<Order>
     */
    List<Order> findOrders(Order order);

    /**
     * 新增
     *
     * @param order order
     */
    void createOrder(Order order);

    /**
     * 修改
     *
     * @param order order
     */
    void updateOrder(Order order);

    /**
     * 删除
     *
     * @param order order
     */
    void deleteOrder(Order order);
}
