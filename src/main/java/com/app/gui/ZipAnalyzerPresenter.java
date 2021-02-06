package com.app.gui;

import com.app.model.analyzer.ZipAnalyzer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.app.utils.NumberConstants.*;
import static com.app.utils.StringConstants.*;

public class ZipAnalyzerPresenter {
    private ZipAnalyzer zipAnalyzer;
    private List<String> paths = new ArrayList<>(Arrays.asList(CSV_FILE_DEFAULT_PATH, ZIP_FILE_DEFAULT_PATH, END_DIRECTORY_DEFAULT_PATH, ZIP_FILE_DEFAULT_NAME));
    private FrameListener listener;

    public ZipAnalyzerPresenter(ZipAnalyzer analyzer) {
        this.zipAnalyzer = analyzer;
    }

    public void start() {
        zipAnalyzer.start(paths, new FrameListener() {
            @Override
            public void begin() {
                listener.begin();
            }

            @Override
            public void end() {
                listener.end();
            }
        });
    }

    public boolean checkAllPath(Component component) {
        for (String path : paths) {
            System.out.println(path);
            if (path.equals(DEFAULT_PATH)) {
                showErrorDialog(component, WARNING_DIALOG_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public void showErrorDialog(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message);
    }

    public void setCsvPath(String csvFilePath) {
        paths.set(CSV_FILE_PATH, csvFilePath);
    }

    public void setZipPath(String zipFilePath) {
        paths.set(ZIP_FILE_PATH, zipFilePath);
    }

    public void setEndDirectoryPath(String endDirectoryPath) {
        paths.set(END_DIRECTORY_PATH, endDirectoryPath);
    }

    public void setZipName(String zipFileName) {
        paths.set(ZIP_FILE_NAME, zipFileName);
    }

    public void setListener(FrameListener listener) {
        this.listener = listener;
    }
}
