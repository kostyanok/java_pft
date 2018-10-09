package ru.stqa.pft.mantis.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("user")
@Entity
@Table(name = "mantis_user_table")
public class UserData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @XStreamOmitField
    @Column(name = "email")
    private String email;
    @XStreamOmitField
    @Column(name = "username")
    private String username;
    @XStreamOmitField
    @Column(name = "password")
    private String password;

    public int getId() {
        return id;
    }

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                Objects.equals(email, userData.email) &&
                Objects.equals(username, userData.username) &&
                Objects.equals(password, userData.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password);
    }

    public String getUsername() {
        return username;
    }

    public UserData withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserData withPassword(String password) {
        this.password = password;
        return this;
    }
}
