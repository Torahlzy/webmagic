package us.codecraft.webmagic.samples.hkbc;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.samples.hkbc.dao.HKDao;
import us.codecraft.webmagic.samples.hkbc.model.HKssttPcTopic;
import us.codecraft.webmagic.samples.hkbc.pipline.HKHGame_TopicsPipLine;
import us.codecraft.webmagic.samples.hkbc.pipline.HKSSTT_ContentPipline;
import us.codecraft.webmagic.samples.hkbc.pipline.HKSSTT_TopicsPipline;
import us.codecraft.webmagic.samples.hkbc.pipline.HKSSXS_TopicsPipLine;
import us.codecraft.webmagic.samples.hkbc.processor.HKBCNormal_TopicProcessor;
import us.codecraft.webmagic.samples.hkbc.processor.HKBCPic_TopicProcessor;
import us.codecraft.webmagic.samples.hkbc.processor.HKSSTT_ContentProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.util.List;

public class HKMainStart {
    public static final String host = "http://www.hkbbcc.xyz/";

    public static void main(String[] args) {
//        spSSXSTopic();
//        spHGameTopic();
//        spSSTTTopic();
        spSSTTContentPic();
    }

    /**
     * 色色贴图主题
     */
    private static void spSSTTTopic() {
        Spider.create(new HKBCPic_TopicProcessor())
                .addUrl("http://www.hkbbcc.xyz/forum.php?mod=forumdisplay&fid=18&page=1")
                .setScheduler(new FileCacheQueueScheduler("./filecache/sstt/"))
//                .addPipeline(new FilePipeline("./spresult/"))
                .addPipeline(new HKSSTT_TopicsPipline())
                .thread(1)
                .run();
    }

    /**
     * 抓取图片
     */
    public static void spSSTTContentPic() {
        //2720
        for (int i = 0; i < 10; i++) {
            int start = i  + 1;
            int count = 1;
            List<HKssttPcTopic> topicsSSTT = HKDao.getTopicsSSTT(start, count);
            if (topicsSSTT == null || topicsSSTT.size() <= 0) {
                MyLogger.logger.warn("[{},{})没有查询结果", start, (start + count));
                continue;
            }
            MyLogger.logger.info("准备抓取主题个数 {} ", topicsSSTT.size());
            String[] urls = new String[topicsSSTT.size()];
            for (int j = 0; j < topicsSSTT.size(); j++) {
                urls[j] = topicsSSTT.get(j).getUrl();
            }
            Spider.create(new HKSSTT_ContentProcessor())
                    .addUrl(urls)
                    .setScheduler(new FileCacheQueueScheduler("./filecache/ssttPic/"))
                    .addPipeline(new HKSSTT_ContentPipline())
                    .thread(1)
                    .run();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 色色小说主题
     */
    private static void spSSXSTopic() {
        Spider.create(new HKBCNormal_TopicProcessor())
                .addUrl("http://www.hkbbcc.xyz/forum-20-1.html")
                .setScheduler(new FileCacheQueueScheduler("./filecache/ssxs/"))
//                .addPipeline(new FilePipeline("./spresult/"))
                .addPipeline(new HKSSXS_TopicsPipLine())
                .thread(1)
                .run();
    }

    /**
     * h-game主题
     */
    private static void spHGameTopic() {
        Spider.create(new HKBCNormal_TopicProcessor())
                .addUrl("http://www.hkbbcc.xyz/forum-42-1.html")
                .setScheduler(new FileCacheQueueScheduler("./filecache/hgame/"))
//                .addPipeline(new FilePipeline("./spresult/"))
                .addPipeline(new HKHGame_TopicsPipLine())
                .thread(1)
                .run();
    }

}
