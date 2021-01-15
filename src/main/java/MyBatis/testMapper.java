package MyBatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.json.JSONObject;


/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-21 13:35:49
 **/
@Mapper
public interface testMapper {

    @Select("select * from t_flow_activity where id = #{id}")
    JSONObject find(Long id);
}
