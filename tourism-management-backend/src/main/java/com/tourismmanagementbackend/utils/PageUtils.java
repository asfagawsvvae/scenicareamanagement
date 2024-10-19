package com.tourismmanagementbackend.utils;

public class PageUtils {
    public static int getStartPage(int page, int limit){

        return (page - 1) * limit;
    }
}
