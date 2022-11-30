package com.michaeladrummonds.aguafina.exceptions;

public class UserNotAuthorizedException extends Exception {

    public UserNotAuthorizedException() {
        super("User not authorized to access this page.");
    }
}
