package us.codecraft.webmagic.samples.hkbc.dao;



import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class Jdbcutils {
    private static ComboPooledDataSource dataSource;//创建c3p0连接，整个项目有一个连接池就可以了，设为static只要实例化一次
    static {
        dataSource = new ComboPooledDataSource();
    }
    
    public static DataSource getDataSource() {
        return dataSource;
    }
    public static QueryRunner getQueryRunner(){//创建DButils常用工具类QueryRunner的对象
        return new QueryRunner(dataSource);
    }  
    
}