package us.codecraft.webmagic.samples.hkbc.pipline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.samples.hkbc.HKBCPageProcessor;
import us.codecraft.webmagic.samples.hkbc.dao.HKDao;
import us.codecraft.webmagic.samples.hkbc.model.HKssxsPcTopic;

import java.util.List;
import java.util.Map;

public class HKssxsTopicsPipLine implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<HKssxsPcTopic>  pc_ssxs_topic_list = (List<HKssxsPcTopic>)resultItems.get("pc_ssxs_topic_List");
        if (pc_ssxs_topic_list == null || pc_ssxs_topic_list.size() < 1) {
            HKBCPageProcessor.logger.error("没有获得结果！{}" + resultItems.getRequest().getUrl());
            return;
        }
        System.out.println("地址: " + resultItems.getRequest().getUrl()
                + "获得" + pc_ssxs_topic_list.size() + "个"
                + "\n第一个：" + pc_ssxs_topic_list.get(0)
        );
        HKDao.insertSsxsPcTopics(pc_ssxs_topic_list);
    }
}
