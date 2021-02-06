package com.app.utils;

public class StringConstants {
    //region Template
    public static final String GENERAL_TEMPLATE = ".*(_)\\d{4}(_)\\d{2}(_)(s|m|l|xl)(\\.)(png|jpeg|webp)";
    //endregion

    //region files naming
    public static final String PREFIX_WARNING_ZIP = "warning_";
    public static final String DONE_TXT = "done.txt";
    public static final String TODO_TXT = "todo.txt";
    public static final String CP866 = "CP866";
    //endregion

    //region tittle and button text
    public static final String APPLICATION_TITTLE = "Хорошего дня! :)";
    public static final String CSV_CHANGE_BUTTON_TEXT = "Выберите csv  файл";
    public static final String ZIP_CHANGE_BUTTON_TEXT = "Выберите zip файл";
    public static final String DIRECTORY_CHANGE_BUTTON_TEXT = "Выберите директорию для сохранения";
    public static final String START_BUTTON_TEXT = "Нажми и завари чайку";
    public static final String START_BUTTON_TEXT_AFTER_WORK = "Извините, что так долго";
    public static final String INFO_TEXT_AFTER_WORK = "Обработка закончена";
    public static final String WARNING_DIALOG_MESSAGE = "Заполнены не все данные";
    //endregion

    //region path
    public static final String CSV_FILE_DEFAULT_PATH = "";
    public static final String ZIP_FILE_DEFAULT_PATH = "";
    public static final String END_DIRECTORY_DEFAULT_PATH = "";
    public static final String ZIP_FILE_DEFAULT_NAME = "";
    public static final String DEFAULT_PATH = "";
    //endregion

    //region FileFilter
    public static final String CSV_EXTENSION = "csv";
    public static final String ZIP_EXTENSION = "zip";
    public static final String CSV_DESCRIPTION = "Файлы Csv (*.csv)";
    public static final String ZIP_DESCRIPTION = "Zip (*.zip)";
    public static final String DIRECTORY_ONLY = "Directories only";
    //endregion

    //region FileNameCorrector
    public static final String SPACE_DELETE_MESSAGE = " удален пробел в исходном названии";
    public static final String SEMICOLON_DELETE_MESSAGE = " удалена точка с запятой  в исходном названии ";
    public static final String DASH_DELETE_MESSAGE = " удалено тире  в исходном названии ";
    public static final String UNDER_SCORE_DELETE_MESSAGE = " удалено нижнее подчеркивание  в исходном названии ";
    public static final String HEADER_DESIGN = " **************** ";
    //endregion

    //region FileNameSneakCorrector

    public static final String CYRILLIC_SYMBOL_WARNING_MESSAGE = " присутствуют русские символы";
    public static final String FILE_NAME_NOT_TRUE_GENERAL_TEMPLATE_WARNING_MESSAGE = " Имя файла не соответствует основному шаблону  ***** id_indexCategory_maskNumber_size *****";
    public static final String ID_NOT_EXIST = " ID не существует или не соответствует основному шаблону  ***** id_indexCategory_maskNumber_size *****";
    public static final String INDEX_CATEGORY_NOT_EXIST = " indexCategory не существует или не соответствует основному шаблонy  ***** id_indexCategory_maskNumber_size *****";
    public static final String MASK_NUMBER_NOT_EXIST = " maskNumber не существует или не соответствует основному шаблонy  ***** id_indexCategory_maskNumber_size *****";
    public static final String SIZE_NOT_EXIST = " size не существует или не соответствует основному шаблонy  ***** id_indexCategory_maskNumber_size *****";







    //endregion






    //endregion

}
