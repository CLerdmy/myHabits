package dev.clerdmy.view;

import dev.clerdmy.model.User;
import dev.clerdmy.response.AuthenticationStatus;
import dev.clerdmy.session.SessionListener;
import dev.clerdmy.session.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUpPanel extends JPanel implements SessionListener {

    private final JLabel hint;
    private final RoundedTextField nameField;
    private final RoundedTextField emailField;
    private final RoundedPasswordField passwordField;
    private final RoundedPasswordField confirmPasswordField;

    public SignUpPanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(GUIConstants.PURPLE);
        add(Box.createVerticalGlue());

        JLabel myHabits = new JLabel("myHabits");
        GUIConfigurator.configureTitle(myHabits);
        add(myHabits);
        add(Box.createVerticalGlue());

        hint = new JLabel("");
        GUIConfigurator.configureText(hint);
        add(hint);
        add(Box.createVerticalStrut(GUIConstants.TEXT_INSETS));

        nameField = new RoundedTextField("Name");
        GUIConfigurator.configureTextField(nameField);
        add(nameField);
        add(Box.createVerticalStrut(GUIConstants.TEXT_INSETS));

        emailField = new RoundedTextField("Email");
        GUIConfigurator.configureTextField(emailField);
        add(emailField);
        add(Box.createVerticalStrut(GUIConstants.TEXT_INSETS));

        passwordField = new RoundedPasswordField("Password");
        GUIConfigurator.configureTextField(passwordField);
        add(passwordField);
        add(Box.createVerticalStrut(GUIConstants.TEXT_INSETS));

        confirmPasswordField = new RoundedPasswordField("Confirm Password");
        GUIConfigurator.configureTextField(confirmPasswordField);
        add(confirmPasswordField);
        add(Box.createVerticalStrut(GUIConstants.SQUARE));

        RoundedButton registerButton = new RoundedButton("Register");
        GUIConfigurator.configureButton(registerButton);
        add(registerButton);
        add(Box.createVerticalStrut(GUIConstants.TEXT_INSETS));

        JLabel hyperLink = new JLabel("Already have an account? Sign In");
        GUIConfigurator.configureText(hyperLink);
        add(hyperLink);

        add(Box.createVerticalGlue());

        SessionManager.addSessionListener(this);

        registerButton.addActionListener(_ -> {
            AuthenticationStatus status = SessionManager.register(nameField.getText(), emailField.getText(), passwordField.getText(), confirmPasswordField.getText());
            switch (status) {
                case SUCCESS -> MainFrame.getInstance().showScreen("MainPanel");
                case EMPTY_NAME -> hint.setText("Enter your name.");
                case EMPTY_EMAIL -> hint.setText("Enter your email address.");
                case INVALID_EMAIL -> hint.setText("Invalid email format.");
                case EMAIL_ALREADY_EXISTS -> hint.setText("User already exists.");
                case EMPTY_PASSWORD -> hint.setText("Create a password.");
                case MISMATCHING_PASSWORDS -> hint.setText("Passwords do not match.");
                case ERROR -> hint.setText("SERVER ERROR");
                default -> hint.setText("ERROR");
            }
        });

        hyperLink.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                MainFrame.getInstance().showScreen("SignIn");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                hyperLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override public void mouseExited(MouseEvent e) {
                hyperLink.setCursor(Cursor.getDefaultCursor());
            }
        });

    }

    @Override
    public void sessionChanged(User newUser) {
        SwingUtilities.invokeLater(() -> {
            if (!hint.getText().isEmpty()) hint.setText("");
            if (!nameField.getText().isEmpty()) nameField.setText("");
            if (!emailField.getText().isEmpty()) emailField.setText("");
            if (!passwordField.getText().isEmpty()) passwordField.setText("");
            if (!confirmPasswordField.getText().isEmpty()) confirmPasswordField.setText("");
        });
    }

}