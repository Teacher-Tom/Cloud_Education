package xyz.likailing.cloud.common.base.util;


import com.aspose.slides.License;
import com.aspose.slides.Presentation;

import lombok.SneakyThrows;

import java.io.*;


/**
 * @author 不敢喝雪碧 我怕心飞扬
 * @version
 */
public class AsposeUtil {

    private static InputStream slides;

    /**
     * licence 验证
     *
     * @return
     * @throws Exception
     */
    public static boolean getLicenseq() throws Exception {
        boolean result = false;
        try {
            InputStream is = AsposeUtil.class
                    .getResourceAsStream("/license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    /**
     * ppt to pdf
     * @param inputStream
     */
    @SneakyThrows
    public static void ppt2PDF(InputStream inputStream) {
        // 验证License
        if (!getLicenseq()) {
            return;
        }
        try {
            Presentation ppt = new Presentation(inputStream);
            ByteArrayOutputStream dstStream = new ByteArrayOutputStream();
            ppt.save("output.pdf", com.aspose.slides.SaveFormat.Pdf);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}