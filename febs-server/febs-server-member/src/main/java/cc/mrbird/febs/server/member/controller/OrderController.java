package cc.mrbird.febs.server.member.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.member.Order;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.server.member.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * 订单表 Controller
 *
 * @author zlczhou
 * @date 2019-12-23 11:59:20
 */
@Slf4j
@Validated
@RestController
@RequestMapping("order")
public class OrderController{

    @Autowired
    private IOrderService orderService;

    @GetMapping
    @PreAuthorize("hasAuthority('order:list')")
    public FebsResponse getAllOrders(Order order) {
        return new FebsResponse().data(orderService.findOrders(order));
    }

    @GetMapping("list")
    @PreAuthorize("hasAuthority('order:view')")
    public FebsResponse orderList(QueryRequest request, Order order) {
        Map<String, Object> dataTable = FebsUtil.getDataTable(this.orderService.findOrders(request, order));
        return new FebsResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('order:add')")
    @ControllerEndpoint(operation = "新增Order", exceptionMessage = "新增Order失败")
    public void addOrder(@Valid Order order) throws FebsException {
        this.orderService.createOrder(order);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('order:delete')")
    @ControllerEndpoint(operation = "删除Order", exceptionMessage = "删除Order失败")
    public void deleteOrder(Order order) throws FebsException {
        this.orderService.deleteOrder(order);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('order:update')")
    @ControllerEndpoint(operation = "修改Order", exceptionMessage = "修改Order失败")
    public void updateOrder(Order order) throws FebsException {
        this.orderService.updateOrder(order);
    }
}
