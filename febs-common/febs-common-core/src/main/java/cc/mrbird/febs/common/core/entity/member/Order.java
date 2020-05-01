package cc.mrbird.febs.common.core.entity.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单表 Entity
 *
 * @author zlczhou
 * @date 2019-12-23 11:59:20
 */
@Data
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1934972722116927361L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 支付方式，0-微信，1-支付宝
     */
    @NotNull
    @TableField("paytype")
    private Boolean paytype;
    /**
     * 微信或者支付宝订单号
     */
    @NotNull
    @TableField("orderid")
    private String orderid;

    /**
     * 会员ID
     */
    @NotNull
    @TableField("mid")
    private Integer mid;

    /**
     * 交易金额
     */
    @TableField("price")
    @Max(value = 99999, message = "{noMaxThan}")
    @Min(value = 1, message = "{noMinThan}")
    private Integer price;

    /**
     * 订单类型0-获取个人信息费，1-信用金，2-退款
     */
    @NotNull
    @TableField("otype")
    private Byte otype;

    /**
     * 订单状态0-未支付，1-已支付，2-已退款，3-已取消
     */
    @NotNull
    @TableField("status")
    private Byte status;

    /**
     * 交易发生时间
     */
    @NotNull
    @TableField("paytime")
    private Date paytime;

}