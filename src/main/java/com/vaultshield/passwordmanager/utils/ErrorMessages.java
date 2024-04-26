package com.vaultshield.passwordmanager.utils;

public class ErrorMessages {

    public static final String ERROR_409_EMAIL = "Email already exist.";

    public static final String ERROR_409_USERNAME = "Username already exist.";

    public static final String ERROR_404_USERNAME = "This username doesn't exist.";

    public static final String ERROR_404_EMAIL = "Email doesn't exist.";

    public static final String USER_NOT_FOUND = "User not found";

    public static final String USER_NOT_FOUND_BY_ID = "User not found with ID: ";

    public static final String USER_NOT_FOUND_BY_USERNAME = "No user found with username: ";

    public static final String CRED_NOT_FOUND_BY_ID = "No credentials found with ID: ";

    public static final String CRED_NOT_FOUND_BY_USERID = "No credentials found for userId: ";

    public static final String INCORRECT_QUERY = "Incorrect query. Use 'id', 'username' o 'email'.";

    public static final String BAD_CREDENTIALS = "Unauthorized - Bad credentials";

    public static final String TOKEN_EXPIRED = "Refresh token was expired. Please make a new signin request";

    public static final String TOKEN_NOT_EXIST = "Refresh token is not in database!";

}
