package us.codecraft.webmagic.samples.hkbc.utils;

import org.apache.http.util.TextUtils;

import java.util.UUID;

public class UrlUtils {
    public static String getFileNameFromUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return UUID.randomUUID().toString();
        }
        String[] split = url.split("/");
        if (split.length < 1) {
            return UUID.randomUUID().toString();
        }
        return split[split.length - 1];
    }
}
