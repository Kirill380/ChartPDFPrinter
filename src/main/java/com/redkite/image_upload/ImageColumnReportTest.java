package com.redkite.image_upload;


import java.awt.Color;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.*;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;

public class ImageColumnReportTest extends BaseDjReportTest {

    private Collection<Report> reports;

    public DynamicReport buildReport() throws Exception {
        Style style = new StyleBuilder(false).setHorizontalAlign(HorizontalAlign.CENTER)
                .setStretching(Stretching.RELATIVE_TO_TALLEST_OBJECT)
                .build();


        Style headerStyle = new StyleBuilder(false)
                .setBackgroundColor(Color.white)
                .setPaddingBottom(10)
                .setBorderBottom(new Border(Border.BORDER_WIDTH_1POINT, Border.BORDER_STYLE_SOLID, new Color(153, 199, 228)))
                .setFont(Font.TIMES_NEW_ROMAN_MEDIUM_BOLD)
                .build();


        Style titleStyle = new StyleBuilder(false)
                .setHorizontalAlign(HorizontalAlign.RIGHT)
                .setVerticalAlign(VerticalAlign.MIDDLE)
                .setPaddingTop(20)
                .setBorderTop(new Border(Border.BORDER_WIDTH_1POINT, Border.BORDER_STYLE_SOLID, new Color(181, 181, 188)))
                .setFont(Font.TIMES_NEW_ROMAN_MEDIUM_BOLD)
                .build();

        Style footerStyle = new StyleBuilder(false)
                .setTextColor(Color.LIGHT_GRAY)
                .setFont(Font.ARIAL_SMALL)
                .build();


        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setMargins(25, 25, 20, 20)
                .setUseFullPageWidth(true)
                .setDetailHeight(300)
                .addAutoText(AutoText.AUTOTEXT_PAGE_X, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT, AutoText.DEFAULT_WIDTH, AutoText.DEFAULT_WIDTH2, footerStyle)
                .addAutoText("vEPC vs EPC #00003184 Report#1 Created at 10-28-2015. 19:35", AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_LEFT, AutoText.DEFAULT_WIDTH*5, footerStyle)
                .addFirstPageImageBanner("header.png", 555, 50, ImageBanner.ALIGN_RIGHT)
                .setTitle("vEPC vs EPC #00003184 Report#1")
                .setDefaultStyles(titleStyle, new Style(), headerStyle, new Style());

        AbstractColumn imageColumn = ColumnBuilder.getNew()
                .setTitle("Traffic Sources by Profile")
                .setColumnProperty("image", InputStream.class.getName())
                .setWidth(540)
                .setImageScaleMode(ImageScaleMode.FILL_PROPORTIONALLY)
                .setColumnType(1)
                .setStyle(style)
                .build();


        drb.addColumn(imageColumn);
        return drb.build();
    }


    public void setReport(Report report) {
        if(reports == null) {
            reports = new ArrayList<>();
        }
        reports.add(report);
    }

    @Override
    protected JRDataSource getDataSource() {
        return new JRBeanCollectionDataSource(reports);
    }
}

