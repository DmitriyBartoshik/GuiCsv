package com.app;

import com.app.gui.ZipAnalyzerFrame;
import com.app.gui.ZipAnalyzerPresenter;
import com.app.model.analyzer.ZipAnalyzer;

public class Main {

    public static void main(String[] args) {
        ZipAnalyzer analyzer=new ZipAnalyzer();
        ZipAnalyzerPresenter presenter = new ZipAnalyzerPresenter(analyzer);
        ZipAnalyzerFrame frame = new ZipAnalyzerFrame(presenter);
        frame.start();
    }
}

