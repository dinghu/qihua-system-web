package com.rqh.system.exception;

import com.rqh.system.bean.ResultBean;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 全局异常处理
 */

@ControllerAdvice
public class GlobalException {
    //日志
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GlobalException.class);

    ResultBean resultBean = new ResultBean();

    //运行时异常
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public ResultBean runtimeExceptionHandler(Exception ex) {
         ex.printStackTrace();
        logger.info("运行时异常----"+ex.getMessage());
        return  new ResultBean("500","运行时异常---"+ex.getMessage(),false,false);
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResultBean nullPointerExceptionHandler(NullPointerException ex) {
        ex.printStackTrace();
        logger.info("空指针异常----"+ex.getMessage());
        return  new ResultBean("500","空指针异常---"+ex.getMessage(),false,false);
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public ResultBean classCastExceptionHandler(ClassCastException ex) {
        ex.printStackTrace();
        logger.info("类型转换异常----"+ex.getMessage());
        return  new ResultBean("500","类型转换异常---"+ex.getMessage(),false,false);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public ResultBean iOExceptionHandler(IOException ex) {
        ex.printStackTrace();
        logger.info("IO异常----"+ex.getMessage());
        return  new ResultBean("500","IO异常---"+ex.getMessage(),false,false);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public ResultBean noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        ex.printStackTrace();
        logger.info("未知方法异常----"+ex.getMessage());
        return  new ResultBean("500","未知方法异常---"+ex.getMessage(),false,false);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public ResultBean indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        ex.printStackTrace();
        logger.info("数组越界异常----"+ex.getMessage());
        return  new ResultBean("500","数组越界异常---"+ex.getMessage(),false,false);
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ResultBean requestNotReadable(HttpMessageNotReadableException ex) {
        ex.printStackTrace();
        logger.info("400错误----"+ex.getMessage());
        return  new ResultBean("400","400错误---"+ex.getMessage(),false,false);
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public ResultBean requestTypeMismatch(TypeMismatchException ex) {
        ex.printStackTrace();
        logger.info("400错误----"+ex.getMessage());
        return  new ResultBean("400","400错误---"+ex.getMessage(),false,false);
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ResultBean requestMissingServletRequest(MissingServletRequestParameterException ex) {
        ex.printStackTrace();
        logger.info("400错误----"+ex.getMessage());
        return  new ResultBean("400","400错误---"+ex.getMessage(),false,false);
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public ResultBean request405(HttpRequestMethodNotSupportedException ex) {
        ex.printStackTrace();
        logger.info("405错误----"+ex.getMessage());
        return  new ResultBean("405","405错误---"+ex.getMessage(),false,false);
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public ResultBean request406(HttpMediaTypeNotAcceptableException ex) {
        ex.printStackTrace();
        logger.info("406错误----"+ex.getMessage());
        return  new ResultBean("406","406错误---"+ex.getMessage(),false,false);
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseBody
    public ResultBean server500(RuntimeException ex) {
        ex.printStackTrace();
        logger.info("500错误----"+ex.getMessage());
        return  new ResultBean("500","500错误---"+ex.getMessage(),false,false);
    }


    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    @ResponseBody
    public ResultBean requestStackOverflow(StackOverflowError ex) {
        ex.printStackTrace();
        logger.info("栈溢出----"+ex.getMessage());
        return  new ResultBean("500","栈溢出---"+ex.getMessage(),false,false);
    }

    //除数不能为0
    @ExceptionHandler({ArithmeticException.class})
    @ResponseBody
    public ResultBean arithmeticException(ArithmeticException ex) {
        ex.printStackTrace();
        logger.info("除数不能为0----"+ex.getMessage());
        return  new ResultBean("500","除数不能为0---"+ex.getMessage(),false,false);
    }


    //其他错误
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResultBean exception(Exception ex) {
        ex.printStackTrace();
        logger.info("其他错误----"+ex.getMessage());
        return  new ResultBean("500","其他错误---"+ex.getMessage(),false,false);
    }


}
