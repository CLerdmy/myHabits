package dev.clerdmy.view;

import dev.clerdmy.model.User;
import dev.clerdmy.response.AuthenticationStatus;
import dev.clerdmy.session.SessionListener;
import dev.clerdmy.session.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignInPanel extends JPanel implements SessionListener {

    private final JLabel hint;
    private final RoundedTextField loginField;
    private final RoundedPasswordField passwordField;

    public SignInPanel() {

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

        loginField = new RoundedTextField("Login");
        GUIConfigurator.configureTextField(loginField);
        add(loginField);
        add(Box.createVerticalStrut(GUIConstants.TEXT_INSETS));

        passwordField = new RoundedPasswordField("Password");
        GUIConfigurator.configureTextField(passwordField);
        add(passwordField);
        add(Box.createVerticalStrut(GUIConstants.SQUARE));

        RoundedButton nextButton = new RoundedButton("Next");
        GUIConfigurator.configureButton(nextButton);
        add(nextButton);
        add(Box.createVerticalStrut(GUIConstants.TEXT_INSETS));

        JLabel hyperLink = new JLabel("Don't have an account? Sign Up");
        GUIConfigurator.configureText(hyperLink);
        add(hyperLink);

        add(Box.createVerticalGlue());

        SessionManager.addSessionListener(this);

        nextButton.addActionListener(_ -> {
            AuthenticationStatus status = SessionManager.login(loginField.getText(), passwordField.getText());
            switch (status) {
                case SUCCESS -> MainFrame.getInstance().showScreen("MainPanel");
                case EMPTY_EMAIL -> hint.setText("Enter your login.");
                case EMPTY_PASSWORD -> hint.setText("Enter your password.");
                case INCORRECT -> hint.setText("Invalid login or password.");
                default -> hint.setText("ERROR");
            }
        });

        hyperLink.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                MainFrame.getInstance().showScreen("SignUp");
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
        if (!hint.getText().isEmpty()) hint.setText("");
        if (!loginField.getText().isEmpty()) loginField.setText("");
        if (!passwordField.getText().isEmpty()) passwordField.setText("");
    }

}