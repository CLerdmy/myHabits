package dev.clerdmy.view;

import dev.clerdmy.model.User;
import dev.clerdmy.session.SessionListener;
import dev.clerdmy.session.SessionManager;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements SessionListener {

    private final CardLayout cardLayout;
    private final JPanel panel;

    public MainPanel() {

        setLayout(new BorderLayout());
        setBackground(GUIConstants.PURPLE);

        RoundedButton menuButton = new RoundedButton("≡");
        GUIConfigurator.configureSquareButton(menuButton);
        add(menuButton);

        SidebarPanel sidebarPanel = new SidebarPanel(this);
        add(sidebarPanel, BorderLayout.WEST);

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);
        panel.setOpaque(false);

        TodayPanel todayPanel = new TodayPanel();
        HabitsPanel habitsPanel = new HabitsPanel();
        StatisticsPanel statisticsPanel = new StatisticsPanel();
        ProfilePanel profilePanel = new ProfilePanel();
        panel.add(todayPanel, "TodayPanel");
        panel.add(habitsPanel, "HabitsPanel");
        panel.add(statisticsPanel, "StatisticsPanel");
        panel.add(profilePanel, "ProfilePanel");


        add(panel);
        cardLayout.show(panel, "TodayPanel");

        menuButton.addActionListener(_ -> {
            sidebarPanel.setVisible(!sidebarPanel.isVisible());
            revalidate();
            repaint();
        });

        SessionManager.addSessionListener(this);

    }

    public void showScreen(String name) {
        cardLayout.show(panel, name);
    }

    @Override
    public void sessionChanged(User newUser) {
        SwingUtilities.invokeLater(() -> showScreen("TodayPanel"));
    }

}