package us.codecraft.webmagic.samples.hkbc.cache;

import us.codecraft.webmagic.samples.hkbc.model.HKBaseTopic;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存即将抓取的主题信息
 */
public class TopicCache {
    private static TopicCache instance;

    private TopicCache() {
    }

    public static TopicCache getInstance() {
        if (instance == null) {
            synchronized (TopicCache.class) {
                if (instance == null) {
                    instance = new TopicCache();
                    return instance;
                }
            }
        }
        return instance;
    }

    Map<String, HKBaseTopic> cache = Collections.synchronizedMap(new HashMap<String, HKBaseTopic>());

    public void cacheTopic(List<? extends HKBaseTopic> list) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                cache.put(list.get(i).getUrl(), list.get(i));
            }
        }
    }

    public HKBaseTopic pop(String url) {
        return cache.remove(url);
    }

    public int getcachSize(){
        return cache.size();
    }

}
