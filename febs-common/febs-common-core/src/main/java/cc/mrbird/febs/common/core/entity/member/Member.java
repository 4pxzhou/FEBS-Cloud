package cc.mrbird.febs.common.core.entity.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员表 Entity
 *
 * @author zlczhou
 * @date 2019-12-20 18:14:31
 */
@Data
@TableName("t_member")
public class Member implements Serializable {

    private static final long serialVersionUID = 3127016732869655213L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号
     */
    @TableField("cellphone")
    @NotBlank(message = "{required}")
    @Size(max = 11, message = "{noMoreThan}")
    private String cellphone;

    /**
     * 个人头像，只能使用素颜照
     */
    @TableField("headimg")
    @NotBlank(message = "{required}")
    @Size(max = 255, message = "{noMoreThan}")
    private String headimg;

    /**
     * 昵称
     */
    @TableField("nickname")
    @NotBlank(message = "{required}")
    @Size(max = 20, message = "{noMoreThan}")
    private String nickname;

    /**
     * 姓名
     */
    @TableField("name")
    @NotBlank(message = "{required}")
    @Size(max = 20, message = "{noMoreThan}")
    private String name;

    /**
     * 性别 设定后禁止修改0-女，1-男，2-保密
     */
    @TableField("sex")
    private Boolean sex;
    /**
     * 身份证号
     */
    @TableField("idcard")
    @NotBlank(message = "{required}")
    @Size(min = 18, message = "{noMinThan}")
    @Size(max = 18, message = "{noMoreThan}")
    private String idcard;

    /**
     * 身份证正面照片
     */
    @TableField("frontcardPhoto")
    @NotBlank(message = "{required}")
    @Size(max = 255, message = "{noMoreThan}")
    private String frontcardphoto;

    /**
     * 身高（厘米）
     */
    @TableField("height")
    @Min(value = 50,message = "{noMinThan}")
    @Max(value = 300,message = "{noMaxThan}")
    private Integer height;

    /**
     * 体重（KG）
     */
    @TableField("weight")
    @Min(value = 30,message = "{noMinThan}")
    @Max(value = 600,message = "{noMaxThan}")
    private Integer weight;

    /**
     * 籍贯
     */
    @TableField("nativeplace")
    @NotBlank(message = "{required}")
    @Size(max = 150, message = "{noMoreThan}")
    private String nativeplace;

    /**
     * 工作城市
     */
    @TableField("workcity")
    @NotBlank(message = "{required}")
    @Size(max = 20, message = "{noMoreThan}")
    private String workcity;

    /**
     * 是否停用，0-停用，1-未停用
     */
    @TableField("status")
    private Boolean status;

    /**
     * 是否审核通过，0-未通过，1-通过，2-待审核
     */
    @TableField("isverify")
    private Integer isverify;

    /**
     * 是否离异，0-未婚，1-离异
     */
    @TableField("isdivorce")
    @NotNull
    private Boolean isdivorce;

    /**
     * 是否有孩子，0-没有，1-有
     */
    @TableField("haschildern")
    @NotNull
    private Boolean haschildern;

    /**
     * 是否有房，0-无，1-有
     */
    @TableField("hasroom")
    @NotNull
    private Boolean hasroom;

    /**
     * 是否有车，0-无，1-有
     */
    @TableField("hascar")
    @NotNull
    private Boolean hascar;

    /**
     * 征婚广告，不大于1000个字符
     */
    @TableField("marryad")
    @NotBlank(message = "{required}")
    @Size(max = 10000, message = "{noMoreThan}")
    private String marryad;

    /**
     * 密码至少包含数字跟字母，可以有特殊字符
     */
    @TableField("password")
    @Pattern(regexp = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\W_]).{8,}$", message = "{minAndMaxMsg}")
    @Size(max = 100, message = "{noMoreThan}")
    private String password;

    /**
     * 会员注册时间
     */
    @TableField("createtime")
    private Date createtime;
}