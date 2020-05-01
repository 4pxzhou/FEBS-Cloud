package cc.mrbird.febs.common.entity.system;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 价格表 Entity
 *
 * @author zlczhou
 * @date 2019-12-20 18:14:27
 */
@Data
@TableName("t_price")
public class Price implements Serializable {

    private static final long serialVersionUID = -950994863109236017L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField("name")
    @NotBlank(message = "{required}")
    @Size(max = 20, message = "{noMoreThan}")
    private String name;

    /**
     * 价格
     */
    @TableField("price")
    @Max(value = 99999, message = "{noMaxThan}")
    @Min(value = 1,message = "{noMinThan}")
    private Integer price;

    /**
     * 金额类型，0-获取个人信息费，1-信用金
     */
    @TableField("ptype")
    @NotNull
    private Byte ptype;

}