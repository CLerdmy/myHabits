package dev.clerdmy.dao;

import dev.clerdmy.model.Habit;
import dev.clerdmy.sql.HabitQuery;
import dev.clerdmy.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HabitDao {

    private Habit mapRowToHabit(ResultSet resultSet) throws SQLException {
        Habit habit = new Habit();
        habit.setId(resultSet.getInt("id"));
        habit.setUserId(resultSet.getInt("user_id"));
        habit.setTitle(resultSet.getString("title"));
        habit.setCreationDate(resultSet.getDate("creation_date").toLocalDate());
        return habit;
    }

    public List<Habit> getAll() {
        List<Habit> habits = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HabitQuery.SELECT_ALL.getSql());
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                habits.add(mapRowToHabit(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return habits;
    }

    public Optional<Habit> getById(int id) {
        try (Connection connection = DatabaseConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(HabitQuery.SELECT_BY_ID.getSql())) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRowToHabit(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public List<Habit> getByUserId(int id) {
        List<Habit> habits = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HabitQuery.SELECT_BY_USER_ID.getSql())) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    habits.add(mapRowToHabit(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return habits;
    }

    public boolean insert(Habit habit) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HabitQuery.INSERT.getSql())) {

            preparedStatement.setInt(1, habit.getUserId());
            preparedStatement.setString(2, habit.getTitle());
            preparedStatement.setDate(3, java.sql.Date.valueOf(habit.getCreationDate()));

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Habit habit) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HabitQuery.UPDATE.getSql())) {

            preparedStatement.setInt(1, habit.getUserId());
            preparedStatement.setString(2, habit.getTitle());
            preparedStatement.setDate(3, java.sql.Date.valueOf(habit.getCreationDate()));
            preparedStatement.setInt(4, habit.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HabitQuery.DELETE.getSql())) {

            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}