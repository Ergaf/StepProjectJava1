package ergaf.step.services;

import ergaf.step.io.FileWorker;
import ergaf.step.user.User;
import ergaf.step.dao.UserDao;

import java.util.ArrayList;

public class UserService {

    private UserDao userDao;

    private String filename = "users.data";

    public UserService(UserDao userDao, String filename) {
        this.userDao = userDao;
        this.filename = filename;
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int getNextId() {
        int id = userDao.getAllUsers().
                stream().
                mapToInt(User::getId).
                reduce((first,second) -> second).orElse(0);

        return id + 1;
    }

    public User getUserById(int id) {
        return userDao.
                getAllUsers().
                stream().
                filter(user -> user.getId() == id).
                findFirst().
                orElse(null);
    }

    public User getUserByFirstNameAndLastName(String firstname, String lastname) {
        return userDao.
                getAllUsers().
                stream().
                filter(user -> user.getFirstName().equals(firstname) && user.getLastName().equals(lastname)).
                findFirst().
                orElse(null);
    }

    public ArrayList<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User addUser(User user) {
        if (getUserByFirstNameAndLastName(user.getFirstName(), user.getLastName()) != null) {
            return user;
        }
        return userDao.addUser(user.setId(getNextId()));
    }

    public int count() {
        return userDao.getAllUsers().size();
    }

    public void saveData(ArrayList<User> users){
        FileWorker.serialize(filename, users);
    }

    public ArrayList<User> prepareData() {
        return FileWorker.deserialize(filename);
    }

    public void loadData(ArrayList<User> users){
        userDao.loadData(users);
    }

    public User getUserByLoginAndPassword(String login, String password) {
        return userDao.
                getAllUsers().
                stream().
                filter(user -> user.getLogin() != null).
                filter(user -> user.getPassword() != null).
                filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password)).
                findFirst().
                orElse(null);
    }

    public boolean unlinkData() {
        return FileWorker.unlinkData(filename);
    }

    public void clearUsers() {
        userDao.clearUsers();
    }

}
