package us.codecraft.webmagic.samples.hkbc.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.samples.hkbc.MyLogger;
import us.codecraft.webmagic.samples.hkbc.cache.TopicCache;
import us.codecraft.webmagic.samples.hkbc.model.HKBaseTopic;
import us.codecraft.webmagic.samples.hkbc.model.HKPicImg;
import us.codecraft.webmagic.samples.hkbc.model.HKssttPcTopic;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

public class HKSSTT_ContentProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    onNextPage nextPage;

    public HKSSTT_ContentProcessor(onNextPage nextPage) {
        this.nextPage = nextPage;
    }

    @Override
    public void process(Page page) {
        //获取下一个页面的地址
        List<HKssttPcTopic> nextPage = this.nextPage.getNextPage();
        if (nextPage != null && nextPage.size() > 0) {
            List<String> urls = new ArrayList<String>();
            for (int i = 0; i < nextPage.size(); i++) {
                urls.add(nextPage.get(i).getUrl());
            }
            page.addTargetRequests(urls);
        }
        //获得图片
        Selectable jsop = page.getHtml().xpath("//div[@id=\"postlist\"]/div[1]/table/tbody/tr[1]/td[2]//ignore_js_op");
        Selectable imgs = jsop.xpath("//img[@zoomfile]");
        Selectable idxpath = imgs.xpath("/@id");
        List<String> img_aid = idxpath.all();
        List<String> img_src = page.getHtml().xpath("//div[@id=\"postlist\"]/div[1]/table/tbody/tr[1]/td[2]//ignore_js_op//img[@zoomfile]/@zoomfile").all();
        MyLogger.logger.info("找到图片 {} 个", (img_aid != null ? img_aid.size() : 0));
        parserTopic(page, img_aid, img_src);

    }

    protected void parserTopic(Page page, List<String> img_aid, List<String> img_src) {
        if (img_aid != null && img_aid.size() > 0) {
            List<HKPicImg> topicsList = new ArrayList<HKPicImg>(img_aid.size());
            HKBaseTopic topic = TopicCache.getInstance().pop(page.getUrl().toString());

            for (int i = 0; i < img_aid.size(); i++) {
                String idstr = img_aid.get(i);
                String src = img_src.get(i);
                HKPicImg imgObj = HKPicImg.create(idstr, src);
                imgObj.setTopicId(topic.getId());
                imgObj.setTopicTitle(topic.getTitle());
                topicsList.add(imgObj);
            }
            page.putField("imgList", topicsList);
        } else {
            MyLogger.logger.warn("没有数据");
            page.setSkip(true);
        }
    }

    @Override
    public Site getSite() {

        site.addCookie("Ovo6_2132_saltkey", "k1544GMQ")
                .addCookie("Ovo6_2132_lastvisit", "1524918994")
                .addCookie("Ovo6_2132_auth", "6d1ft8sgrfgG5PZAuFGKyrZjZ%2F2IABBehdpD25Wu53riEQwG5J%2FDCn%2BEfxPt1%2BU8tUewEfYTZe5dG0KZ4BXN5UjXpRZs")
                .addCookie("Ovo6_2132_lastcheckfeed", "3068814%7C1525020806")
                .addCookie("Ovo6_2132_nofavfid", "1")
                .addCookie("Ovo6_2132_smile", "1D1")
                .addCookie("PHPSESSID", "13s4sb84cvmi5v9cpb7hlkt466")
                .addCookie("Ovo6_2132_viewid", "tid_4957034")
                .addCookie("Ovo6_2132_visitedfid", "20D2D18")
                .addCookie("Ovo6_2132_home_diymode", "1")
                .addCookie("Ovo6_2132_myrepeat_rr", "R0")
                .addCookie("Ovo6_2132_ulastactivity", "1525363703%7C0")
                .addCookie("Ovo6_2132_lastact", "1525363975%09forum.php%09forumdisplay")
                .addCookie("Ovo6_2132_forum_lastvisit", "D_18_1525277021D_2_1525277090D_20_1525363975")

                .addHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 7.1.2; zh-cn; Redmi 5 Plus Build/N2G47H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/61.0.3163.128 Mobile Safari/537.36 XiaoMi/MiuiBrowser/9.6.1")
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                .addHeader("Cache-Control", "max-age=0")
                .addHeader("Connection", "keep-alive")
                .addHeader("Host", "www.hkbbcc.xyz");
        return site;
    }

    public interface onNextPage {
        List<HKssttPcTopic> getNextPage();
    }
}
