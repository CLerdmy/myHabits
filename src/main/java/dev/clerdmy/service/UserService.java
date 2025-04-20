package dev.clerdmy.service;

import dev.clerdmy.dao.UserDao;
import dev.clerdmy.model.User;
import dev.clerdmy.response.AuthenticationStatus;
import dev.clerdmy.session.AuthenticationResult;
import dev.clerdmy.util.ValidationUtils;

import java.util.Optional;

public class UserService {

    private final UserDao userDao = new UserDao();

    public AuthenticationResult registerUser(String name, String email, String password, String confirmPassword) {

        if (name == null || name.isEmpty()) return new AuthenticationResult(null, AuthenticationStatus.EMPTY_NAME);
        if (email == null || email.isEmpty()) return new AuthenticationResult(null, AuthenticationStatus.EMPTY_EMAIL);
        if (password == null || confirmPassword == null || password.isEmpty() || confirmPassword.isEmpty()) return new AuthenticationResult(null, AuthenticationStatus.EMPTY_PASSWORD);
        if (!ValidationUtils.isValidEmail(email)) return new AuthenticationResult(null, AuthenticationStatus.INVALID_EMAIL);
        if (userDao.getByEmail(email).isPresent()) return new AuthenticationResult(null, AuthenticationStatus.EMAIL_ALREADY_EXISTS);
        if (!password.equals(confirmPassword)) return new AuthenticationResult(null, AuthenticationStatus.MISMATCHING_PASSWORDS);

        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);

        boolean success = userDao.insert(user);
        return success ? new AuthenticationResult(user, AuthenticationStatus.SUCCESS) : new AuthenticationResult(null, AuthenticationStatus.ERROR);
    }

    public AuthenticationResult loginUser(String email, String password) {

        if (email == null || email.isEmpty()) return new AuthenticationResult(null, AuthenticationStatus.EMPTY_EMAIL);
        if (password == null || password.isEmpty()) return new AuthenticationResult(null, AuthenticationStatus.EMPTY_PASSWORD);

        Optional<User> user = userDao.getByEmail(email);
        if (user.isEmpty() || !user.get().getPassword().equals(password)) return new AuthenticationResult(null, AuthenticationStatus.INCORRECT);

        return new AuthenticationResult(user.get(), AuthenticationStatus.SUCCESS);
    }

    public AuthenticationResult updateUser(User user, String name, String email, String newPassword, String currentPassword) {

        if ((name == null || name.isEmpty()) && (email == null || email.isEmpty()) && (newPassword == null || newPassword.isEmpty())) return  new AuthenticationResult(null, AuthenticationStatus.EMPTY);
        if (currentPassword == null || currentPassword.isEmpty()) return new AuthenticationResult(null, AuthenticationStatus.EMPTY_PASSWORD);
        if (!currentPassword.equals(user.getPassword())) return new AuthenticationResult(null, AuthenticationStatus.MISMATCHING_PASSWORDS);
        if (!ValidationUtils.isValidEmail(email)) return new AuthenticationResult(null, AuthenticationStatus.INVALID_EMAIL);
        if (userDao.getByEmail(email).isPresent()) return new AuthenticationResult(null, AuthenticationStatus.EMAIL_ALREADY_EXISTS);

        User updatedUser = new User();
        updatedUser.setId(user.getId());
        assert name != null;
        updatedUser.setUsername(name.isEmpty() ? user.getUsername() : name);
        assert email != null;
        updatedUser.setEmail(email.isEmpty() ? user.getEmail() : email);
        assert newPassword != null;
        updatedUser.setPassword(newPassword.isEmpty() ? user.getPassword() : newPassword);

        boolean success = userDao.update(updatedUser);
        return success ? new AuthenticationResult(updatedUser, AuthenticationStatus.SUCCESS) : new AuthenticationResult(null, AuthenticationStatus.ERROR);
    }

}