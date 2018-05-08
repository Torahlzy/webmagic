package us.codecraft.webmagic.samples.hkbc;

import org.junit.Test;
import us.codecraft.webmagic.samples.hkbc.model.Anode;
import us.codecraft.webmagic.samples.hkbc.query.CreateResultXml;

import java.util.ArrayList;
import java.util.List;

public class XmlTest {
    @Test
    public  void testCreateHtml() {
        List<Anode> list = new ArrayList<Anode>();
        list.add(Anode.create("http://www.baidu.com", "guge"));
        CreateResultXml.createResult(list,"test");
    }
}
