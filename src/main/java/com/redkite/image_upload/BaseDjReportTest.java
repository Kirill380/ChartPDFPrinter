package com.redkite.image_upload;


import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.util.SortUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;

import java.io.FileOutputStream;
import java.util.*;

public abstract class BaseDjReportTest {
    protected JasperPrint jp;
    protected JasperReport jr;
    protected Map params = new HashMap();
    protected DynamicReport dr;

    public abstract DynamicReport buildReport() throws Exception;

    public void testReport() throws Exception {
        dr = buildReport();
        JRDataSource ds = getDataSource();
        jr = DynamicJasperHelper.generateJasperReport(dr, getLayoutManager(), params);
        jp = JasperFillManager.fillReport(jr, params, ds);
        List<JasperPrint> list = Arrays.asList(jp, jp, jp);
        JasperPrint jpMain = JasperFillManager.fillReport(jr, params, ds);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getPages().size(); j++) {
                jpMain.addPage(list.get(i).getPages().get(j));
            }
        }

        exportReport(jpMain);
    }

    protected LayoutManager getLayoutManager() {
        return new ClassicLayoutManager();
    }

    protected void exportReport(JasperPrint jp) throws Exception {
        FileOutputStream out = new FileOutputStream("D:\\\\reports\\test.pdf");
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jp));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.setConfiguration(getConfiguration());
        exporter.exportReport();
        out.close();
    }

    private static PdfReportConfiguration getConfiguration() {
        SimplePdfReportConfiguration configuration = new SimplePdfReportConfiguration();
        return configuration;
    }


    protected JRDataSource getDataSource() {
        Collection dummyCollection = RepositoryReports.getDummyCollection();
        dummyCollection = SortUtils.sortCollection(dummyCollection, dr.getColumns());
        return new JRBeanCollectionDataSource(dummyCollection);
    }

}
