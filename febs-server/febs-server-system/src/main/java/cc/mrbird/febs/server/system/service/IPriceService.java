package cc.mrbird.febs.server.system.service;

import cc.mrbird.febs.common.entity.system.Price;
import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 价格表 Service接口
 *
 * @author zlczhou
 * @date 2019-12-20 17:37:38
 */
public interface IPriceService extends IService<Price> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param price price
     * @return IPage<Price>
     */
    IPage<Price> findPrices(QueryRequest request, Price price);

    /**
     * 查询（所有）
     *
     * @param price price
     * @return List<Price>
     */
    List<Price> findPrices(Price price);

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    Price findByName(String name);

    /**
     * 新增
     *
     * @param price price
     */
    void createPrice(Price price);

    /**
     * 修改
     *
     * @param price price
     */
    void updatePrice(Price price);

    /**
     * 删除
     *
     * @param price price
     */
    void deletePrice(Price price);

    /**
     * 删除
     *
     * @param priceIds String[]
     */
    void deletePrices(String[] priceIds);
}
