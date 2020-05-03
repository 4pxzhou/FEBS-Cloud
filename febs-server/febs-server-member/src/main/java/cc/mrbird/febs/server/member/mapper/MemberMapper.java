package cc.mrbird.febs.server.member.mapper;


import cc.mrbird.febs.common.core.entity.member.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 会员表 Mapper
 *
 * @author MrBird
 * @date 2019-12-20 17:23:16
 */
public interface MemberMapper extends BaseMapper<Member> {
   void passInfo(Integer id);
   void notPassInfo(Integer id);
   void forbidMember(Integer id);
   void enbaleMember(Integer id);
}
