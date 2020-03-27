package ergaf.step.dao;

import ergaf.step.utils.io.Logger;
import ergaf.step.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {

    private List<User> users = new ArrayList<>();

    @Override
    public List<User> getAll() {
        Logger.info("был получен список users");
        return users;
    }

    @Override
    public User add(User user) {
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
    public void clear() {
        Logger.info("clearUserList");
        users.clear();
    }

    @Override
    public void loadData(List<User> users) {
        Logger.info("loadData");
        this.users = users;
    }

    @Override
    public boolean delete(User user) {
        return users.remove(user);
    }
}
