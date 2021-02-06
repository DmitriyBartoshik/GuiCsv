package com.app.gui;

import com.app.utils.CustomFileFilter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import java.io.File;

import static com.app.utils.CustomFileFilter.FileFilterEnum.CSV;
import static com.app.utils.CustomFileFilter.FileFilterEnum.ZIP;
import static com.app.utils.NumberConstants.WINDOWS_HEIGHT;
import static com.app.utils.NumberConstants.WINDOWS_WIDTH;
import static com.app.utils.StringConstants.*;

public class ZipAnalyzerFrame extends JFrame implements FrameListener {
    private static final long serialVersionUID = 1L;

    private JButton csvButton;
    private JButton zipButton;
    private JButton saveButton;
    private JButton startButton;
    private JTextField endText;
    private JFileChooser fileChooser;
    private ZipAnalyzerPresenter presenter;

    public ZipAnalyzerFrame(ZipAnalyzerPresenter presenter) {
        super(APPLICATION_TITTLE);
        this.presenter = presenter;
    }

    public void start() {
        if (presenter != null) {
            presenter.setListener(this);
        }
        initGui();
    }

    private void initGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel contents = new JPanel();
        fileChooser = new JFileChooser();

        csvButton = new JButton(CSV_CHANGE_BUTTON_TEXT);
        csvButton.addActionListener((e) -> {
            CustomFileFilter eff = new CustomFileFilter(CSV);

            fileChooser.setDialogTitle(CSV_CHANGE_BUTTON_TEXT);
            fileChooser.addChoosableFileFilter(eff);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(ZipAnalyzerFrame.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                presenter.setCsvPath(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        zipButton = new JButton(ZIP_CHANGE_BUTTON_TEXT);
        zipButton.addActionListener((e) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle(ZIP_CHANGE_BUTTON_TEXT);
            CustomFileFilter eff = new CustomFileFilter(ZIP);
            fileChooser.addChoosableFileFilter(eff);
            fileChooser.setAcceptAllFileFilterUsed(false);

            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(ZipAnalyzerFrame.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                presenter.setZipPath(fileChooser.getSelectedFile().getAbsolutePath());
                presenter.setZipName(fileChooser.getSelectedFile().getName());

            }
        });

        saveButton = new JButton(DIRECTORY_CHANGE_BUTTON_TEXT);
        saveButton.addActionListener((e) -> {
            fileChooser.setDialogTitle(DIRECTORY_CHANGE_BUTTON_TEXT);
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setFileFilter(new FileFilter() {

                @Override
                public boolean accept(File f) {
                    return f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return DIRECTORY_ONLY;
                }

            });
            int result = fileChooser.showSaveDialog(ZipAnalyzerFrame.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                presenter.setEndDirectoryPath(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        startButton = new JButton(START_BUTTON_TEXT);
        startButton.addActionListener((e -> {
            if (presenter != null) {
                startPresenter();
            }
        }));

        endText = new JTextField();

        contents.add(csvButton);
        contents.add(zipButton);
        contents.add(saveButton);
        contents.add(startButton);
        contents.add(endText);

        setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
        setContentPane(contents);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void begin() {
        startButton.setEnabled(false);
        csvButton.setEnabled(false);
        zipButton.setEnabled(false);
        saveButton.setEnabled(false);
    }

    @Override
    public void end() {
        startButton.setEnabled(true);
        startButton.setText(START_BUTTON_TEXT_AFTER_WORK);
        csvButton.setEnabled(true);
        zipButton.setEnabled(true);
        saveButton.setEnabled(true);
        endText.setText(INFO_TEXT_AFTER_WORK);
    }

    void startPresenter() {
        if (presenter.checkAllPath(ZipAnalyzerFrame.this)) {
            presenter.start();
        }
    }
}
