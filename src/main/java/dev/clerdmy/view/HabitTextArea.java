package dev.clerdmy.view;

import dev.clerdmy.model.Habit;
import dev.clerdmy.session.SessionManager;

import javax.swing.*;
import java.awt.*;

public class HabitTextArea extends JPanel {

    public HabitTextArea(Habit habit) {

        setOpaque(false);
        setLayout(new BorderLayout(GUIConstants.TEXT_INSETS / 2, 0));

        RoundedTextArea textArea = new RoundedTextArea(habit.getTitle());
        GUIConfigurator.configureWhiteTextArea(textArea);
        add(textArea, BorderLayout.CENTER);

        RoundedButton deleteButton = new RoundedButton("Delete");
        GUIConfigurator.configureSquareButton(deleteButton);
        deleteButton.setHoverColor(GUIConstants.RED);
        deleteButton.addActionListener(_ -> SessionManager.deleteHabit(habit));
        add(deleteButton, BorderLayout.EAST);

        setMaximumSize(new Dimension(GUIConstants.MIN_FRAME_WIDTH / 2, Integer.MAX_VALUE));

    }

}