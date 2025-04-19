package dev.clerdmy.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private final CardLayout cardLayout;
    private final JPanel panel;

    private MainFrame() {

        setUndecorated(false);
        setSize(GUIConstants.DEFAULT_WIDTH, GUIConstants.DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(GUIConstants.MIN_WIDTH, GUIConstants.MIN_HEIGHT));

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        SignInPanel signIn = new SignInPanel();
        SignUpPanel signUp = new SignUpPanel();
        MainPanel mainPanel = new MainPanel();

        panel.add(signIn, "SignIn");
        panel.add(signUp, "SignUp");
        panel.add(mainPanel, "MainPanel");

        add(panel);
        cardLayout.show(panel, "MainPanel");

        setVisible(true);
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    public void showScreen(String name) {
        cardLayout.show(panel, name);
    }

}