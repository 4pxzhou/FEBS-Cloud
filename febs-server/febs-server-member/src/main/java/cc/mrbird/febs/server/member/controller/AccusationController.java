package cc.mrbird.febs.server.member.controller;


import cc.mrbird.febs.common.core.entity.FebsResponse;
import cc.mrbird.febs.common.core.entity.QueryRequest;
import cc.mrbird.febs.common.core.entity.member.Accusation;
import cc.mrbird.febs.common.core.exception.FebsException;
import cc.mrbird.febs.common.core.utils.FebsUtil;
import cc.mrbird.febs.server.member.service.IAccusationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;
import java.util.Map;

/**
 * 举报表，指控 Controller
 *
 * @author zlczhou
 * @date 2019-12-23 17:58:17
 */
@Slf4j
@Validated
@RestController
@RequestMapping("accusation")
public class AccusationController {

    @Autowired
    private IAccusationService accusationService;

    @GetMapping
    @PreAuthorize("hasAuthority('accusation:list')")
    public FebsResponse getAllAccusations(Accusation accusation) {
        return new FebsResponse().data(accusationService.findAccusations(accusation));
    }

    @GetMapping("list")
    @PreAuthorize("hasAuthority('accusation:view')")
    public FebsResponse accusationList(QueryRequest request, Accusation accusation) {
        Map<String, Object> dataTable = FebsUtil.getDataTable(this.accusationService.findAccusations(request, accusation));
        return new FebsResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('accusation:add')")
    public void addAccusation(@Valid Accusation accusation) throws FebsException {
        try {
            this.accusationService.createAccusation(accusation);
        } catch (Exception e) {
            String message = "新增Accusation失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}