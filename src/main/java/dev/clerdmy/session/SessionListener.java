package dev.clerdmy.session;

import dev.clerdmy.model.User;

public interface SessionListener {

    void sessionChanged(User newUser);

}