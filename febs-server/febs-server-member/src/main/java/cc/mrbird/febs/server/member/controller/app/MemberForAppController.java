package cc.mrbird.febs.server.member.controller.app;

import cc.mrbird.febs.common.core.entity.FebsResponse;
import cc.mrbird.febs.common.core.entity.member.Member;
import cc.mrbird.febs.common.core.exception.FebsException;
import cc.mrbird.febs.server.member.annotation.ControllerEndpoint;
import cc.mrbird.febs.server.member.controller.app.vo.MemberVo;
import cc.mrbird.febs.server.member.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 会员表 为app提供的注册Controller
 *
 * @author zlczhou
 * @date 2019-12-20 17:23:16
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/app/member")
public class MemberForAppController {

    @Autowired
    private IMemberService memberService;

    @PostMapping
    @ControllerEndpoint(operation = "新增Member", exceptionMessage = "新增Member失败")
    public void addMember(@Valid Member member) throws FebsException {
        List<Member> lstMember = this.memberService.findMembers(member);
        if (CollectionUtils.isEmpty(lstMember)){
            this.memberService.createMember(member);
        }else{
            throw new FebsException("该手机号或者身份证号已经注册");
        }
    }

    /**
     * 通过身份证号获取会员练习方式
     * @param idCard
     * @return
     */
    @GetMapping("/getContact")
    @ControllerEndpoint(operation = "获取会员联系方式", exceptionMessage = "获取会员联系方式失败")
    public FebsResponse getContact(@NotBlank(message = "{required}") String idCard){
        //校验字段
        return null;
    }

    /**
     * 获取所有征婚会员的信息
     * @return
     */
    @GetMapping("/getMemberInfo")
    @ControllerEndpoint(operation = "获取会员征婚信息", exceptionMessage = "获取会员征婚信息失败")
    public FebsResponse getMemberInfo(@Valid MemberVo memberVo){
        return null;
    }
}