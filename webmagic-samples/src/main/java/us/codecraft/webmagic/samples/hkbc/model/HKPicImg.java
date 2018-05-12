package us.codecraft.webmagic.samples.hkbc.model;

import org.apache.http.util.TextUtils;
import us.codecraft.webmagic.samples.hkbc.HKMainStart;

public class HKPicImg extends BaseModel {
    String aid;//从网页得来的id
    String src;
    int topicId;//所属主题的数据库id
    String topicTitle;

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

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

    public HKPicImg() {
    }

    @Override
    public String toString() {
        return "HKPicImg{" +
                "aid='" + aid + '\'' +
                ", src='" + src + '\'' +
                ", topicId=" + topicId +
                ", topicTitle='" + topicTitle + '\'' +
                '}';
    }
}
