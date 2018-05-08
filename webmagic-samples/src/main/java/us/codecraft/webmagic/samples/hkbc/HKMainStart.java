package us.codecraft.webmagic.samples.hkbc;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.samples.hkbc.pipline.HKHGameTopicsPlpLine;
import us.codecraft.webmagic.samples.hkbc.pipline.HKSSXSTopicsPipLine;
import us.codecraft.webmagic.samples.hkbc.processor.HKBCTopicProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

public class HKMainStart {
    public static void main(String[] args) {
//        spSSXSTopic();
        spHGameTopic();
    }

    private static void spSSXSTopic() {
        Spider.create(new HKBCTopicProcessor())
                .addUrl("http://www.hkbbcc.xyz/forum-20-1.html")
                .setScheduler(new FileCacheQueueScheduler("./filecache/ssxs/"))
//                .addPipeline(new FilePipeline("./spresult/"))
                .addPipeline(new HKSSXSTopicsPipLine())
                .thread(1)
                .run();
    }
    private static void spHGameTopic() {
        Spider.create(new HKBCTopicProcessor())
                .addUrl("http://www.hkbbcc.xyz/forum-42-1.html")
                .setScheduler(new FileCacheQueueScheduler("./filecache/hgame/"))
//                .addPipeline(new FilePipeline("./spresult/"))
                .addPipeline(new HKHGameTopicsPlpLine())
                .thread(1)
                .run();
    }

}
