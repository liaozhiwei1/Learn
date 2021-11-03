package MyBatis;


import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        testMapper mapper = sqlSession.getMapper(testMapper.class);
        mapper.find(111L);
        sqlSession.close();
    }

    public void doQuery(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2.获取连接
            connection = DriverManager.getConnection("", "", "");

            //3.绑定sql
            preparedStatement = connection.prepareStatement("select * from t_flow_activity where id = ?");

            //4.预编译
            preparedStatement.setString(1,"aaa");
            //5.执行sql,获取结果集
            ResultSet resultSet = preparedStatement.executeQuery();

            //6.处理结果集
            while (resultSet.next()){

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
