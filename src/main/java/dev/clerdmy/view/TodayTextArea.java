package dev.clerdmy.view;

import dev.clerdmy.model.Habit;
import dev.clerdmy.model.HabitCheckpoint;
import dev.clerdmy.session.SessionManager;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TodayTextArea extends RoundedTextArea {

    private final Habit habit;
    private HabitCheckpoint checkpoint;

    public TodayTextArea(Habit habit) {

        super(habit.getTitle());
        this.habit = habit;

        setBackground(GUIConstants.RED);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        getTextArea().setFont(GUIConstants.MAIN_FONT);
        getTextArea().setForeground(GUIConstants.BLACK);

        initCheckpoint();

        getTextArea().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                checkpoint.setCompleted(!checkpoint.isCompleted());
                SessionManager.updateCheckpointStatus(checkpoint);
                setBackground(checkpoint.isCompleted() ? GUIConstants.GREEN : GUIConstants.RED);
                repaint();
            }
        });

    }

    private void initCheckpoint() {
        checkpoint = SessionManager.getOrCreateTodayCheckpoint(habit);
        setBackground(checkpoint.isCompleted() ? GUIConstants.GREEN : GUIConstants.RED);
    }

}