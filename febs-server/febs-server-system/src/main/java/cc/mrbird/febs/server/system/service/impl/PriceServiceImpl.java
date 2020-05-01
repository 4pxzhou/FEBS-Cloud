package cc.mrbird.febs.server.system.service.impl;


import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.system.Price;
import cc.mrbird.febs.server.system.mapper.PriceMapper;
import cc.mrbird.febs.server.system.service.IPriceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 价格表 Service实现
 *
 * @author zlczhou
 * @date 2019-12-20 17:37:38
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PriceServiceImpl extends ServiceImpl<PriceMapper, Price> implements IPriceService {

    @Override
    public IPage<Price> findPrices(QueryRequest request, Price price) {
        LambdaQueryWrapper<Price> queryWrapper = getPriceLambdaQueryWrapper(price);
        Page<Price> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Price> findPrices(Price price) {
        LambdaQueryWrapper<Price> queryWrapper = getPriceLambdaQueryWrapper(price);
        return this.baseMapper.selectList(queryWrapper);
    }

    private LambdaQueryWrapper<Price> getPriceLambdaQueryWrapper(Price price) {
        LambdaQueryWrapper<Price> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        if (StringUtils.isNotBlank(price.getName())) {
            queryWrapper.likeLeft(Price::getName, price.getName());
        }
        if (price.getPtype() != null) {
            queryWrapper.eq(Price::getPtype, price.getPtype());
        }
        queryWrapper.orderByDesc(Price::getId);
        return queryWrapper;
    }

    @Override
    public Price findByName(String name) {
        LambdaQueryWrapper<Price> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.likeLeft(Price::getName, name);
        }
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional
    public void createPrice(Price price) {
        this.save(price);
    }

    @Override
    @Transactional
    public void updatePrice(Price price) {
        this.saveOrUpdate(price);
    }

    @Override
    @Transactional
    public void deletePrice(Price price) {
        LambdaQueryWrapper<Price> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}

    @Override
    @Transactional
    public void deletePrices(String[] priceIds) {
        removeByIds(Arrays.asList(priceIds));
    }
}
