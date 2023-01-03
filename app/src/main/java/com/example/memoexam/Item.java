package com.example.memoexam;

import java.io.Serializable;

public class Item implements Serializable {

    String memoList;

    public Item(String memoList) {
        this.memoList = memoList;
    }

    public Item() {

    }

    public void setMemoList(String memoList) {
        this.memoList = memoList;
    }

    public String getMemoList() {
        return memoList;
    }


}
