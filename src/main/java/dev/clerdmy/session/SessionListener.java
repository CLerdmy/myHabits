package dev.clerdmy.session;

import dev.clerdmy.model.User;

public interface SessionListener {

    void sessionChanged(User newUser);

    default void habitsChanged(User currentUser) {}

}