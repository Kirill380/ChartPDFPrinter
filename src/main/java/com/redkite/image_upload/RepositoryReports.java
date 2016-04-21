package com.redkite.image_upload;

import java.util.ArrayList;
import java.util.Collection;

public class RepositoryReports {

    private static Collection<Report> dummyCollection = new ArrayList<Report>();


    public static Collection getDummyCollection() {
        return dummyCollection;
    }
}
