package us.codecraft.webmagic.samples.hkbc.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.samples.hkbc.model.HKssxsPcTopic;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.5.1
 */
public class HKBCNormal_TopicProcessor implements PageProcessor {
    public static final Logger logger = LoggerFactory.getLogger(HKBCNormal_TopicProcessor.class);

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        page.addTargetRequests( page.getHtml().xpath("//*[@id=\"fd_page_bottom\"]/div/a").links().all());
        Selectable a_nodes = page.getHtml().xpath("//*[@id=\"moderate\"]/table/tbody/tr/th/a");
        List<String> topics_title = page.getHtml().xpath("//*[@id=\"moderate\"]/table/tbody/tr/th/a/text()").all();
        List<String> topics_url = a_nodes.links().all();
        logger.info("找到主题 {} 个", (topics_url != null ? topics_url.size() : 0));
        parserTopic(page, topics_title, topics_url);
    }

    protected void parserTopic(Page page, List<String> topics_title, List<String> topics_url) {
        if (topics_url != null && topics_url.size() > 0) {
            List<HKssxsPcTopic> topicsList = new ArrayList<HKssxsPcTopic>(topics_url.size());
            for (int i = 0; i < topics_url.size(); i++) {
                HKssxsPcTopic hKssxsTopic = HKssxsPcTopic.create();
                hKssxsTopic.setUrl(topics_url.get(i));
                hKssxsTopic.setTitle(topics_title.get(i).trim());
                topicsList.add(hKssxsTopic);
            }
            page.putField("topic", topicsList);
        } else {
            page.setSkip(true);
        }
    }

    @Override
    public Site getSite() {

        site.addCookie("Ovo6_2132_saltkey", "k1544GMQ")
                .addCookie("Ovo6_2132_lastvisit","1524918994")
                .addCookie("Ovo6_2132_auth","6d1ft8sgrfgG5PZAuFGKyrZjZ%2F2IABBehdpD25Wu53riEQwG5J%2FDCn%2BEfxPt1%2BU8tUewEfYTZe5dG0KZ4BXN5UjXpRZs")
                .addCookie("Ovo6_2132_lastcheckfeed","3068814%7C1525020806")
                .addCookie("Ovo6_2132_nofavfid","1")
                .addCookie("Ovo6_2132_smile","1D1")
                .addCookie("PHPSESSID","13s4sb84cvmi5v9cpb7hlkt466")
                .addCookie("Ovo6_2132_viewid","tid_4957034")
                .addCookie("Ovo6_2132_visitedfid","20D2D18")
                .addCookie("Ovo6_2132_home_diymode","1")
                .addCookie("Ovo6_2132_myrepeat_rr","R0")
                .addCookie("Ovo6_2132_ulastactivity","1525363703%7C0")
                .addCookie("Ovo6_2132_lastact","1525363975%09forum.php%09forumdisplay")
                .addCookie("Ovo6_2132_forum_lastvisit","D_18_1525277021D_2_1525277090D_20_1525363975")

                .addHeader("User-Agent","Mozilla/5.0 (Linux; U; Android 7.1.2; zh-cn; Redmi 5 Plus Build/N2G47H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/61.0.3163.128 Mobile Safari/537.36 XiaoMi/MiuiBrowser/9.6.1")
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .addHeader("Accept-Encoding","gzip, deflate")
                .addHeader("Accept-Language","zh-CN,zh;q=0.9")
                .addHeader("Cache-Control","max-age=0")
                .addHeader("Connection","keep-alive")
                .addHeader("Host","www.hkbbcc.xyz");

        return site;
    }

}
