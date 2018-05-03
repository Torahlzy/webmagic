package us.codecraft.webmagic.samples.hkbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.samples.GithubRepo;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.5.1
 */
public class HKBCPageProcessor implements PageProcessor {
    public static final Logger logger = LoggerFactory.getLogger(HKBCPageProcessor.class);

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+)").all());
//        githubRepo.setAuthor(page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        Selectable a_nodes = page.getHtml().xpath("//*[@id=\"moderate\"]/table//th[@class='common']/a");
        List<String> all = a_nodes.links().all();
        logger.info("urls : {}", all);
//        githubRepo.setName(page.getHtml().xpath("//h1[contains(@class, 'entry-title') and contains(@class, 'public')]/strong/a/text()").toString());
//        githubRepo.setReadme(page.getHtml().xpath("//div[@id='readme']/tidyText()").toString());
//        if (githubRepo.getName() == null) {
            //skip this page
            page.setSkip(true);
//        } else {
//            page.putField("repo", githubRepo);
//        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new HKBCPageProcessor())
                .addUrl("http://www.hkbbcc.xyz/forum-20-1.html")
                .setScheduler(new FileCacheQueueScheduler("./filecache"))
                .thread(1)
                .run();
    }
}
