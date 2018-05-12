package us.codecraft.webmagic.samples.hkbc.pipline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.samples.hkbc.MyLogger;
import us.codecraft.webmagic.samples.hkbc.model.HKssttPcTopic;
import us.codecraft.webmagic.samples.hkbc.utils.UrlUtils;

import java.io.*;

public class ImgFilePipLine implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        byte[] img = resultItems.get("img");
        HKssttPcTopic topic = resultItems.get("topic");
        String url = resultItems.get("url");
        if (img == null) {
            MyLogger.logger.warn("img对象为空");
            return;
        }
        String title = topic.getTitle();
        File dir = new File("./img/" + title);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File imgFile = new File(dir, UrlUtils.getFileNameFromUrl(url));
        try {
            if (imgFile.exists()) {
                imgFile.delete();
            }
            imgFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(imgFile);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(img);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
