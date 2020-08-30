package cn.fengylb.mycommunity.mycommunity.mapper;

import cn.fengylb.mycommunity.mycommunity.dto.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag,account_id) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag},#{accountId})")
    void create(Question question);

    @Select("select * from question limit #{offset} , #{size}")
    List<Question> list(@Param("offset") int offset, @Param("size") int size);

    @Select("select count(1) from question ")
    Integer listCount();

    @Select("select * from question where account_id = #{accountId} limit #{offset} , #{size}")
    List<Question> listByUserAccountId(@Param("accountId")Long accountId,@Param("offset") int offset,@Param("size") Integer size);

    @Select("select count(1) from question  where account_id = #{accountId}")
    Integer listCountByUserAccountId(Long accountId);
}
