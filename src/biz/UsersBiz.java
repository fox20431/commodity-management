package biz;

import entity.Users;

public interface UsersBiz {

    boolean login(Users users);

    Users getUsersByUsername(String username);

    int addUsers(Users user);

    boolean validateUsername(String username);

}

