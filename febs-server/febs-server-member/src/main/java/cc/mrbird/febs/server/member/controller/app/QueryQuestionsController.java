package cc.mrbird.febs.server.member.controller.app;

import cc.mrbird.febs.common.core.entity.member.Question;
import cc.mrbird.febs.server.member.annotation.ControllerEndpoint;
import cc.mrbird.febs.server.member.service.IQuestionService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



/**
 * 问题表 Controller
 *
 * @author MrBird
 * @date 2020-03-31 21:38:11
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/app/question")
public class QueryQuestionsController {

    @Autowired
    private IQuestionService questionService;

//    @GetMapping
//    @ControllerEndpoint(operation = "App加载问题列表", exceptionMessage = "App加载问题列表失败")
//    public List<Question> getAllQuestions(Question question) {
//        return questionService.findQuestions(question);
//    }

    @GetMapping
    @ControllerEndpoint(operation = "App加载问题列表", exceptionMessage = "App加载问题列表失败")
    public String getAllQuestions(Question question) {
        return JSONObject.toJSONString(questionService.findQuestions(question));
    }


}