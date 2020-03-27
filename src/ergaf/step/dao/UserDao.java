package ergaf.step.dao;

import ergaf.step.io.Logger;
import ergaf.step.user.User;
import ergaf.step.user.UserBaseInterface;

import java.util.ArrayList;

public class UserDao implements UserBaseInterface {

    private ArrayList<User> users = new ArrayList<>();

    @Override
    public ArrayList<User> getAllUsers() {
        Logger.info("был получен список users");
        return users;
    }

    @Override
    public User addUser(User user) {
        Logger.info("addUserToCollection");

        int index = users.indexOf(user);
        if (index == -1) {
            users.add(user);
        } else {
            users.set(index, user);
        }
        return user;
    }

    @Override
    public void clearUsers() {
        Logger.info("clearUserList");
        users.clear();
    }

    @Override
    public void loadData(ArrayList<User> users) {
        Logger.info("loadData");
        this.users = users;
    }

}
