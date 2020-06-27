package biz.impl;

import biz.UsersBiz;
import dao.UsersDao;
import dao.impl.UsersDaoImpl;
import entity.Users;

public class UsersBizImpl implements UsersBiz {

    UsersDao usersDao = new UsersDaoImpl();

    @Override
    public boolean login(Users users) {

        boolean flag = false;

        flag = usersDao.login(users);

        return flag;
    }

    @Override
    public Users getUsersByUsername(String username){

        Users users = new Users();

        users=usersDao.getUsersByUsername(username);

        return users;
    }

    @Override
    public int addUsers(Users users) {

        int i;

        i=usersDao.addUsers(users);

        return i;
    }

    @Override
    public boolean validateUsername(String username) {

        boolean flag = false;

        flag = usersDao.validateUsername(username);

        return flag;
    }

}
