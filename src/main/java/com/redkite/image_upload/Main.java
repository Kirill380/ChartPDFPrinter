package com.redkite.image_upload;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException, TranscoderException {
        InputStream svgImage = Main.class.getClassLoader().getResourceAsStream("chart.svg");
        OutputStream pngImage = new FileOutputStream("chart.png");
        PNGTranscoder t = new PNGTranscoder();

        t.transcode(new TranscoderInput(svgImage), new TranscoderOutput(pngImage));
        pngImage.flush();
        pngImage.close();
        svgImage.close();
    }
}


