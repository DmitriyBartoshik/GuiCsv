package com.app.model.monitor;

import java.io.FileWriter;
import java.io.IOException;

import static com.app.utils.StringConstants.*;

public class FileNameCorrector {
    private String originalFileName;
    private String transformFileName;
    private FileWriter writer;
    private boolean isHeaderWritten = false;

    /**
     * удаляем пробелы в имени файла
     */

    public FileNameCorrector spaceDelete() throws IOException {
        String fileNameWithoutSpace = this.transformFileName.replaceAll("\\s", "");
        if (!this.transformFileName.equals(fileNameWithoutSpace)) {
            writeHeaderLog();
            writer.write(SPACE_DELETE_MESSAGE + "\r\n");
        }
        this.transformFileName = fileNameWithoutSpace;
        return this;
    }

    /**
     * удаляем точку с запятой в имени файла
     */
    public FileNameCorrector semicolonDelete() throws IOException {
        String fileNameWithoutSemicolon = this.transformFileName.replaceAll("\\;", "");

        if (!this.transformFileName.equals(fileNameWithoutSemicolon)) {
            writeHeaderLog();
            writer.write(SEMICOLON_DELETE_MESSAGE + "\r\n");
        }
        this.transformFileName = fileNameWithoutSemicolon;
        return this;
    }

    /**
     * удаляем дублированные тире в имени файла
     */
    public FileNameCorrector dashDelete() throws IOException {
        String fileNameWithoutDash = this.transformFileName.replaceAll("\\-+", "-");

        if (!this.transformFileName.equals(fileNameWithoutDash)) {
//            System.out.println("Удалено тире  в исходном названии " + originalFileName);
            writeHeaderLog();
            writer.write(DASH_DELETE_MESSAGE + "\r\n");
        }
        this.transformFileName = fileNameWithoutDash;
        return this;
    }

    /**
     * удаляем дублированные нижние подчеркивания в имени файла
     */
    public FileNameCorrector underScoreDelete() throws IOException {
        String fileNameWithoutUnderScore = this.transformFileName.replaceAll("\\_+", "_");

        if (!this.transformFileName.equals(fileNameWithoutUnderScore)) {
            writeHeaderLog();
            writer.write(UNDER_SCORE_DELETE_MESSAGE + "\r\n");
        }
        this.transformFileName = fileNameWithoutUnderScore;
        return this;
    }

    private void writeHeaderLog() throws IOException {
        if (!isHeaderWritten) {
            writer.write("\r\n" + HEADER_DESIGN + originalFileName + HEADER_DESIGN + "\r\n");
            isHeaderWritten = true;
        }
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
        this.transformFileName = originalFileName;
        isHeaderWritten = false;
    }

    public void setWriter(FileWriter writer) {
        this.writer = writer;
    }

    public String getTransformFileName() {
        return transformFileName;
    }
}
