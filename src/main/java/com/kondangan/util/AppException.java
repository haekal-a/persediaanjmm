package com.kondangan.util;

public class AppException extends RuntimeException {


    private int level;
    private String title;

    public static int LEVEL_INFO = 1;
    public static int LEVEL_ERROR = 2;

    /**
     *
     * @param level 1 = Info; 2 = Error
     * @param message message
     */
    public AppException(int level, String message){

        super(message);
        this.level = level;
        this.title = "Terjadi Kesalahan";
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
