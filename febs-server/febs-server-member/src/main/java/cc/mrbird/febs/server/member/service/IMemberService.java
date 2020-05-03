package cc.mrbird.febs.server.member.service;


import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.member.Member;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 会员表 Service接口
 *
 * @author MrBird
 * @date 2019-12-20 17:23:16
 */
public interface IMemberService extends IService<Member> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param member member
     * @return IPage<Member>
     */
    IPage<Member> findFalseMembers(QueryRequest request, Member member);

    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param member member
     * @return IPage<Member>
     */
    IPage<Member> findTrueMembers(QueryRequest request, Member member);

    /**
     * 查询（所有）
     *
     * @param member member
     * @return List<Member>
     */
    List<Member> findMembers(Member member);

    /**
     * 审核会员
     * 通过
     * @param id
     */
    void passInfo(Integer id);

    /**
     * 审核会员
     * 不通过
     * @param id
     */
    void notPassInfo(Integer id);

    /**
     * 禁用会员
     * @param id
     */
    void forbidMember(Integer id);

    /**
     * 启用会员
     * @param id
     */
    void enbaleMember(Integer id);

    /**
     * 新增
     *
     * @param member member
     */
    void createMember(Member member);

    /**
     * 删除
     *
     * @param member member
     */
    void deleteMember(Member member);
}
