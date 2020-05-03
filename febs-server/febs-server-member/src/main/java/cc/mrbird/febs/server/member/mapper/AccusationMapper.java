package cc.mrbird.febs.server.member.mapper;

import cc.mrbird.febs.common.core.entity.member.Accusation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 举报表，指控 Mapper
 *
 * @author zlczhou
 * @date 2019-12-23 17:58:17
 */
public interface AccusationMapper extends BaseMapper<Accusation> {
    void setDone(Integer id);

    List<Accusation> findByHmid(Integer id);
}