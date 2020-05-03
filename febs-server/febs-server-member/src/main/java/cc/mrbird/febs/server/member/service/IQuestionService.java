package cc.mrbird.febs.server.member.service;


import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.member.Question;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 问题表 Service接口
 *
 * @author MrBird
 * @date 2020-03-31 21:38:11
 */
public interface IQuestionService extends IService<Question> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param question question
     * @return IPage<Question>
     */
    IPage<Question> findQuestions(QueryRequest request, Question question);

    /**
     * 查询（所有）
     *
     * @param question question
     * @return List<Question>
     */
    List<Question> findQuestions(Question question);

    /**
     * 通过问题描述查询是否已经添加该问题
     * @param question
     * @return
     */
    Question findByQuestion(String question);

    /**
     * 新增
     *
     * @param question question
     */
    void createQuestion(Question question);

    /**
     * 修改
     *
     * @param question question
     */
    void updateQuestion(Question question);

    /**
     * 删除
     *
     * @param question question
     */
    void deleteQuestion(Question question);

    /**
     * 删除
     *
     * @param questionIds String[]
     */
    void deleteQuestions(String[] questionIds);
}
