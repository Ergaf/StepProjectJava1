package ergaf.step.dao;

import ergaf.step.entities.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void add_user_should_add_user_to_collection() {
        //given
        UserDao userDao = new UserDao();
        //when
        userDao.add(new User("Mark", "Tven").setId(1));
        //then
        assertEquals(1, userDao.getAll().size());
    }

    @Test
    public void add_the_same_user_not_increase_size_of_collection() {
        //given
        UserDao userDao = new UserDao();
        //when
        userDao.add(new User("Mark", "Tven").setId(1));
        userDao.add(new User("Mark", "Tven").setId(1));
        //then
        assertEquals(1, userDao.getAll().size());
    }

    @Test
    public void clear_users_should_clean_collection() {
        //given
        UserDao userDao = new UserDao();
        //when
        userDao.add(new User("Mark", "Tven").setId(1));
        userDao.add(new User("Mark", "Tven").setId(2));
        userDao.clear();
        //then
        assertEquals(0, userDao.getAll().size());
    }
}
