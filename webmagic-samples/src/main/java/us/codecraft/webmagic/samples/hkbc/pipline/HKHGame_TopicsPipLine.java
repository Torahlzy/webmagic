package us.codecraft.webmagic.samples.hkbc.pipline;

import us.codecraft.webmagic.samples.hkbc.dao.HKDao;
import us.codecraft.webmagic.samples.hkbc.model.HKssxsPcTopic;

import java.util.List;

public class HKHGame_TopicsPipLine extends HKTopicsPipLine<HKssxsPcTopic> {
    protected void insertDao(List<HKssxsPcTopic> list) {
        HKDao.insertHGamePcTopics(list);
    }
}
