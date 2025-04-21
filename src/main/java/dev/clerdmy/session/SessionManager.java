package dev.clerdmy.session;

import dev.clerdmy.model.Habit;
import dev.clerdmy.model.HabitCheckpoint;
import dev.clerdmy.model.User;
import dev.clerdmy.response.AuthenticationStatus;
import dev.clerdmy.service.HabitCheckpointService;
import dev.clerdmy.service.HabitService;
import dev.clerdmy.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {

    private static User currentUser;
    private static final List<SessionListener> listeners = new ArrayList<>();
    private static final UserService userService = new UserService();
    private static final HabitService habitService = new HabitService();
    private static final HabitCheckpointService habitCheckpointService = new HabitCheckpointService();

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

    public static AuthenticationStatus update(String name, String email, String newPassword, String currentPassword) {
        AuthenticationResult result = userService.updateUser(currentUser, name, email, newPassword, currentPassword);
        if (result.isSuccess()) {
            currentUser = result.user();
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

    public static List<Habit> getHabits() {
        if (currentUser == null) return List.of();
        return habitService.getHabitsForUser(currentUser);
    }

    public static HabitCheckpoint getOrCreateTodayCheckpoint(Habit habit) {
        return habitCheckpointService.getOrCreateTodayCheckpoint(habit);
    }

    public static void updateCheckpointStatus(HabitCheckpoint checkpoint) {
        habitCheckpointService.updateCheckpointStatus(checkpoint);
    }

    public static void createHabit(String title) {
        habitService.createHabit(currentUser.getId(), title);
        notifyHabitsChanged();
    }

    private static void notifyHabitsChanged() {
        for (SessionListener listener : listeners) {
            listener.habitsChanged(currentUser);
        }
    }

    public static void deleteHabit(Habit habit) {
        boolean checkpointClear = habitCheckpointService.deleteByHabitId(habit);
        boolean habitClear = habitService.deleteHabit(habit);
        if (checkpointClear && habitClear) {
            notifyHabitsChanged();
        }
    }

}