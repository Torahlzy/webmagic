package us.codecraft.webmagic.samples.hkbc.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import us.codecraft.webmagic.samples.hkbc.processor.HKBCTopicProcessor;
import us.codecraft.webmagic.samples.hkbc.model.HKssxsPcTopic;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class HKDao {
    /**
     * pc端色色小说
     *
     * @param list
     */
    public static void insertSsxsPcTopics(List<HKssxsPcTopic> list) {
        if (list == null || list.size() <= 0) {
            HKBCTopicProcessor.logger.error("没有数据要保存 ：{}", list);
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
            HKBCTopicProcessor.logger.info("插入结果：{}", Arrays.toString(batch));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * pc端hgame
     *
     * @param list
     */
    public static void insertHGamePcTopics(List<HKssxsPcTopic> list) {
        if (list == null || list.size() <= 0) {
            HKBCTopicProcessor.logger.error("没有数据要保存 ：{}", list);
            return;
        }

        QueryRunner qr = new QueryRunner(Jdbcutils.getDataSource());
        String sql = "insert into pc_hgame_topic (title,url) values(?,?)";
        Object[][] params = new Object[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            params[i][0] = list.get(i).getTitle();
            params[i][1] = list.get(i).getUrl();
        }
        try {
            int[] batch = qr.batch(sql, params);
            HKBCTopicProcessor.logger.info("插入结果：{}", Arrays.toString(batch));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<HKssxsPcTopic> queryKeyword(String key) {

        //第一步：创建queryRunner对象，用来操作sql语句
        QueryRunner qr = new QueryRunner(Jdbcutils.getDataSource());

        //第二步：创建sql语句
        String sql = "SELECT * FROM pc_ssxs_topic where title like concat('%','#" + key + "#','%')";

        try {
            List<HKssxsPcTopic> result = qr.query(sql,
                    new BeanListHandler<HKssxsPcTopic>(HKssxsPcTopic.class));
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}



