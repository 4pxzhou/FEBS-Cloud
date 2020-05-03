package cc.mrbird.febs.server.member.controller;



import cc.mrbird.febs.common.core.entity.FebsResponse;
import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.member.Accusation;
import cc.mrbird.febs.common.core.entity.member.Member;
import cc.mrbird.febs.common.core.exception.FebsException;
import cc.mrbird.febs.common.core.utils.FebsUtil;
import cc.mrbird.febs.server.member.annotation.ControllerEndpoint;
import cc.mrbird.febs.server.member.service.IAccusationService;
import cc.mrbird.febs.server.member.service.IMemberService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 会员表 Controller
 *
 * @author zlczhou
 * @date 2019-12-20 17:23:16
 */
@Slf4j
@Validated
@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired
    private IMemberService memberService;
    @Autowired
    private IAccusationService accusationService;

    @GetMapping("checklist")
    @PreAuthorize("hasAuthority('member:checkView')")
    @ControllerEndpoint(operation = "获取待审核Member信息", exceptionMessage = "获取待审核Member失败")
    public FebsResponse getAllMembers(QueryRequest request, Member member) {
        Map<String, Object> dataTable = FebsUtil.getDataTable(this.memberService.findFalseMembers(request, member));
        return new FebsResponse().data(dataTable);
    }

    @PutMapping("pass")
    @PreAuthorize("hasAuthority('member:pass')")
    @ControllerEndpoint(operation = "审核通过Member信息", exceptionMessage = "审核通过Member信息失败")
    public void passMemberInfo(Integer id) throws FebsException {
        this.memberService.passInfo(id);
    }

    @PutMapping("notpass")
    @PreAuthorize("hasAuthority('member:notpass')")
    @ControllerEndpoint(operation = "审核不通过Member信息", exceptionMessage = "审核不通过Member信息失败")
    public void notPassMemberInfo(Integer id) throws FebsException {
        this.memberService.notPassInfo(id);
    }

    @GetMapping("memberlist")
    @PreAuthorize("hasAuthority('member:view')")
    @ControllerEndpoint(operation = "获取已审核Member信息", exceptionMessage = "获取已审核Member失败")
    public FebsResponse memberList(QueryRequest request, Member member) {
        Map<String, Object> dataTable = FebsUtil.getDataTable(this.memberService.findTrueMembers(request, member));
        return new FebsResponse().data(dataTable);
    }

    @PutMapping("forbidMember")
    @PreAuthorize("hasAuthority('member:forbid')")
    @ControllerEndpoint(operation = "禁用Member信息/按钮", exceptionMessage = "禁用Member信息失败")
    public void forbidMember(Integer id) throws FebsException {
        this.memberService.forbidMember(id);
        this.accusationService.setDone(id);
    }

    @PutMapping("enbaleMember")
    @PreAuthorize("hasAuthority('member:enable')")
    @ControllerEndpoint(operation = "启用Member信息",exceptionMessage = "启用Memeber信息失败")
    public void enbaleMember(Integer id) throws FebsException {
        List<Accusation> lstAccusation = this.accusationService.findByHmid(id);
        if (CollectionUtils.isNotEmpty(lstAccusation)) {
            this.memberService.enbaleMember(id);
        } else {
            throw new FebsException("该用户被举报，不可启用");
        }
    }
}