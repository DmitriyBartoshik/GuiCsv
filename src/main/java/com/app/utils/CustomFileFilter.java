package com.app.utils;

import static com.app.utils.StringConstants.*;

public class CustomFileFilter extends javax.swing.filechooser.FileFilter {
    private String extension;
    private String description;

    public CustomFileFilter(FileFilterEnum filterEnum) {
        this.extension = filterEnum.extension;
        this.description = filterEnum.description;
    }

    @Override
    public boolean accept(java.io.File file) {
        if (file != null) {
            if (file.isDirectory())
                return true;
            return file.getName().endsWith(extension);
        }
        return false;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public enum FileFilterEnum {
        CSV(CSV_EXTENSION, CSV_DESCRIPTION),
        ZIP(ZIP_EXTENSION, ZIP_DESCRIPTION);

        private String extension;
        private String description;

        FileFilterEnum(String extension, String description) {
            this.extension = extension;
            this.description = description;
        }
    }
}

