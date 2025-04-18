package dev.clerdmy.model;

import java.time.LocalDate;

public class HabitCheckpoint {

    private int id;
    private int habitId;
    private LocalDate checkpointDate;
    private boolean completed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public LocalDate getCheckpointDate() {
        return checkpointDate;
    }

    public void setCheckpointDate(LocalDate checkpointDate) {
        this.checkpointDate = checkpointDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}