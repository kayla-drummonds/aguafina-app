package com.michaeladrummonds.aguafina.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@ControllerAdvice
@RequestMapping
public class ErrorController {
    private static final String PACKAGE_NAME = "com.michaeladrummonds.aguafina";

    @GetMapping("/error/404")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String error404(HttpServletRequest request, Exception ex) {
        return "redirect:/error?404";
    }

    @ExceptionHandler(Exception.class)
    @GetMapping("/error")
    public String handleAllExceptions(HttpServletRequest request, Exception ex, Model model) {

        model.addAllAttributes(buildExceptionParameters(ex, request));
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
        }

        return "error";
    }

    private String getRequestURL(HttpServletRequest request) {
        String result = request.getRequestURL().toString();
        if (request.getQueryString() != null) {
            result = result + "?" + request.getQueryString();
        }

        return result;
    }

    public Map<String, Object> buildExceptionParameters(Exception ex, String message) {
        Map<String, Object> result = new HashMap<>();

        String stackTrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(ex));

        // message is the request URL if it was an error page, otherwise it can be a
        // message
        // from the class that calls it
        result.put("requestUrl", message);
        result.put("message", ex.getMessage());
        result.put("stackTrace", stackTrace);

        if (ex.getCause() != null) {
            result.put("rootcause", ExceptionUtils.getRootCause(ex));

            String roottrace = getHTMLStackTrace(ExceptionUtils.getRootCauseStackTrace(ex));
            result.put("roottrace", roottrace);
        }

        return result;
    }

    public Map<String, Object> buildExceptionParameters(Exception ex, HttpServletRequest request) {
        String message = getRequestURL(request);
        return buildExceptionParameters(ex, message);
    }

    private String getHTMLStackTrace(String[] stack) {
        StringBuffer result = new StringBuffer();
        for (String frame : stack) {
            if (frame.contains(PACKAGE_NAME)) {
                result.append(" &nbsp; &nbsp; &nbsp;" + frame.trim().substring(3) + "<br>\n");
            } else if (frame.contains("Caused by:")) {
                result.append("Caused By:<br>");
            }
        }

        return result.toString();
    }
}
