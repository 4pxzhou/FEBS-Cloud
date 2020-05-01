package cc.mrbird.febs.common.entity.member;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.*;

/**
 * 举报表，指控 Entity
 *
 * @author zlczhou
 * @date 2019-12-23 17:58:17
 */
@Data
@TableName("t_accusation")
public class Accusation implements Serializable {

    private static final long serialVersionUID = -5083008245162041034L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 对方的会员ID
     */
    @TableField("hmid")
    private Integer hmid;

    /**
     * 您的会员ID
     */
    @TableField("umid")
    private Integer umid;

    /**
     * 对方QQ或者微信号
     */
    @TableField("hcontact")
    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    private String hcontact;

    /**
     * 你的QQ或者微信号
     */
    @TableField("ucontact")
    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    private String ucontact;

    /**
     * 图片截图1
     */
    @TableField("img1")
    @NotBlank(message = "{required}")
    @Size(max = 255, message = "{noMoreThan}")
    private String img1;

    /**
     * 图片截图2
     */
    @TableField("img2")
    @NotBlank(message = "{required}")
    @Size(max = 255, message = "{noMoreThan}")
    private String img2;

    /**
     * 图片截图3
     */
    @TableField("img3")
    @NotBlank(message = "{required}")
    @Size(max = 255, message = "{noMoreThan}")
    private String img3;

    /**
     * 图片截图4
     */
    @TableField("img4")
    private String img4;

    /**
     * 图片截图5
     */
    @TableField("img5")
    private String img5;

    /**
     * 举报状态,0-开启，1-处理中，2-完结
     */
    @TableField("status")
    @NotNull
    private Boolean status;
}