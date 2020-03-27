package ergaf.step.services;

import ergaf.step.dao.UserDao;
import ergaf.step.entities.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService;

    private final static String TEST_DB = "users.test.data";

    @Before
    public void executedBeforeEach() {
        userService = new UserService(
                new UserDao(),
                TEST_DB
        );
    }

    @Test
    public void get_users_should_return_users() {
        //given
        User user = new User("A", "B");
        //when
        userService.addUser(user);
        //then
        assertEquals(1, userService.count());
    }

    @Test
    public void save_data_generates_db_when_called() {
        //given
        User user = new User("A", "B");
        userService.addUser(user);
        //when
        List<User> users = userService.getAllUsers();
        userService.saveData(users);
        //then
        userService.loadData(userService.prepareData());
        assertEquals(users, userService.getAllUsers());
    }

    @Test
    public void get_next_id_should_return_one_when_new_user_added_to_empty_collection() {
        //given
        User user = new User("A", "B");
        userService.addUser(user);
        //when
        //then
        assertEquals(1, userService.getUserById(1).getId());
    }

    @Test
    public void get_next_id_should_return_two_when_new_user_added_to_non_empty_collection() {
        //given
        userService.addUser(new User("A", "B"));
        userService.addUser(new User("C", "D"));
        //when
        //then
        assertEquals("D", userService.getUserById(2).getLastName());
    }
}
