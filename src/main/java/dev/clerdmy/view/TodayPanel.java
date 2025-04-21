package dev.clerdmy.view;

import dev.clerdmy.model.Habit;
import dev.clerdmy.model.User;
import dev.clerdmy.session.SessionListener;
import dev.clerdmy.session.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TodayPanel extends ScrollPanel<Habit> implements SessionListener {

    private final JLabel dateLabel;

    public TodayPanel() {

        super();

        dateLabel = new JLabel("DATE");
        GUIConfigurator.configureText(dateLabel);
        dateLabel.setFont(GUIConstants.TIMER_FONT);
        add(dateLabel, BorderLayout.NORTH);

        fillContentList(SessionManager.getHabits());

        SessionManager.addSessionListener(this);

    }

    protected void fillContentList(List<Habit> habits) {
        contentListPanel.removeAll();
        dateLabel.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        for (Habit habit : habits) {
            TodayTextArea todayTextArea = new TodayTextArea(habit);
            contentListPanel.add(todayTextArea);
            contentListPanel.add(Box.createVerticalStrut(GUIConstants.TEXT_INSETS));
        }

        contentListPanel.revalidate();
        contentListPanel.repaint();
        scrollPane.getVerticalScrollBar().setValue(0);
    }

    @Override
    public void habitsChanged(User currentUser) {
        SwingUtilities.invokeLater(() -> fillContentList(SessionManager.getHabits()));
    }

    @Override
    public void sessionChanged(User newUser) {
        SwingUtilities.invokeLater(() -> fillContentList(SessionManager.getHabits()));
    }

}