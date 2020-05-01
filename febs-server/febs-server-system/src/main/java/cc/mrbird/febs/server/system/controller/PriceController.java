package cc.mrbird.febs.server.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.entity.system.Price;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.server.system.service.IPriceService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 价格表 Controller
 *
 * @author zlczhou
 * @date 2019-12-20 17:37:38
 */
@Slf4j
@Validated
@RestController
@RequestMapping("price")
public class PriceController{

    @Autowired
    private IPriceService priceService;

    @GetMapping("list")
    @PreAuthorize("hasAuthority('price:view')")
    public FebsResponse getAllPrices(Price price) {
        return new FebsResponse().data(priceService.findPrices(price));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('price:view')")
    public FebsResponse priceList(QueryRequest request, Price price) {
        Map<String, Object> dataTable = FebsUtil.getDataTable(this.priceService.findPrices(request, price));
        return new FebsResponse().data(dataTable);
    }

    @GetMapping("check/{name}")
    public boolean checkPriceName(@NotBlank(message = "{required}") @PathVariable String name) {
        return this.priceService.findByName(name) == null;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('price:add')")
    @ControllerEndpoint(operation = "新增价格/按钮", exceptionMessage = "新增价格失败")
    public void addPrice(@Valid Price price) throws FebsException {
        this.priceService.createPrice(price);
    }

    @DeleteMapping("{priceIds}")
    @PreAuthorize("hasAuthority('price:delete')")
    @ControllerEndpoint(operation = "删除价格/按钮", exceptionMessage = "删除价格失败")
    public void deletePrice(@NotBlank(message = "{required}") @PathVariable String priceIds) throws FebsException {
        this.priceService.deletePrices(priceIds.split(StringPool.COMMA));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('price:update')")
    @ControllerEndpoint(operation = "修改价格/按钮", exceptionMessage = "修改价格失败")
    public void updatePrice(@Valid Price price) throws FebsException {
        this.priceService.updatePrice(price);
    }
}
