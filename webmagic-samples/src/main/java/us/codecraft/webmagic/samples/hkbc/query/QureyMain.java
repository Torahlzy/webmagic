package us.codecraft.webmagic.samples.hkbc.query;

import us.codecraft.webmagic.samples.hkbc.dao.HKDao;
import us.codecraft.webmagic.samples.hkbc.model.Anode;
import us.codecraft.webmagic.samples.hkbc.model.HKssxsPcTopic;

import java.util.ArrayList;
import java.util.List;

public class QureyMain {

    public static void main(String[] args) {
        String keyword = "ntr";
        List<HKssxsPcTopic> resultList = HKDao.queryKeyword(keyword);
        List<Anode> list = new ArrayList<Anode>(resultList.size());
        for (HKssxsPcTopic topic : resultList) {
            list.add(Anode.create(topic));
        }

        CreateResultXml.createResult(list, keyword);
    }
}
