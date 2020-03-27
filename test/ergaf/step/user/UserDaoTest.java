package ergaf.step.user;

import ergaf.step.dao.UserDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void add_user_should_add_user_to_collection() {
        //given
        UserDao userDao = new UserDao();
        //when
        userDao.addUser(new User("Mark", "Tven").setId(1));
        //then
        assertEquals(1, userDao.getAllUsers().size());
    }

    @Test
    public void add_the_same_user_not_increase_size_of_collection() {
        //given
        UserDao userDao = new UserDao();
        //when
        userDao.addUser(new User("Mark", "Tven").setId(1));
        userDao.addUser(new User("Mark", "Tven").setId(1));
        //then
        assertEquals(1, userDao.getAllUsers().size());
    }

    @Test
    public void clear_users_should_clean_collection() {
        //given
        UserDao userDao = new UserDao();
        //when
        userDao.addUser(new User("Mark", "Tven").setId(1));
        userDao.addUser(new User("Mark", "Tven").setId(2));
        userDao.clearUsers();
        //then
        assertEquals(0, userDao.getAllUsers().size());
    }
}
