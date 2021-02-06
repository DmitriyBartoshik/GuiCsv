package com.app.model.monitor;


import com.app.utils.WearUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.app.utils.StringConstants.*;

public class FileNameSneak {
    private String transformFileName;
    private FileWriter writer;
    private boolean isHeaderWritten = false;
    private List<String> listId;
    private boolean isOriginName = true;

    public FileNameSneak(List<String> listId) {
        this.listId = listId;
    }

    /**
     * проверяем наличие кириллицы в имени файла
     */
    public FileNameSneak checkCyrillic() throws IOException {
        boolean isCyrillic = transformFileName.chars()
                .mapToObj(Character.UnicodeBlock::of)
                .anyMatch(b -> b.equals(Character.UnicodeBlock.CYRILLIC));
        if (isCyrillic) {
            writeHeaderLog();
            if (writer != null) {
                writer.write(transformFileName + CYRILLIC_SYMBOL_WARNING_MESSAGE + "\r\n");
                this.isOriginName = false;
            }
        }
        return this;
    }

    public FileNameSneak checkGeneralTemplate() throws IOException {
        boolean isMatchesPattern = transformFileName.matches(GENERAL_TEMPLATE);
        if (!isMatchesPattern) {
            writeHeaderLog();
            writer.write(transformFileName + FILE_NAME_NOT_TRUE_GENERAL_TEMPLATE_WARNING_MESSAGE + "\r\n");
            this.isOriginName = false;
        }
        return this;
    }

    public FileNameSneak checkFileNameById() throws IOException {
        boolean isIdValid = false;
        String checkIdExpression = "";
        for (String id : listId) {
            checkIdExpression = "^" + id + "(_{1})" + "(.+)";
            isIdValid = transformFileName.matches(checkIdExpression);
            if (isIdValid) {
                break;
            }
        }
        if (!isIdValid) {
            writeHeaderLog();
            writer.write(transformFileName + ID_NOT_EXIST + "\r\n");
            this.isOriginName = false;
        }
        return this;
    }

    public FileNameSneak checkFileNameByIndexCategory() throws IOException {
        boolean isIndexCategoryValid = false;
        String indexCategoryTemplate = "";
        List<String> indexCategories = WearUtils.getAllIndexCategory();
        for (String indexCategory : indexCategories) {
            indexCategoryTemplate = "_" + indexCategory + "_";
            if (transformFileName.contains(indexCategoryTemplate)) {
                isIndexCategoryValid = true;
            }
        }
        if (!isIndexCategoryValid) {
            writeHeaderLog();
            writer.write(transformFileName + INDEX_CATEGORY_NOT_EXIST + "\r\n");
            this.isOriginName = false;
        }
        return this;
    }

    public FileNameSneak checkFileNameByMaskNumber() throws IOException {
        boolean isMaskNumberValid = false;
        String maskNumberTemplate = "";
        List<String> maskNumbers = WearUtils.getAllMaskNumber();
        for (String maskNumber : maskNumbers) {
            maskNumberTemplate = "_" + maskNumber + "_";
            if (transformFileName.contains(maskNumberTemplate)) {
                isMaskNumberValid = true;
            }
        }
        if (!isMaskNumberValid) {
            writeHeaderLog();
            writer.write(transformFileName + MASK_NUMBER_NOT_EXIST + "\r\n");
            this.isOriginName = false;
        }
        return this;
    }

    public FileNameSneak checkFileNameBySize() throws IOException {
        boolean isSizeValid = false;
        String sizeTemplate = "";
        List<String> sizes = WearUtils.getAllSize();
        for (String size : sizes) {
            sizeTemplate = "(.*_)" + size + "(\\.[a-z]+)$";
            isSizeValid = transformFileName.matches(sizeTemplate);
            if (isSizeValid) {
                break;
            }
        }
        if (!isSizeValid) {
            writeHeaderLog();
            writer.write(transformFileName + SIZE_NOT_EXIST + "\r\n");
            this.isOriginName = false;
        }
        return this;
    }

    private void writeHeaderLog() throws IOException {
        if (!isHeaderWritten) {
            writer.write("\r\n" + HEADER_DESIGN + transformFileName + HEADER_DESIGN + "\r\n");
            isHeaderWritten = true;
        }
    }

    public FileNameSneak setTransformFileName(String transformFileName) {
        this.transformFileName = transformFileName;
        this.isHeaderWritten = false;
        this.isOriginName = true;
        return this;
    }


    public FileNameSneak setWriter(FileWriter writer) {
        this.writer = writer;
        return this;
    }

    public void setListId(List<String> listId) {
        this.listId = listId;
    }

    public boolean isOriginName() {
        return this.isOriginName;
    }
}
