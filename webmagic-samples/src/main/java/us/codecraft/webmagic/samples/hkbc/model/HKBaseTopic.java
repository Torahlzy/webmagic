package us.codecraft.webmagic.samples.hkbc.model;

public abstract class HKBaseTopic {
    protected String title;//标题
    protected String url;//网址全部

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

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
