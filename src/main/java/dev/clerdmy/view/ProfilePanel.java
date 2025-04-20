package dev.clerdmy.view;

import dev.clerdmy.model.User;
import dev.clerdmy.response.AuthenticationStatus;
import dev.clerdmy.session.SessionListener;
import dev.clerdmy.session.SessionManager;

import javax.swing.*;

public class ProfilePanel extends JPanel implements SessionListener {

    private final RoundedTextField nameField;
    private final RoundedTextField emailField;
    private final RoundedPasswordField newPasswordField;
    private final RoundedPasswordField confirmPasswordField;
    private final JLabel hint;

    public ProfilePanel() {

        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());

        JLabel nameHint = new JLabel("Enter a new name if you want to change it.");
        GUIConfigurator.configureTextLong(nameHint);
        add(nameHint);

        nameField = new RoundedTextField("Name");
        GUIConfigurator.configureTextFieldLong(nameField);
        add(nameField);
        add(Box.createVerticalStrut(GUIConstants.TEXT_INSETS));

        JLabel emailHint = new JLabel("Enter a new email if you want to change it.");
        GUIConfigurator.configureTextLong(emailHint);
        add(emailHint);

        emailField = new RoundedTextField("Email");
        GUIConfigurator.configureTextFieldLong(emailField);
        add(emailField);
        add(Box.createVerticalStrut(GUIConstants.TEXT_INSETS));

        JLabel newPasswordHint = new JLabel("Enter a new password if you want to change it.");
        GUIConfigurator.configureTextLong(newPasswordHint);
        add(newPasswordHint);

        newPasswordField = new RoundedPasswordField("New Password");
        GUIConfigurator.configureTextFieldLong(newPasswordField);
        add(newPasswordField);
        add(Box.createVerticalStrut(GUIConstants.TEXT_INSETS));

        JLabel currentPasswordHint = new JLabel("To confirm the changes, enter your current password.");
        GUIConfigurator.configureTextLong(currentPasswordHint);
        add(currentPasswordHint);

        confirmPasswordField = new RoundedPasswordField("Current Password");
        GUIConfigurator.configureTextFieldLong(confirmPasswordField);
        add(confirmPasswordField);
        add(Box.createVerticalStrut(GUIConstants.SQUARE));

        hint = new JLabel("");
        GUIConfigurator.configureText(hint);
        add(hint);

        RoundedButton confirmButton = new RoundedButton("Confirm");
        GUIConfigurator.configureButtonLong(confirmButton);
        add(confirmButton);
        add(Box.createVerticalGlue());

        confirmButton.addActionListener(_ -> {
            AuthenticationStatus status = SessionManager.update(nameField.getText(), emailField.getText(), newPasswordField.getText(), confirmPasswordField.getText());
            switch (status) {
                case SUCCESS -> sessionChanged(SessionManager.getCurrentUser());
                case EMPTY -> hint.setText("All fields are empty.");
                case EMPTY_PASSWORD -> hint.setText("Enter current password.");
                case INVALID_EMAIL -> hint.setText("Invalid email format.");
                case EMAIL_ALREADY_EXISTS -> hint.setText("Email already exists.");
                case MISMATCHING_PASSWORDS -> hint.setText("Passwords do not match.");
                case ERROR -> hint.setText("SERVER ERROR");
                default -> hint.setText("ERROR");
            }
        });

        SessionManager.addSessionListener(this);

    }

    @Override
    public void sessionChanged(User newUser) {
        SwingUtilities.invokeLater(() -> {
            if (!nameField.getText().isEmpty()) nameField.setText("");
            if (!emailField.getText().isEmpty()) emailField.setText("");
            if (!newPasswordField.getText().isEmpty()) newPasswordField.setText("");
            if (!confirmPasswordField.getText().isEmpty()) confirmPasswordField.setText("");
            if (newUser != null) {
                nameField.setPlaceholder(newUser.getUsername());
                emailField.setPlaceholder(newUser.getEmail());
            }
            hint.setText("");
        });
    }

}