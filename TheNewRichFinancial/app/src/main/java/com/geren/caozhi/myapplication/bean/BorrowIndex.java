package com.geren.caozhi.myapplication.bean;

/**
 * Created by neil on 16/9/13.
 */
public class BorrowIndex {

    private int isFirst;

    private int status;

    private BorrowPage borrowPage;

    public BorrowPage getBorrowPage() {
        return borrowPage;
    }

    public void setBorrowPage(BorrowPage borrowPage) {
        this.borrowPage = borrowPage;
    }




    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(int isFirst) {
        this.isFirst = isFirst;
    }

}
