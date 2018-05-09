package us.codecraft.webmagic.samples.hkbc.query;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import us.codecraft.webmagic.samples.hkbc.model.Anode;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class CreateResultXml {

    public static void createResult(List<Anode> alist, String title) {
        if (alist == null || alist.size() <= 0) {
            return;
        }
        // 创建DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 创建DocumentBuilder
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        if (builder == null) {
            return;
        }
        // 创建html基础
        Document document = builder.newDocument();
        Element html = document.createElement("html");
        Element head = document.createElement("head");
        html.appendChild(head);
        Element meta = document.createElement("meta");
        meta.setAttribute("http-equiv", "Content-Type");
        meta.setAttribute("content", "application/xhtml+xml; charset=utf-8");
        head.appendChild(meta);
        document.appendChild(html);

        Element body = document.createElement("body");
        html.appendChild(body);
        //增加结果
        for (int i = 0; i < alist.size(); i++) {
            Element a = document.createElement("a");
            Anode anode = alist.get(i);
            a.setTextContent((i + 1) + "." + anode.innertext);
            a.setAttribute("href", anode.url);
            body.appendChild(a);
            body.appendChild(document.createElement("br"));
        }
        saveToFile(document, title);
    }

    private static void saveToFile(Document document, String title) {

        // 创建TransformerFactory对象
        TransformerFactory tff = TransformerFactory.newInstance();
        // 创建Transformer对象
        Transformer tf = null;
        try {
            tf = tff.newTransformer();
            File file = new File("quryresult/" + title + ".html");

            if (file.exists()) {
                file.delete();
            } else if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            // 使用Transformer的transform()方法将DOM树转换成XML
            tf.transform(new DOMSource(document), new StreamResult(file));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
