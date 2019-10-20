package com.warden.common.rest;

import com.warden.common.bean.Response;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * @author ：yangjiaying
 * @version ：
 * @date ：Created in 2019/8/13
 * @description ：处理{@link org.springframework.web.bind.MethodArgumentNotValidException}
 */
@ControllerAdvice
public class ValidExceptionHandlerController {
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response exception(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuffer stringBuffer = new StringBuffer("参数校验错误：");
        allErrors.forEach(objectError -> {
            FieldError fieldError = (FieldError) objectError;
            stringBuffer.append(fieldError.getDefaultMessage()).append(";");
        });
        stringBuffer.replace(stringBuffer.length() - 1, stringBuffer.length(), "。");
        return Response.failure(400, stringBuffer.toString());
    }
}
