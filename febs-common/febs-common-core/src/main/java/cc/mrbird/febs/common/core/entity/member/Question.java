package cc.mrbird.febs.common.entity.member;

import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 问题表 Entity
 *
 * @author MrBird
 * @date 2020-03-31 21:38:11
 */
@Data
@TableName("t_question")
public class Question implements Serializable {

    private static final long serialVersionUID = -4298305602405803375L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 问题描述
     */
    @TableField("question")
    @NotBlank(message = "{required}")
    @Size(max = 255, message = "{noMoreThan}")
    private String question;

    /**
     * 答案A
     */
    @TableField("answera")
    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    private String answera;

    /**
     * 答案B
     */
    @TableField("answerb")
    @NotBlank(message = "{required}")
    @Size(max = 50, message = "{noMoreThan}")
    private String answerb;

    /**
     * 正确的答案
     */
    @TableField("rightanswer")
    @NotBlank(message = "{required}")
    @Size(max = 4, message = "{noMoreThan}")
    private String rightanswer;
}