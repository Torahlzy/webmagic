package us.codecraft.webmagic.samples.hkbc;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.samples.hkbc.cache.ImgCache;
import us.codecraft.webmagic.samples.hkbc.dao.HKDao;
import us.codecraft.webmagic.samples.hkbc.model.HKPicImg;
import us.codecraft.webmagic.samples.hkbc.pipline.ImgFilePipLine;
import us.codecraft.webmagic.samples.hkbc.processor.HKBCImg_FileProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.util.ArrayList;
import java.util.List;

public class PicStart {
    public static void main(String[] args) {
        spSSPics();
    }

    static int currentPicId = 4011;

    /**
     * 抓取图片地址
     */
    public static void spSSPics() {
        final int count = 5;
        List<HKPicImg> picUrl = HKDao.getPicUrl(currentPicId, count);
        String[] urls = new String[picUrl.size()];
        for (int i = 0; i < picUrl.size(); i++) {
            urls[i] = picUrl.get(i).getSrc();
        }
        ImgCache.getInstance().cacheTopic(picUrl);
        Spider.create(new HKBCImg_FileProcessor(new OnNextUrl() {
            @Override
            public List<String> getNextUrl() {
                if (ImgCache.getInstance().getcachSize() < 100) {
                    currentPicId += count;
                    List<HKPicImg> picUrl = HKDao.getPicUrl(currentPicId, count);
                    List<String> urls = new ArrayList<String>();
                    for (int i = 0; i < picUrl.size(); i++) {
                        urls.add(picUrl.get(i).getSrc());
                    }
                    ImgCache.getInstance().cacheTopic(picUrl);
                    return urls;
                }
                return null;
            }
        }))
                .addUrl(urls)
                .setScheduler(new FileCacheQueueScheduler("./filecache/ssttImgFile/"))
                .addPipeline(new ImgFilePipLine())
                .thread(1)
                .run();
    }
}
