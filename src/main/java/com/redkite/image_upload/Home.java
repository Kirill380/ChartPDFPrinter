package com.redkite.image_upload;

import net.sf.jasperreports.engine.util.JRJdk14ImageReader;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileOutputStream;

@Controller
public class Home {

    @RequestMapping({"/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @RequestMapping("/upload")
    public void uploadImage(Model model, @RequestParam("image") String image) throws Exception {
        Report report = new Report("Test", image);
        FileOutputStream out = new FileOutputStream("D:\\\\image.png");
        IOUtils.copy(report.getImage(), out);
        out.close();
        ImageColumnReportTest printer = new ImageColumnReportTest();
        printer.setReport(report);
        printer.testReport();
    }

}
