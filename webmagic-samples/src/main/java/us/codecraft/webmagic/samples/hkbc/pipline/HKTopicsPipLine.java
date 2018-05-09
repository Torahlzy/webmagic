package us.codecraft.webmagic.samples.hkbc.pipline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.samples.hkbc.processor.HKBCNormal_TopicProcessor;

import java.util.List;

public  abstract  class HKTopicsPipLine<T> implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<T> pc_ssxs_topic_list = (List<T>) resultItems.get("topic");
        if (pc_ssxs_topic_list == null || pc_ssxs_topic_list.size() < 1) {
            HKBCNormal_TopicProcessor.logger.error("没有获得结果！{}" + resultItems.getRequest().getUrl());
            return;
        }
        System.out.println("地址: " + resultItems.getRequest().getUrl()
                + "获得" + pc_ssxs_topic_list.size() + "个"
                + "\n第一个：" + pc_ssxs_topic_list.get(0)
        );
        insertDao(pc_ssxs_topic_list);
    }

    protected abstract void insertDao(List<T> list);
}
