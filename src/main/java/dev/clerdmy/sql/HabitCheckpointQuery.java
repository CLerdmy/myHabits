package dev.clerdmy.sql;

public enum HabitCheckpointQuery {

    SELECT_ALL("SELECT * FROM habit_checkpoints"),
    SELECT_BY_ID("SELECT * FROM habit_checkpoints WHERE id=?"),
    SELECT_BY_HABIT_ID("SELECT * FROM habit_checkpoints WHERE habit_id=?"),
    SELECT_BY_HABIT_ID_AND_DATE("SELECT * FROM habit_checkpoints WHERE habit_id=? AND checkpoint_date=?"),
    INSERT("INSERT INTO habit_checkpoints (habit_id, checkpoint_date, completed) VALUES (?, ?, ?)"),
    UPDATE("UPDATE habit_checkpoints SET habit_id=?, checkpoint_date=?, completed=? WHERE id=?"),
    DELETE_BY_HABIT_ID("DELETE FROM habit_checkpoints WHERE habit_id=?"),
    DELETE("DELETE FROM habit_checkpoints WHERE id=?");

    private final String sql;

    HabitCheckpointQuery(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

}