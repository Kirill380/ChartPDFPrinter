package com.redkite.image_upload;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Report {
    private String name;
    private String image;

    public Report(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public InputStream getImage() throws UnsupportedEncodingException, TranscoderException {
        InputStream svg = new ByteArrayInputStream(image.getBytes());
        ByteArrayOutputStream png = new ByteArrayOutputStream();
        PNGTranscoder t = new PNGTranscoder();
        t.transcode(new TranscoderInput(svg), new TranscoderOutput(png));
        return new ByteArrayInputStream(png.toByteArray());
    }
}
