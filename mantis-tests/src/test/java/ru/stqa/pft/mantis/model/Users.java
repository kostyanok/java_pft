package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.*;

public class Users extends ForwardingSet<UserData> {

    private Set<UserData> delegate;

    public Users(Users users) {
        this.delegate = new HashSet<>(users.delegate);
    }

    public Users() {
        this.delegate = new HashSet<>();
    }

    public Users(Collection<UserData> users) {
        this.delegate = new HashSet<>(users);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }

    public Set<UserData> withoutUser(Set<UserData> users, int id) {
        Set<UserData> usersResult = new HashSet<>();
        for (UserData user : users)
            if (id != user.getId())
                usersResult.add(user);

        return usersResult;
    }
}
