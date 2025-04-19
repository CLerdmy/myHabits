package dev.clerdmy.response;

public enum AuthenticationStatus {

    SUCCESS,
    EMPTY_NAME,
    EMPTY_EMAIL,
    EMPTY_PASSWORD,
    MISMATCHING_PASSWORDS,
    EMAIL_ALREADY_EXISTS,
    INVALID_EMAIL,
    INCORRECT,
    ERROR

}