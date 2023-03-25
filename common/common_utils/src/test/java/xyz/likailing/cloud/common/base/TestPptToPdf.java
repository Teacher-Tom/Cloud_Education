package xyz.likailing.cloud.common.base;


import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import org.junit.Test;
import xyz.likailing.cloud.common.base.util.AsposeUtil;
import xyz.likailing.cloud.common.base.util.PdfConvertUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author 12042
 * @create 2023/3/24 0:03
 */

public class TestPptToPdf {
    @Test
    public void testPdf(){
        String path = "F:\\资料\\服务外包\\去年作品\\2104082—BugFly^2—【A21】基于服务编排的银行存款产品开发【三湘银行】—项目简介PPT.pptx";
        boolean b = PdfConvertUtil.pptxToPdf(path, "F:\\资料\\服务外包\\去年作品");
        System.out.println(b);
    }
    @Test
    public void testPresentation(){
        Presentation presentation = new Presentation("F:\\资料\\服务外包\\去年作品\\2104082—BugFly^2—【A21】基于服务编排的银行存款产品开发【三湘银行】—项目简介PPT.pptx");
        presentation.save("output.pdf", SaveFormat.Pdf);

    }
    @Test
    public void testPptToPdf() throws FileNotFoundException {
        File file = new File("F:\\资料\\服务外包\\去年作品\\2104082—BugFly^2—【A21】基于服务编排的银行存款产品开发【三湘银行】—项目简介PPT.pptx");
        FileInputStream fileInputStream = new FileInputStream(file);
        AsposeUtil.ppt2PDF(fileInputStream);
    }
}
