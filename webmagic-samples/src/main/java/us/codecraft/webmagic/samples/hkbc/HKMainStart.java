package us.codecraft.webmagic.samples.hkbc;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.samples.hkbc.cache.TopicCache;
import us.codecraft.webmagic.samples.hkbc.dao.HKDao;
import us.codecraft.webmagic.samples.hkbc.model.HKssttPcTopic;
import us.codecraft.webmagic.samples.hkbc.pipline.*;
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
        spHCGTopic();
    }

    /**
     * 色色贴图主题
     */
    private static void spSSTTTopic() {
        Spider.create(new HKBCPic_TopicProcessor())
                .addUrl("http://www.hkbbcc.xyz/forum.php?mod=forumdisplay&fid=18&page=1")
                .setScheduler(new FileCacheQueueScheduler("./filecache/sstt/"))
                .addPipeline(new HKSSTT_TopicsPipline())
                .thread(1)
                .run();
    }
    /**
     * hcg
     */
    private static void spHCGTopic() {
        Spider.create(new HKBCNormal_TopicProcessor())
                .addUrl("http://www.hkbbcc.xyz/forum-66-1.html")
                .setScheduler(new FileCacheQueueScheduler("./filecache/hcg/"))
                .addPipeline(new HKHCG_TopicsPipline())
                .thread(1)
                .run();
    }
    static int curent_ssttTopicid = 1;

    /**
     * 抓取图片地址
     */
    public static void spSSTTContentPic() {
        //每次查3个
        final int count = 2;
        List<HKssttPcTopic> topicsSSTT = HKDao.getTopicsSSTT(curent_ssttTopicid, count);
        if (topicsSSTT == null || topicsSSTT.size() <= 0) {
            MyLogger.logger.warn("[{},{})没有查询结果", curent_ssttTopicid, (curent_ssttTopicid + count));
        }
        MyLogger.logger.info("准备抓取主题个数 {} ", topicsSSTT.size());
        String[] urls = new String[topicsSSTT.size()];
        for (int j = 0; j < topicsSSTT.size(); j++) {
            urls[j] = topicsSSTT.get(j).getUrl();
        }
        TopicCache.getInstance().cacheTopic(topicsSSTT);
        HKSSTT_ContentProcessor pageProcessor = new HKSSTT_ContentProcessor(new HKSSTT_ContentProcessor.onNextPage() {
            @Override
            public List<HKssttPcTopic> getNextPage() {
                if (TopicCache.getInstance().getcachSize() < 100) {
                    curent_ssttTopicid += count;//每次从上次查询结束的位置开始
                    List<HKssttPcTopic> topicsSSTT = HKDao.getTopicsSSTT(curent_ssttTopicid, count);
                    TopicCache.getInstance().cacheTopic(topicsSSTT);
                    MyLogger.logger.info("准备添加主题个数 {} ", topicsSSTT != null ? topicsSSTT.size() : null);
                    return topicsSSTT;
                }
                return null;
            }
        });
        Spider.create(pageProcessor)
                .addUrl(urls)
                .setScheduler(new FileCacheQueueScheduler("./filecache/ssttPic/"))
                .addPipeline(new HKSSTT_ContentPipline())
                .thread(1)
                .run();
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
