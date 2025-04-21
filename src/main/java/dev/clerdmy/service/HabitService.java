package dev.clerdmy.service;

import dev.clerdmy.dao.HabitDao;
import dev.clerdmy.model.Habit;
import dev.clerdmy.model.User;

import java.time.LocalDate;
import java.util.List;

public class HabitService {

    private final HabitDao habitDao = new HabitDao();

    public List<Habit> getHabitsForUser(User user) {
        return habitDao.getByUserId(user.getId());
    }

    public void createHabit(int id, String title) {
        Habit habit = new Habit();
        habit.setTitle(title);
        habit.setUserId(id);
        habit.setCreationDate(LocalDate.now());

        habitDao.insert(habit);
    }

    public boolean deleteHabit(Habit habit) {
        return habitDao.delete(habit.getId());
    }

}