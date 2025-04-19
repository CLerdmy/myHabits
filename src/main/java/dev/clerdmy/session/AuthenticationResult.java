package dev.clerdmy.session;

import dev.clerdmy.model.User;
import dev.clerdmy.response.AuthenticationStatus;

public record AuthenticationResult(User user, AuthenticationStatus status) {

    public boolean isSuccess() {
        return user != null;
    }

}