package MyBatis;

import Dto.UserDto;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

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
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(new FileReader("D:\\ross最新\\Learn\\src\\main\\resources\\mybatis.xml"));
        Configuration configuration = build.getConfiguration();
        Connection connection = DriverManager.getConnection("","","");
        Transaction transaction = new JdbcTransaction(connection);
        SimpleExecutor simpleExecutor = new SimpleExecutor(configuration, transaction);
        simpleExecutor.
    }
}
