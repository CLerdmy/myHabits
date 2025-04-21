package dev.clerdmy.service;

import dev.clerdmy.dao.HabitCheckpointDao;
import dev.clerdmy.model.Habit;
import dev.clerdmy.model.HabitCheckpoint;

import java.time.LocalDate;
import java.util.Optional;

public class HabitCheckpointService {

    private final HabitCheckpointDao habitCheckpointDao = new HabitCheckpointDao();

    private Optional<HabitCheckpoint> getTodayCheckpoint(Habit habit) {
        LocalDate today = LocalDate.now();
        return habitCheckpointDao.getByHabitIdAndDate(habit.getId(), today);
    }

    private HabitCheckpoint createTodayCheckpoint(Habit habit) {
        HabitCheckpoint checkpoint = new HabitCheckpoint();
        checkpoint.setHabitId(habit.getId());
        checkpoint.setCheckpointDate(LocalDate.now());
        checkpoint.setCompleted(false);

        if (habitCheckpointDao.insert(checkpoint)) {
            return habitCheckpointDao.getByHabitIdAndDate(habit.getId(), LocalDate.now()).orElseThrow();
        } else {
            throw new RuntimeException();
        }
    }

    public void updateCheckpointStatus(HabitCheckpoint checkpoint) {
        if (!habitCheckpointDao.update(checkpoint)) {
            throw new RuntimeException();
        }
    }

    public HabitCheckpoint getOrCreateTodayCheckpoint(Habit habit) {
        Optional<HabitCheckpoint> optionalHabitCheckpoint = getTodayCheckpoint(habit);
        return optionalHabitCheckpoint.orElseGet(() -> createTodayCheckpoint(habit));
    }

    public boolean deleteByHabitId(Habit habit) {
        return habitCheckpointDao.deleteByHabitId(habit.getId());
    }

}