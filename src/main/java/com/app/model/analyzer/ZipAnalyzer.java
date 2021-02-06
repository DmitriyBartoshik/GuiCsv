package com.app.model.analyzer;

import com.app.gui.FrameListener;
import com.app.model.monitor.FileNameCorrector;
import com.app.model.monitor.FileNameSneak;
import com.app.utils.WearUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import static com.app.utils.NumberConstants.*;
import static com.app.utils.StringConstants.*;

public class ZipAnalyzer {
    private FileNameCorrector fileNameCorrector;
    private FileNameSneak fileNameSneak;
    private List<String> paths;
    private FrameListener frameListener;

    public void start(List<String> paths, FrameListener frameListener) {
        this.frameListener = frameListener;
        init(paths);
        try {
            this.frameListener.begin();
            scan();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void scan() throws IOException {
        String endZipPathNormal = paths.get(END_DIRECTORY_PATH) + File.separator + paths.get(ZIP_FILE_NAME);
        String endZipPathSneak = paths.get(END_DIRECTORY_PATH) + File.separator + PREFIX_WARNING_ZIP + paths.get(ZIP_FILE_NAME);
        String doneTextFilePath = paths.get(END_DIRECTORY_PATH) + File.separator + DONE_TXT;
        String toDoTextFilePath = paths.get(END_DIRECTORY_PATH) + File.separator + TODO_TXT;

        /* use this code, if file names contain cyrillic symbols
         * need testing
         */
        ZipFile zipFile = new ZipFile(paths.get(ZIP_FILE_PATH), Charset.forName(CP866));

        /* use this code, if file names not contain cyrillic symbols
         *   ZipFile zipFile = new ZipFile(paths.get(ZIP_FILE_PATH));
         */

        final ZipOutputStream normalZos = new ZipOutputStream(new FileOutputStream(endZipPathNormal));
        final ZipOutputStream sneakZos = new ZipOutputStream(new FileOutputStream(endZipPathSneak));
        ZipOutputStream currentZos = null;


        FileWriter correctorWriter = new FileWriter(doneTextFilePath, false);
        FileWriter sneakWriter = new FileWriter(toDoTextFilePath, false);

        fileNameCorrector.setWriter(correctorWriter);
        fileNameSneak.setWriter(sneakWriter);

        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while (entries.hasMoreElements()) {
            ZipEntry entryIn = entries.nextElement();

            String originalFileName = entryIn.getName();
            fileNameCorrector.setOriginalFileName(originalFileName);

            String transformFileName = fileNameCorrector
                    .spaceDelete()
                    .dashDelete()
                    .semicolonDelete()
                    .underScoreDelete()
                    .getTransformFileName();

            boolean isOriginAfterSneak = fileNameSneak.setTransformFileName(transformFileName)
                    .checkCyrillic()
                    .checkGeneralTemplate()
                    .checkFileNameById()
                    .checkFileNameByIndexCategory()
                    .checkFileNameByMaskNumber()
                    .checkFileNameBySize()
                    .isOriginName();


            ZipEntry zipEntry = new ZipEntry(transformFileName);

            if (isOriginAfterSneak) {
                currentZos = normalZos;
            } else {
                currentZos = sneakZos;
            }

            currentZos.putNextEntry(zipEntry);
            InputStream is = zipFile.getInputStream(entryIn);

            /* use this code, for real application
             *  for (int c = is.read(); c != -1; c = is.read()) {
             *   currentZos.write(c);
             * }
             */

            /*
             *  use this code, if need testing
             */
            byte[] buf = new byte[1024];
            int c;
            while ((c = is.read(buf)) > 0) {
                currentZos.write(c);
            }
            currentZos.closeEntry();
        }
        currentZos.close();
        normalZos.close();
        sneakZos.close();
        correctorWriter.close();
        sneakWriter.close();
        if (frameListener != null) {
            frameListener.end();
        }
    }

    public void init(List<String> paths) {
        this.paths = paths;
        List<String> listId = WearUtils.getIdFromCsv(paths.get(CSV_FILE_PATH));
        fileNameCorrector = new FileNameCorrector();
        fileNameSneak = new FileNameSneak(listId);
    }
}
