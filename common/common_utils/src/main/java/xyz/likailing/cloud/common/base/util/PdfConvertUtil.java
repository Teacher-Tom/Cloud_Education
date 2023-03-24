package xyz.likailing.cloud.common.base.util;

/**
 * @Author 12042
 * @create 2023/3/24 0:15
 */
import cn.hutool.core.util.StrUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

import org.apache.poi.xslf.usermodel.*;
import org.apache.poi.hslf.usermodel.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 类名称：PdfConvertUtil
 * 类描述：转为为PDF工具类
 */
public final class PdfConvertUtil {

    /**
     * pptToPdf
     * @param pptPath PPT文件路径
     * @param pdfDir 生成的PDF文件路径
     * @return
     */
    public static boolean pptToPdf(String pptPath, String pdfDir) {

        if (StrUtil.isEmpty(pptPath)) {
            throw new RuntimeException("word文档路径不能为空");
        }

        if (StrUtil.isEmpty(pdfDir)) {
            throw new RuntimeException("pdf目录不能为空");
        }


        String pdfPath = pdfDir + StrUtil.sub(pptPath, pptPath.lastIndexOf(StrUtil.BACKSLASH), pptPath.lastIndexOf(StrUtil.DOT)) + StrUtil.DOT + "pdf";

        Document document = null;
        HSLFSlideShow hslfSlideShow = null;
        FileOutputStream fileOutputStream = null;
        PdfWriter pdfWriter = null;

        try {
            hslfSlideShow = new HSLFSlideShow(new FileInputStream(pptPath));

            // 获取ppt文件页面
            Dimension dimension = hslfSlideShow.getPageSize();

            fileOutputStream = new FileOutputStream(pdfPath);

            document = new Document();

            // pdfWriter实例
            pdfWriter = PdfWriter.getInstance(document, fileOutputStream);

            document.open();

            PdfPTable pdfPTable = new PdfPTable(1);

            List<HSLFSlide> hslfSlideList = hslfSlideShow.getSlides();

            for (int i=0; i < hslfSlideList.size(); i++) {
                HSLFSlide hslfSlide = hslfSlideList.get(i);
                // 设置字体, 解决中文乱码
                for (HSLFShape shape : hslfSlide.getShapes()) {
                    if (shape instanceof HSLFTextShape) {
                        HSLFTextShape textShape = (HSLFTextShape) shape;
                        for (HSLFTextParagraph textParagraph : textShape.getTextParagraphs()) {
                            for (HSLFTextRun textRun : textParagraph.getTextRuns()) {
                                textRun.setFontFamily("宋体");
                            }
                        }
                    }
                }
                BufferedImage bufferedImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);

                Graphics2D graphics2d = bufferedImage.createGraphics();

                graphics2d.setPaint(Color.white);
                graphics2d.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 12));

                hslfSlide.draw(graphics2d);

                graphics2d.dispose();

                Image image = Image.getInstance(bufferedImage, null);
                image.scalePercent(50f);

                // 写入单元格
                pdfPTable.addCell(new PdfPCell(image, true));
                document.add(image);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (pdfWriter != null) {
                    pdfWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     *
     * @Title: pptxToPdf
     * @param pptPath PPT文件路径
     * @param pdfDir 生成的PDF文件路径
     */
    public static boolean pptxToPdf(String pptPath, String pdfDir) {

        if (StrUtil.isEmpty(pptPath)) {
            throw new RuntimeException("word文档路径不能为空");
        }

        if (StrUtil.isEmpty(pdfDir)) {
            throw new RuntimeException("pdf目录不能为空");
        }

        String pdfPath = pdfDir + StrUtil.sub(pptPath, pptPath.lastIndexOf(StrUtil.BACKSLASH), pptPath.lastIndexOf(StrUtil.DOT)) + StrUtil.DOT + "pdf";

        Document document = null;

        XMLSlideShow slideShow = null;


        FileOutputStream fileOutputStream = null;

        PdfWriter pdfWriter = null;


        try {

            slideShow = new XMLSlideShow(new FileInputStream(pptPath));

            Dimension dimension = slideShow.getPageSize();

            fileOutputStream = new FileOutputStream(pdfPath);

            document = new Document(new com.itextpdf.text.Rectangle((float)dimension.getWidth()*2,(float)dimension.getHeight()*2));

            pdfWriter = PdfWriter.getInstance(document, fileOutputStream);

            document.open();

            PdfPTable pdfPTable = new PdfPTable(1);

            List<XSLFSlide> slideList = slideShow.getSlides();

            for (int i = 0, row = slideList.size(); i < row; i++) {

                XSLFSlide slide = slideList.get(i);
                // 设置字体, 解决中文乱码
                for (XSLFShape shape : slide.getShapes()) {
                    if (shape instanceof XSLFTextShape) {
                        XSLFTextShape textShape = (XSLFTextShape) shape;
                        for (XSLFTextParagraph textParagraph : textShape.getTextParagraphs()) {
                            for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
                                textRun.setFontFamily("宋体");
                            }
                        }
                    }
                }

                BufferedImage bufferedImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);

                Graphics2D graphics2d = bufferedImage.createGraphics();
                graphics2d.setPaint(Color.white);
                graphics2d.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 12));
                slide.draw(graphics2d);


                Image image = Image.getInstance(bufferedImage, null);
                image.scalePercent(100f);

                // 写入单元格
                pdfPTable.addCell(new PdfPCell(image, true));
                document.add(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (pdfWriter != null) {
                    pdfWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
