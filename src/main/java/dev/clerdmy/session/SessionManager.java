package dev.clerdmy.session;

import dev.clerdmy.model.User;
import dev.clerdmy.response.AuthenticationStatus;
import dev.clerdmy.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {

    private static User currentUser;
    private static final List<SessionListener> listeners = new ArrayList<>();
    private static final UserService userService = new UserService();

    private static void notifyListeners() {
        for (SessionListener listener : listeners) {
            listener.sessionChanged(currentUser);
        }
    }

    public static AuthenticationStatus login(String email, String password) {
        AuthenticationResult result = userService.loginUser(email, password);
        if (result.isSuccess()) {
            currentUser = result.user();
            notifyListeners();
        }
        return result.status();
    }

    public static AuthenticationStatus register(String name, String email, String password, String confirmPassword) {
        AuthenticationResult result = userService.registerUser(name, email, password, confirmPassword);
        if (result.isSuccess()) {
            currentUser = result.user();
            notifyListeners();
        }
        return result.status();
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
        notifyListeners();
    }

    public static void addSessionListener(SessionListener listener) {
        listeners.add(listener);
    }

}