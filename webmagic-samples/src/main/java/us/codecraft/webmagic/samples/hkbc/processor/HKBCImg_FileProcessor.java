package us.codecraft.webmagic.samples.hkbc.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.samples.hkbc.OnNextUrl;
import us.codecraft.webmagic.samples.hkbc.cache.ImgCache;
import us.codecraft.webmagic.samples.hkbc.dao.HKDao;
import us.codecraft.webmagic.samples.hkbc.model.HKPicImg;
import us.codecraft.webmagic.samples.hkbc.model.HKssttPcTopic;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.5.1
 */
public class HKBCImg_FileProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(10);
    OnNextUrl nextPage;

    public HKBCImg_FileProcessor(OnNextUrl nextPage) {
        this.nextPage = nextPage;
    }

    @Override
    public void process(Page page) {
        //获取下一个页面的地址
        List<String> nextPage = this.nextPage.getNextUrl();
        if (nextPage != null && nextPage.size() > 0) {
            page.addTargetRequests(nextPage);
        }
        HKPicImg pop = ImgCache.getInstance().pop(page.getUrl().toString());
        HKssttPcTopic ssttTopic = HKDao.getSsttByTopicId(pop.getTopicId());

        page.putField("topic", ssttTopic);
        page.putField("img", page.getBytes());
        page.putField("url", page.getUrl().toString());

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


}
