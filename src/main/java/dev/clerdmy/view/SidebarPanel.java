package dev.clerdmy.view;

import dev.clerdmy.model.User;
import dev.clerdmy.session.SessionListener;
import dev.clerdmy.session.SessionManager;

import javax.swing.*;

public class SidebarPanel extends JPanel implements SessionListener {

    public SidebarPanel(MainPanel mainPanel) {

        setBounds(0, 0, GUIConstants.SIDEBAR, GUIConstants.DEFAULT_HEIGHT);
        setBackground(GUIConstants.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setVisible(false);
        add(Box.createVerticalStrut(GUIConstants.SQUARE + GUIConstants.TEXT_INSETS));

        RoundedButton todayButton = new RoundedButton("Today");
        GUIConfigurator.configureSidebarButton(todayButton);
        add(todayButton);

        todayButton.addActionListener(_ -> mainPanel.showScreen("TodayPanel"));

        RoundedButton habitsButton = new RoundedButton("Habits");
        GUIConfigurator.configureSidebarButton(habitsButton);
        add(habitsButton);

        habitsButton.addActionListener(_ -> mainPanel.showScreen("HabitsPanel"));

        RoundedButton statisticsButton = new RoundedButton("Statistics");
        GUIConfigurator.configureSidebarButton(statisticsButton);
        add(statisticsButton);

        statisticsButton.addActionListener(_ -> mainPanel.showScreen("StatisticsPanel"));

        RoundedButton profileButton = new RoundedButton("Profile");
        GUIConfigurator.configureSidebarButton(profileButton);
        add(profileButton);
        add(Box.createVerticalGlue());

        profileButton.addActionListener(_ -> mainPanel.showScreen("ProfilePanel"));

        RoundedButton logoutButton = new RoundedButton("Logout");
        GUIConfigurator.configureSidebarButton(logoutButton);
        logoutButton.setHoverColor(GUIConstants.RED);
        add(logoutButton);
        add(Box.createVerticalStrut(GUIConstants.TEXT_INSETS / 2));

        logoutButton.addActionListener(_ -> {
            SessionManager.logout();
            MainFrame.getInstance().showScreen("SignIn");
        });

        SessionManager.addSessionListener(this);

    }

    @Override
    public void sessionChanged(User newUser) {
        SwingUtilities.invokeLater(() -> {
            setVisible(false);
            revalidate();
            repaint();
        });
    }

}