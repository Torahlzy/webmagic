package us.codecraft.webmagic.samples.hkbc.model;

import org.apache.http.util.TextUtils;
import us.codecraft.webmagic.samples.hkbc.HKMainStart;

public class HKPicImg {
    String aid;
    String src;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public static HKPicImg create(String aid, String src) {
        if (!TextUtils.isEmpty(src)) {
            if (!src.startsWith("http")) {
                src = HKMainStart.host + src;
            }
        }
        return new HKPicImg(aid, src);
    }

    public HKPicImg(String aid, String src) {
        this.aid = aid;
        this.src = src;
    }

    @Override
    public String toString() {
        return "HKPicImg{" +
//                "aid=" + aid +
                ", src='" + src + '\'' +
                '}';
    }
}
