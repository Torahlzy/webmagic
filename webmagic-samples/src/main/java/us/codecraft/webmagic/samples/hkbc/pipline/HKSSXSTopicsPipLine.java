package us.codecraft.webmagic.samples.hkbc.pipline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.samples.hkbc.dao.HKDao;
import us.codecraft.webmagic.samples.hkbc.model.HKssxsPcTopic;
import us.codecraft.webmagic.samples.hkbc.processor.HKBCTopicProcessor;

import java.util.List;

public class HKSSXSTopicsPipLine extends HKTopicsPipLine {


    protected void insertDao(List<HKssxsPcTopic> pc_ssxs_topic_list) {
        HKDao.insertSsxsPcTopics(pc_ssxs_topic_list);
    }
}
