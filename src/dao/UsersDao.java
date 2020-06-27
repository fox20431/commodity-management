package dao;

import entity.Users;

public interface UsersDao {
    boolean login(Users users);
    Users getUsersByUsername(String username);
    int addUsers(Users users);
    boolean validateUsername(String username);
}
