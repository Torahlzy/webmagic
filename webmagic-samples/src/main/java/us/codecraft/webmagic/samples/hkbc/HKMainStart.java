package us.codecraft.webmagic.samples.hkbc;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.samples.hkbc.pipline.HKHGame_TopicsPipLine;
import us.codecraft.webmagic.samples.hkbc.pipline.HKSSTT_TopicsPipline;
import us.codecraft.webmagic.samples.hkbc.pipline.HKSSXS_TopicsPipLine;
import us.codecraft.webmagic.samples.hkbc.processor.HKBCPic_TopicProcessor;
import us.codecraft.webmagic.samples.hkbc.processor.HKBCNormal_TopicProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

public class HKMainStart {
    public static void main(String[] args) {
//        spSSXSTopic();
//        spHGameTopic();
        spSSTTTopic();
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

    public static void spSSTTContentPic(){


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
