package us.codecraft.webmagic.samples.hkbc.model;

public class HKssxsPcTopic {
    String title;//标题
    String url;//网址全部

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static HKssxsPcTopic create() {
        return new HKssxsPcTopic();
    }

    public HKssxsPcTopic() {
    }

    @Override
    public String toString() {
        return "HKssxsPcTopic{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
