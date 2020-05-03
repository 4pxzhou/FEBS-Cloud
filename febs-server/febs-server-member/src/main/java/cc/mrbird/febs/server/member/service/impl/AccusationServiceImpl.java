package cc.mrbird.febs.server.member.service.impl;

import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.member.Accusation;
import cc.mrbird.febs.server.member.mapper.AccusationMapper;
import cc.mrbird.febs.server.member.service.IAccusationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
/**
 * 举报表，指控 Service实现
 *
 * @author zlczhou
 * @date 2019-12-23 17:58:17
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AccusationServiceImpl extends ServiceImpl<AccusationMapper, Accusation> implements IAccusationService {

    @Autowired
    private AccusationMapper accusationMapper;

    @Override
    public IPage<Accusation> findAccusations(QueryRequest request, Accusation accusation) {
        LambdaQueryWrapper<Accusation> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        if(accusation.getHmid() != null){
            queryWrapper.likeRight(Accusation::getHmid, accusation.getHmid()).or().likeRight(Accusation::getUmid, accusation.getHmid());
        }
        Page<Accusation> page = new Page<Accusation>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Accusation> findAccusations(Accusation accusation) {
	    LambdaQueryWrapper<Accusation> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createAccusation(Accusation accusation) {
        this.save(accusation);
    }

    @Override
    @Transactional
    public void updateAccusation(Accusation accusation) {
        this.saveOrUpdate(accusation);
    }

    @Override
    @Transactional
    public void deleteAccusation(Accusation accusation) {
        LambdaQueryWrapper<Accusation> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}

    @Override
    public List<Accusation> findByHmid(Integer id) {
        return this.accusationMapper.findByHmid(id);
    }

    @Override
    @Transactional
    public void setDone(Integer id) {
        this.accusationMapper.setDone(id);
    }
}