package xyz.likailing.cloud;

import com.hankcs.hanlp.HanLP;
import org.junit.Test;
import xyz.likailing.cloud.common.base.util.SummaryUtils;

import java.util.List;

/**
 * @Author 12042
 * @create 2023/2/28 10:46
 */

public class TestSummary {
    @Test
    public void testSummary(){
        String text = "1、\tJSP引擎是支持JSP程序的Web容器，负责运行JSP，并将有关结果发送到客户端。\n" +
                "2、\t目前流行的JSP引擎之一是Tomcat安装Tomcat服务器，首先要安装JDK。\n" +
                "3、\tJSP页面必须保存在Web服务目录中，Tomcat服务器的webapps下的目录都可以作为Web服务目录。如果想让webapps以外的其他的目录作为Web服务目录，必须要修改Tomcat服务器下conf文件夹中的server.xml文件，并重新启动Tomcat服务器。\n" +
                "4、\t当服务器上的一个JSP页面被第一次请求执行时，服务器上的JSP引擎首先将JSP页面文件转译成一个Java文件，再将这个Java文件编译生成字节码文件，然后通过执行字节码文件响应客户的请求。\n" +
                "5、\t当多个客户请求一个JSP页面时，Tomcat服务器为每个客户启动一个线程，该线程负责执行常驻内存的字节码文件来响应相应客户的请求。这些线程由Tomcat服务器来管理。\n" +
                "6、\tJSP与Servlet的关系，JSP是转换成Servlet来运行的。\n";
        String text2 = "1、\tJSP页面的构成：普通的HTML标记（客户端浏览器执行）、JSP标记、成员变量和方法声明、Java程序片、Java表达式（JSP引擎处理并将结果发送给用户浏览器）\n" +
                "2、\t成员变量为所有用户共享，任何用户对成员变量的操作都会影响其他用户，synchronized关键字保证一次只有一个线程执行\n" +
                "3、\t多用户访问JSP页面，其程序片会被执行多次，分别在不同线程中，其局部变量互不干扰。\n" +
                "4、\tpage指令标记用来定义整个JSP页面的一些属性，常用的有contentType和import。\n" +
                "5、\tinclude指令标记在编译阶段就处理所需要的文件，被处理的文件在逻辑与语法上依赖于当前JSP页面，优点是速度快；include动作标记是在JSP页面运行时才处理文件，在逻辑与语法上独立于当前JSP页面，更加灵活。\n" +
                "6、\t注意成员变量和方法的声明和普通Java程序片内变量声明的区别:<%! %> 、\n" +
                "<% %>\n" +
                "7、\t在多次执行的情况下，成员变量和局部变量的值会如何变化，这个很重要，也和容易错。\n" +
                "8、\tJSP页面中Java程序片是顺序执行的，某个Java程序片中的变量在后续的程序片中依然有效。\n" +
                "9、\tJSP的注释在客户端不可见，HTML的可见\n" +
                "10、\tPage指令标记的contentType只能指定一个值\n" +
                "11、\tForward动作标记是用于转向新的页面，转后之后地址栏不变，param动作标记是传递参数的\n";
        List<String> wods = SummaryUtils.textRank(text,10);
        System.out.println(wods);
        List<String> summary = HanLP.extractSummary(text2, 5);
        List<String> wordInfos = HanLP.extractKeyword(text2,10);
        System.out.println(wordInfos);
        System.out.println(summary);
    }
}
