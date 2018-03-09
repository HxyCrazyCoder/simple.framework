package simple.framework.core.exception;

/**
 * Copyright (c) 2017-2018 Wuhan Yryz Network Company LTD.
 * All rights reserved.
 * <p>
 * Created on 2018/3/9 16:05
 * Created by huangxy
 */
public class BizException extends RuntimeException{

    private String code;

    private String msg;

    private String showMsg;

    private Throwable throwable;


    public BizException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.showMsg = msg;
    }

    public BizException(String code, String msg, String showMsg) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.showMsg = showMsg;
    }

    public BizException(String code, String msg,Throwable throwable) {
        super(msg,throwable);
        this.code = code;
        this.msg = msg;
        this.showMsg = msg;
    }

    public BizException(String code, String msg, String showMsg,Throwable throwable) {
        super(msg,throwable);
        this.code = code;
        this.msg = msg;
        this.showMsg = showMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getShowMsg() {
        return showMsg;
    }

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }
}
