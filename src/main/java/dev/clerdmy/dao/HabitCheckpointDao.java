package dev.clerdmy.dao;

import dev.clerdmy.model.HabitCheckpoint;
import dev.clerdmy.sql.HabitCheckpointQuery;
import dev.clerdmy.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HabitCheckpointDao {

    private HabitCheckpoint mapRowToHabitCheckpoint(ResultSet resultSet) throws SQLException {
        HabitCheckpoint habitCheckpoint = new HabitCheckpoint();
        habitCheckpoint.setId(resultSet.getInt("id"));
        habitCheckpoint.setHabitId(resultSet.getInt("habit_id"));
        habitCheckpoint.setCheckpointDate(resultSet.getDate("checkpoint_date").toLocalDate());
        habitCheckpoint.setCompleted(resultSet.getBoolean("completed"));
        return habitCheckpoint;
    }

    public List<HabitCheckpoint> getAll() {
        List<HabitCheckpoint> habitCheckpoints = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HabitCheckpointQuery.SELECT_ALL.getSql());
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                habitCheckpoints.add(mapRowToHabitCheckpoint(resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return habitCheckpoints;
    }

    public Optional<HabitCheckpoint> getById(int id) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HabitCheckpointQuery.SELECT_BY_ID.getSql())) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRowToHabitCheckpoint(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public Optional<HabitCheckpoint> getByHabitId(int id) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HabitCheckpointQuery.SELECT_BY_HABIT_ID.getSql())) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRowToHabitCheckpoint(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public boolean insert(HabitCheckpoint habitCheckpoint) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HabitCheckpointQuery.INSERT.getSql())) {

            preparedStatement.setInt(1, habitCheckpoint.getHabitId());
            preparedStatement.setDate(2, java.sql.Date.valueOf(habitCheckpoint.getCheckpointDate()));
            preparedStatement.setBoolean(3, habitCheckpoint.isCompleted());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(HabitCheckpoint habitCheckpoint) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HabitCheckpointQuery.UPDATE.getSql())) {

            preparedStatement.setInt(1, habitCheckpoint.getHabitId());
            preparedStatement.setDate(2, java.sql.Date.valueOf(habitCheckpoint.getCheckpointDate()));
            preparedStatement.setBoolean(3, habitCheckpoint.isCompleted());
            preparedStatement.setInt(4, habitCheckpoint.getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HabitCheckpointQuery.DELETE.getSql())) {

            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<HabitCheckpoint> getByHabitIdAndDate(int habitId, LocalDate date) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HabitCheckpointQuery.SELECT_BY_HABIT_ID_AND_DATE.getSql())) {

            preparedStatement.setInt(1, habitId);
            preparedStatement.setDate(2, java.sql.Date.valueOf(date));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRowToHabitCheckpoint(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public boolean deleteByHabitId(int id) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(HabitCheckpointQuery.DELETE_BY_HABIT_ID.getSql())) {

            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}