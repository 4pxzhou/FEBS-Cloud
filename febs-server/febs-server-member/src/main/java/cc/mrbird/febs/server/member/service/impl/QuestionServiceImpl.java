package cc.mrbird.febs.server.member.service.impl;

import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.member.Question;
import cc.mrbird.febs.server.member.mapper.QuestionMapper;
import cc.mrbird.febs.server.member.service.IQuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Arrays;
import java.util.List;
/**
 * 问题表 Service实现
 *
 * @author MrBird
 * @date 2020-03-31 21:38:11
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public IPage<Question> findQuestions(QueryRequest request, Question question) {
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        if(StringUtils.isNotBlank(question.getQuestion())){
            queryWrapper.like(Question::getQuestion,question.getQuestion());
        }
        Page<Question> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Question> findQuestions(Question question) {
	    LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public Question findByQuestion(String question) {
        return questionMapper.findByQuestion(question);
    }

    @Override
    @Transactional
    public void createQuestion(Question question) {
        this.save(question);
    }

    @Override
    @Transactional
    public void updateQuestion(Question question) {
        this.saveOrUpdate(question);
    }

    @Override
    @Transactional
    public void deleteQuestion(Question question) {
        LambdaQueryWrapper<Question> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}

    @Override
    @Transactional
    public void deleteQuestions(String[] questionIds) {
        removeByIds(Arrays.asList(questionIds));
    }
}
