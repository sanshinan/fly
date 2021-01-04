package com.example.day02.modle.bean;

public class Tokenbean {

    /**
     * errno : 0
     * errmsg :
     * data : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiN2I5NjU2NTYtNzBlYi00NzI2LWI0YTctYzUyMzY2ODYxNDg1IiwicmFuZG9tIjoib3Q4dHRnNmViaCIsImlhdCI6MTYwOTczOTgzNn0.eP_Fk-TKYv96IkXQDTuvyIMQtFxyaUiMBSh1BZRHn08
     */

    private int errno;
    private String errmsg;
    private String data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
