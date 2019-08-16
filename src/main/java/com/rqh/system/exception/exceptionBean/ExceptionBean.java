package com.rqh.system.exception.exceptionBean;

/**
 * @ClassName: ExceptionBean
 * @Auther: 王振科
 * @Date: 2018/12/27 15:07
 * @Version: 1.0
 * @Description:
 */
public class ExceptionBean extends RuntimeException {
    private int errorCode;  // 错误代码

    public ExceptionBean(String s, int errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public ExceptionBean(int errorCode) {
        this.errorCode = errorCode;
    }

    public ExceptionBean(String s) {
        super(s);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
