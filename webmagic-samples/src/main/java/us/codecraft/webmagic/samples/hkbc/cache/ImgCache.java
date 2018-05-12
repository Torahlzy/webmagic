package us.codecraft.webmagic.samples.hkbc.cache;

import us.codecraft.webmagic.samples.hkbc.model.HKPicImg;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存即将抓取的主题信息
 */
public class ImgCache {
    private static ImgCache instance;

    private ImgCache() {
    }

    public static ImgCache getInstance() {
        if (instance == null) {
            synchronized (ImgCache.class) {
                if (instance == null) {
                    instance = new ImgCache();
                    return instance;
                }
            }
        }
        return instance;
    }

    Map<String, HKPicImg> cache = Collections.synchronizedMap(new HashMap<String, HKPicImg>());

    public void cacheTopic(List<? extends HKPicImg> list) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                cache.put(list.get(i).getSrc(), list.get(i));
            }
        }
    }

    public HKPicImg pop(String url) {
        return cache.remove(url);
    }

    public int getcachSize(){
        return cache.size();
    }

}
