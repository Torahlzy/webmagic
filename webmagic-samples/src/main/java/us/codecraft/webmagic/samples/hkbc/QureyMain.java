package us.codecraft.webmagic.samples.hkbc;

import us.codecraft.webmagic.samples.hkbc.dao.HKDao;
import us.codecraft.webmagic.samples.hkbc.model.Anode;
import us.codecraft.webmagic.samples.hkbc.model.HKssxsPcTopic;
import us.codecraft.webmagic.samples.hkbc.processor.HKBCNormal_TopicProcessor;
import us.codecraft.webmagic.samples.hkbc.query.CreateResultXml;

import java.util.ArrayList;
import java.util.List;

public class QureyMain {

    public static void main(String[] args) {
        querySSXSKey();
//        querySSTTTopicUrl();
    }

    private static void querySSXSKey() {
        String keyword = "ntr";
        List<HKssxsPcTopic> resultList = HKDao.queryKeywordInSsxs(keyword);
        if (resultList == null || resultList.size() <= 0) {
            HKBCNormal_TopicProcessor.logger.info("关键词 {} 没有结果", keyword);
            return;
        }
        List<Anode> list = new ArrayList<Anode>(resultList.size());
        for (HKssxsPcTopic topic : resultList) {
            list.add(Anode.create(topic));
        }

        CreateResultXml.createResult(list, keyword);
    }

    public static void querySSTTTopicUrl() {


    }
}
