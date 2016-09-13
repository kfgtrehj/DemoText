package com.geren.caozhi.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by neil on 16/9/13.
 */
public class BorrowPage {

    private int pageSize;
    private int pageNum;

    public ArrayList<Page> getPageArray() {
        return pageArray;
    }

    public void setPageArray(ArrayList<Page> pageArray) {
        this.pageArray = pageArray;
    }

    private ArrayList<Page> pageArray;
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }



}
