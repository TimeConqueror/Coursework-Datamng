package ru.timeconqueror.coursework.util;

import org.springframework.web.multipart.MultipartFile;

public class FileContainer {

    private MultipartFile fileData;

    public MultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }
}
