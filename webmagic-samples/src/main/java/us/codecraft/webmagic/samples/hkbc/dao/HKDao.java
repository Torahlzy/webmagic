package us.codecraft.webmagic.samples.hkbc.dao;

import org.apache.commons.dbutils.QueryRunner;
import us.codecraft.webmagic.samples.hkbc.HKBCPageProcessor;
import us.codecraft.webmagic.samples.hkbc.model.HKssxsPcTopic;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class HKDao {
    public static void insertSsxsPcTopics(List<HKssxsPcTopic> list) {
        if (list == null || list.size() <= 0) {
            HKBCPageProcessor.logger.error("没有数据要保存 ：{}", list);
            return;
        }

        QueryRunner qr = new QueryRunner(Jdbcutils.getDataSource());
        String sql = "insert into pc_ssxs_topic (title,url) values(?,?)";
        Object[][] params = new Object[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            params[i][0] = list.get(i).getTitle();
            params[i][1] = list.get(i).getUrl();
        }
        try {
            int[] batch = qr.batch(sql, params);
            HKBCPageProcessor.logger.info("插入结果：{}", Arrays.toString(batch));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//需求：修改id==7的数据

    public void test2() {

//第一步：创建queryRunner对象，用来操作sql语句

        QueryRunner qr = new QueryRunner(Jdbcutils.getDataSource());

//第二步：创建sql语句

        String sql = "update user set name = ? where id = ?";

//第三步：执行sql语句,params:是sql语句的参数

//注意，给sql语句设置参数的时候，按照user表中字段的顺序

        try {

            int update = qr.update(sql, "柳岩", 7);

            System.out.println(update);

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

//需求：删除id==7的数据


    public void test3() {

//第一步：创建queryRunner对象，用来操作sql语句

        QueryRunner qr = new QueryRunner(Jdbcutils.getDataSource());

//第二步：创建sql语句

        String sql = "delete from user where id = ?";

//第三步：执行sql语句,params:是sql语句的参数

//注意，给sql语句设置参数的时候，按照user表中字段的顺序

        try {

            int update = qr.update(sql, 7);

            System.out.println(update);

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
}



