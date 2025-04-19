package dev.clerdmy.sql;

public enum UserQuery {

    SELECT_ALL("SELECT * FROM users"),
    SELECT_BY_ID("SELECT * FROM users WHERE id=?"),
    SELECT_BY_EMAIL("SELECT * FROM users WHERE email=?"),
    INSERT("INSERT INTO users (username, email, password) VALUES (?, ?, ?)"),
    UPDATE("UPDATE users SET username=?, email=?, password=? WHERE id=?"),
    DELETE("DELETE FROM users WHERE id=?");

    private final String sql;

    UserQuery(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

}