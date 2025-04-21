package dev.clerdmy.sql;

public enum HabitQuery {

    SELECT_ALL("SELECT * FROM habits"),
    SELECT_BY_ID("SELECT * FROM habits WHERE id=?"),
    SELECT_BY_USER_ID("SELECT * FROM habits WHERE user_id=?"),
    INSERT("INSERT INTO habits (user_id, title, creation_date) VALUES (?, ?, ?)"),
    UPDATE("UPDATE habits SET user_id=?, title=?, creation_date=? WHERE id=?"),
    DELETE("DELETE FROM habits WHERE id=?");

    private final String sql;

    HabitQuery(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

}