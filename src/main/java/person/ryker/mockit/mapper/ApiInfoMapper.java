package person.ryker.mockit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import person.ryker.mockit.model.ApiInfo;

/**
 * @author raymondcode raymondcode@outlook.com
 * @date 2017/10/23
 */
@Mapper
public interface ApiInfoMapper {
    @Insert(
        "INSERT INTO api_info(`key`, `status`, `content_type`, `body`) VALUES(#{key}, #{status}, #{contentType}, "
            + "#{body,jdbcType=BLOB})")
    int insert(ApiInfo apiInfo);

    @Results(id = "apiInfo", value = {
        @Result(property = "id", column = "id", id = true),
        @Result(property = "key", column = "key"),
        @Result(property = "status", column = "status"),
        @Result(property = "contentType", column = "content_type"),
        @Result(property = "body", column = "body"),
    })
    @Select("SELECT `id`, `key`, `status`, `content_type`, `body` FROM api_info WHERE `key` = #{key}")
    ApiInfo getByKey(@Param("key") String key);
}
