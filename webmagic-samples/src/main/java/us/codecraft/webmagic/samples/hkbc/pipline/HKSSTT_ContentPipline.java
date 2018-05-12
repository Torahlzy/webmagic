package us.codecraft.webmagic.samples.hkbc.pipline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.samples.hkbc.MyLogger;
import us.codecraft.webmagic.samples.hkbc.dao.HKDao;
import us.codecraft.webmagic.samples.hkbc.model.HKPicImg;

import java.util.List;

public class HKSSTT_ContentPipline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<HKPicImg> imgList = resultItems.get("imgList");
        if (imgList == null || imgList.size() < 1) {
            MyLogger.logger.error("没有获得结果！{}" + resultItems.getRequest().getUrl());
            return;
        }
        System.out.println("地址: " + resultItems.getRequest().getUrl()
                + "获得" + imgList.size() + "个"
                + "\n：" + imgList
        );
        HKDao.insertImg_SSTTContent(imgList);
    }
}
