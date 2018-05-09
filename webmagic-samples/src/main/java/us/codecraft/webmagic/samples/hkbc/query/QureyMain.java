package us.codecraft.webmagic.samples.hkbc.query;

import us.codecraft.webmagic.samples.hkbc.dao.HKDao;
import us.codecraft.webmagic.samples.hkbc.model.Anode;
import us.codecraft.webmagic.samples.hkbc.model.HKssxsPcTopic;
import us.codecraft.webmagic.samples.hkbc.processor.HKBCTopicProcessor;

import java.util.ArrayList;
import java.util.List;

public class QureyMain {

    public static void main(String[] args) {
        String keyword = "ntr";
        List<HKssxsPcTopic> resultList = HKDao.queryKeywordInSsxs(keyword);
        if (resultList == null || resultList.size() <= 0) {
            HKBCTopicProcessor.logger.info("关键词 {} 没有结果", keyword);
            return;
        }
        List<Anode> list = new ArrayList<Anode>(resultList.size());
        for (HKssxsPcTopic topic : resultList) {
            list.add(Anode.create(topic));
        }

        CreateResultXml.createResult(list, keyword);
    }
}
