package com.michaeladrummonds.aguafina.errors;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class CustomExceptionHandler extends DefaultHandlerExceptionResolver {

        @ExceptionHandler({ ConstraintViolationException.class })
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ModelAndView handleConstraintViolation(ConstraintViolationException ex, WebRequest request,
                        HttpStatus status) {

                ModelAndView mav = new ModelAndView("error");

                List<String> errors = new ArrayList<>();
                for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
                        errors.add(violation.getRootBeanClass().getName() + " " +
                                        violation.getPropertyPath() + ": " + violation.getMessage());
                }

                mav.addObject("errors", errors);
                mav.addObject("message", ex.getMessage());
                mav.addObject("status", status);
                return mav;

        }

        @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ModelAndView handleMethodArgumentTypeMismatch(
                        MethodArgumentTypeMismatchException ex, WebRequest request, HttpStatus status) {
                ModelAndView mav = new ModelAndView("error");
                String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

                mav.addObject("error", error);
                mav.addObject("message", ex.getMessage());
                mav.addObject("status", status);
                return mav;
        }

        @ExceptionHandler({ NoHandlerFoundException.class })
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ModelAndView handleNoHandlerFoundException(
                        NoHandlerFoundException ex, HttpStatus status, WebRequest request) {
                ModelAndView mav = new ModelAndView("error");
                String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

                mav.addObject("error", error);
                mav.addObject("message", ex.getMessage());
                mav.addObject("status", status);
                return mav;
        }

        @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
        @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
        public ModelAndView handleHttpRequestMethodNotSupported(
                        HttpRequestMethodNotSupportedException ex,
                        HttpStatus status,
                        WebRequest request) {
                ModelAndView mav = new ModelAndView("error");

                StringBuilder builder = new StringBuilder();
                builder.append(ex.getMethod());
                builder.append(
                                " method is not supported for this request. Supported methods are ");
                ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

                String error = builder.toString();

                mav.addObject("error", error);
                mav.addObject("message", ex.getMessage());
                mav.addObject("status", status);
                return mav;
        }

        @ExceptionHandler({ Exception.class })
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public ModelAndView handleAll(Exception ex, WebRequest request, HttpStatus status) {
                ModelAndView mav = new ModelAndView("error");
                String error = "Unknown error has occurred.";

                mav.addObject("error", error);
                mav.addObject("message", ex.getMessage());
                mav.addObject("status", status);
                return mav;

        }
}
