package cc.mrbird.febs.server.member.mapper;

import cc.mrbird.febs.common.core.entity.member.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 问题表 Mapper
 *
 * @author MrBird
 * @date 2020-03-31 21:38:11
 */
public interface QuestionMapper extends BaseMapper<Question> {
    Question findByQuestion(String question);
}