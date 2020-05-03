package cc.mrbird.febs.server.member.service.impl;



import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.member.Order;
import cc.mrbird.febs.server.member.mapper.OrderMapper;
import cc.mrbird.febs.server.member.service.IOrderService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单表 Service实现
 *
 * @author zlczhou
 * @date 2019-12-23 11:59:20
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public IPage<Order> findOrders(QueryRequest request, Order order) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        if(order.getPaytype() != null){
            queryWrapper.eq(Order::getPaytype,order.getPaytype());
        }
        if(StringUtils.isNotBlank(order.getOrderid())){
            queryWrapper.likeRight(Order::getOrderid,order.getOrderid());
        }
        if(order.getMid() != null){
            queryWrapper.likeRight(Order::getMid,order.getMid());
        }
        if(order.getOtype() != null ){
            queryWrapper.eq(Order::getOtype,order.getOtype());
        }
        if(order.getStatus() != null){
            queryWrapper.eq(Order::getStatus,order.getStatus());
        }
        Page<Order> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Order> findOrders(Order order) {
	    LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createOrder(Order order) {
        this.save(order);
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        this.saveOrUpdate(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Order order) {
        LambdaQueryWrapper<Order> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
