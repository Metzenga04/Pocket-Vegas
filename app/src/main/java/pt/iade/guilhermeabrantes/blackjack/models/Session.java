package pt.iade.guilhermeabrantes.blackjack.models;

import java.sql.Timestamp;

public class Session {
    private int id;
    private User user;
    private Timestamp date_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", user=" + user +
                ", date_time=" + date_time +
                '}';
    }
}
