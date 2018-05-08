package us.codecraft.webmagic.samples.hkbc.model;

public class Anode {
    public String url;
    public String innertext;

    public Anode(String url, String innertext) {
        this.url = url;
        this.innertext = innertext;
    }

    public static Anode create(String url, String innertext) {
        return new Anode(url, innertext);
    }

    public static Anode create(HKssxsPcTopic topic) {
        return new Anode(topic.url, topic.title);
    }
}
