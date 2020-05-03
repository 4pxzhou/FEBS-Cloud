package cc.mrbird.febs.server.member.service.impl;


import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.member.Member;
import cc.mrbird.febs.server.member.mapper.MemberMapper;
import cc.mrbird.febs.server.member.service.IMemberService;
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
 * 会员表 Service实现
 *
 * @author MrBird
 * @date 2019-12-20 17:23:16
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public IPage<Member> findFalseMembers(QueryRequest request, Member member) {
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        // 设置查询条件
        if(StringUtils.isNotBlank(member.getCellphone())){
            queryWrapper.likeRight(Member::getCellphone, member.getCellphone());
        }
        queryWrapper.eq(Member::getIsverify,0).or().eq(Member::getIsverify, 2);
        Page<Member> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public IPage<Member> findTrueMembers(QueryRequest request, Member member) {
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        // 设置查询条件
        if(StringUtils.isNotBlank(member.getCellphone())){
            queryWrapper.likeRight(Member::getCellphone, member.getCellphone());
        }
        if(member.getStatus() != null){
            queryWrapper.eq(Member::getStatus, member.getStatus());
        }
        queryWrapper.eq(Member::getIsverify,1);
        Page<Member> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Member> findMembers(Member member) {
	    LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
		// 设置查询条件
        queryWrapper.eq(Member::getCellphone,member.getCellphone()).or().eq(Member::getIdcard,member.getIdcard());
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public void passInfo(Integer id) {
        this.memberMapper.passInfo(id);
    }

    @Override
    public void notPassInfo(Integer id) {
        this.memberMapper.notPassInfo(id);
    }

    @Override
    public void forbidMember(Integer id) {
        this.memberMapper.forbidMember(id);
    }

    @Override
    public void enbaleMember(Integer id) {
        this.memberMapper.enbaleMember(id);
    }

    @Override
    @Transactional
    public void createMember(Member member) {
        this.save(member);
    }

    @Override
    @Transactional
    public void deleteMember(Member member) {
        LambdaQueryWrapper<Member> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
