package cc.mrbird.febs.server.member.service;


import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.member.Accusation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 举报表，指控 Service接口
 *
 * @author zlczhou
 * @date 2019-12-23 17:58:17
 */
public interface IAccusationService extends IService<Accusation> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param accusation accusation
     * @return IPage<Accusation>
     */
    IPage<Accusation> findAccusations(QueryRequest request, Accusation accusation);

    /**
     * 查询（所有）
     *
     * @param accusation accusation
     * @return List<Accusation>
     */
    List<Accusation> findAccusations(Accusation accusation);

    /**
     * 新增
     *
     * @param accusation accusation
     */
    void createAccusation(Accusation accusation);

    /**
     * 修改
     *
     * @param accusation accusation
     */
    void updateAccusation(Accusation accusation);

    /**
     * 删除
     *
     * @param accusation accusation
     */
    void deleteAccusation(Accusation accusation);

    /**
     * 查看会员idHmid是否被举报
     * @param id
     */
    List<Accusation> findByHmid(Integer id);

    /**
     * 设置为完结
     * @param id
     */
    void setDone(Integer id);
}
