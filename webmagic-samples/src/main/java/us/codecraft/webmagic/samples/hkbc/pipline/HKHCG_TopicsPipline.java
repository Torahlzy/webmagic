package us.codecraft.webmagic.samples.hkbc.pipline;

import us.codecraft.webmagic.samples.hkbc.dao.HKDao;
import us.codecraft.webmagic.samples.hkbc.model.HKssxsPcTopic;

import java.util.List;

public class HKHCG_TopicsPipline extends HKTopicsPipLine<HKssxsPcTopic> {
    @Override
    protected void insertDao(List<HKssxsPcTopic> list) {
        HKDao.insertHCGPcTopics(list);
    }
}
