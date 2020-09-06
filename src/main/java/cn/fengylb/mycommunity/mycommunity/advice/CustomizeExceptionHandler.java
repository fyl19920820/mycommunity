package cn.fengylb.mycommunity.mycommunity.advice;

import cn.fengylb.mycommunity.mycommunity.dto.ResultDTO;
import cn.fengylb.mycommunity.mycommunity.exception.CustomizeErrorCode;
import cn.fengylb.mycommunity.mycommunity.exception.CustomizeException;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, HttpServletResponse response, Throwable ex, Model model) {
        String contentType = request.getContentType();
        if (contentType != null && contentType.equals("application/json")){
            ResultDTO resultDTO;
            if (ex instanceof CustomizeException){
                resultDTO = ResultDTO.errorOf((CustomizeException) ex);
            }else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            response.setStatus(200);
            try {
                PrintWriter writer = response.getWriter();
                writer.print(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else {
            if (ex instanceof CustomizeException){
                model.addAttribute("message",ex.getMessage());
            }else {
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR);
            }
            ex.printStackTrace();
            return new ModelAndView("error");
        }
    }
}
