package MyBatis;


import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.Transaction;
import org.json.JSONObject;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @version: 1.0
 * @description:
 * mybatis的一级缓存
 * mybatis的二级缓存
 * spring中的mybatis一级缓存失效
 *
 * @author: zhiwei.liao
 * @create: 2020-12-04 11:45:28
 **/

public class Mybatis {

    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("/Users/liaozhiwei/Documents/learn/Learn/src/main/resources/mybatis.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(reader);
        SqlSession sqlSession = build.openSession();
        Configuration configuration = build.getConfiguration();
        configuration.addMapper(testMapper.class);
        testMapper mapper = build.openSession().getMapper(testMapper.class);
        mapper.find(111L);
        sqlSession.close();
    }

    public void doQuery(){

    }
}
