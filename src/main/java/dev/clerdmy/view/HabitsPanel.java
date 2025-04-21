package dev.clerdmy.view;

import dev.clerdmy.model.Habit;
import dev.clerdmy.model.User;
import dev.clerdmy.session.SessionListener;
import dev.clerdmy.session.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HabitsPanel extends ScrollPanel<Habit> implements SessionListener {

    public HabitsPanel() {

        super();

        RoundedTextField inputArea = new RoundedTextField("Input a new habit...");
        GUIConfigurator.configureTextField(inputArea);

        RoundedButton sendButton = new RoundedButton("Add");
        sendButton.setHoverColor(GUIConstants.GREEN);
        GUIConfigurator.configureSquareButton(sendButton);

        sendButton.addActionListener(_ -> {
            if (inputArea.getText() != null && !inputArea.getText().isEmpty()) {
                SessionManager.createHabit(inputArea.getText());
                inputArea.setText("");
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new BorderLayout(GUIConstants.TEXT_INSETS / 2, 0));
        inputPanel.add(inputArea, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);

        fillContentList(SessionManager.getHabits());

        SessionManager.addSessionListener(this);

    }

    @Override
    protected void fillContentList(List<Habit> habits) {

        for (Habit habit : habits) {
            HabitTextArea habitText = new HabitTextArea(habit);
            contentListPanel.add(habitText);
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