/*
package MyBatis;

import Dto.UserDto;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ReuseExecutor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * @version: 1.0
 * @description:
 * mybatis的一级缓存
 * mybatis的二级缓存
 * spring中的mybatis一级缓存失效
 *
 * @author: zhiwei.liao
 * @create: 2020-12-04 11:45:28
 **//*

public class Mybatis {

    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("D:\\ross最新\\Learn\\src\\main\\resources\\mybatis.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(reader);
//        Configuration configuration = build.getConfiguration();
//        configuration.addMapper(testMapper.class);
        testMapper mapper = build.openSession().getMapper(testMapper.class);
        JSONObject jsonObject = mapper.find(1318750478217375744l);
        Map map  = new HashMap();
    }
}
*/
