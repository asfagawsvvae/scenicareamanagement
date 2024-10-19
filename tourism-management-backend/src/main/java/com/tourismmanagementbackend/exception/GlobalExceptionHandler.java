package com.tourismmanagementbackend.exception;


import com.tourismmanagementbackend.utils.result.DataResult;
import com.tourismmanagementbackend.utils.result.code.Code;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice("com.hwadee.springboot")
public class GlobalExceptionHandler {
    /**
     * 出现异常返回错误提示, 并且回滚事务
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DataResult exceptionHandler(Exception e){
        System.out.println("抓到异常");
        e.printStackTrace();
        return DataResult.errByErrCode(Code.ERROR);
    }
}