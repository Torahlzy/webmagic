package us.codecraft.webmagic.samples.hkbc.dao;

import us.codecraft.webmagic.samples.hkbc.HKBCPageProcessor;
import us.codecraft.webmagic.samples.hkbc.model.HKssxsPcTopic;

import java.util.List;

public class HKDao {
    public static void insertSsxsPcTopics(List<HKssxsPcTopic> list){
        HKBCPageProcessor.logger.info("保存数据库 ：{}", list);

    }
}
