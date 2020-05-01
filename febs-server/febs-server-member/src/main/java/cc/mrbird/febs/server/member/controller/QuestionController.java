package cc.mrbird.febs.server.member.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.member.Question;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.server.member.service.IQuestionService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 问题表 Controller
 *
 * @author MrBird
 * @date 2020-03-31 21:38:11
 */
@Slf4j
@Validated
@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @GetMapping("list")
    @PreAuthorize("hasAuthority('question:view')")
    @ControllerEndpoint(operation = "条件查询Question/按钮", exceptionMessage = "条件Question查询失败")
    public FebsResponse questionList(QueryRequest request, Question question) {
        Map<String, Object> dataTable = FebsUtil.getDataTable(this.questionService.findQuestions(request, question));
        return new FebsResponse().data(dataTable);
    }

    @GetMapping("check/{question}")
    public boolean checkQuestion(@NotBlank(message = "{required}") @PathVariable String question) {
        return this.questionService.findByQuestion(question) == null;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('question:add')")
    @ControllerEndpoint(operation = "新增Question/按钮", exceptionMessage = "新增Question失败")
    public void addQuestion(@Valid Question question) throws FebsException {
        this.questionService.createQuestion(question);
    }

    @DeleteMapping("{questionIds}")
    @PreAuthorize("hasAuthority('question:delete')")
    @ControllerEndpoint(operation = "删除Question/按钮", exceptionMessage = "删除Question失败")
    public void deleteQuestion(@NotBlank(message = "{required}") @PathVariable String questionIds) throws FebsException {
        this.questionService.deleteQuestions(questionIds.split(StringPool.COMMA));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('question:update')")
    @ControllerEndpoint(operation = "修改Question/按钮", exceptionMessage = "修改Question失败")
    public void updateQuestion(@Valid Question question) throws FebsException {
        this.questionService.updateQuestion(question);
    }
}
