package us.codecraft.webmagic.samples.hkbc.pipline;

import us.codecraft.webmagic.samples.hkbc.dao.HKDao;
import us.codecraft.webmagic.samples.hkbc.model.HKssttPcTopic;

import java.util.List;

public class HKSSTT_TopicsPipline extends HKTopicsPipLine<HKssttPcTopic> {
    @Override
    protected void insertDao(List<HKssttPcTopic> list) {
        HKDao.insertSSTTPcTopics(list);
    }
}
