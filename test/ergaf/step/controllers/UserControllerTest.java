package ergaf.step.controllers;

import ergaf.step.dao.UserDao;
import ergaf.step.services.UserService;
import ergaf.step.entities.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserControllerTest {

    UserController userController;

    @Before
    public void executedBeforeEach() {
        userController = new UserController(
                new UserService(
                        new UserDao()
                )
        );
    }

    @Test
    public void add_the_same_name_user_return_the_same_user() {
        //given
        userController.addUser(new User("Piter", "Pen"));
        userController.addUser(new User("Piter", "Pen"));
        //when
        //then
        assertEquals(1, userController.count());
    }

    @Test
    public void add_the_same_name_user_return_the_same_user_from_controller() {
        //given
        User user1 = userController.addUser(new User("Piter", "Pen"));
        User user2 = userController.addUser(new User("Piter", "Pen"));
        //when
        //then
        assertEquals(user1, user2);
    }

    @Test
    public void get_user_by_first_name_and_last_name_return_user_when_exists() {
        //given
        User user1 = userController.addUser(new User("Piter", "Pen"));
        //when
        User user2 = userController.getUserByFirstNameAndLastName("Piter", "Pen");
        //then
        assertEquals(user1, user2);
    }

    @Test
    public void get_user_by_first_name_and_last_name_does_not_return_user_when_non_exists() {
        //given
        userController.addUser(new User("Piter", "Pen"));
        //when
        //then
        assertNull(userController.getUserByFirstNameAndLastName("Piters", "Pen"));
    }

    @Test
    public void get_user_by_login_and_password_return_user_when_exists() {
        //given
        User user1 = userController.addUser(new User("Piter", "Pen").setLogin("a").setPassword("b"));
        //when
        User user2 = userController.getUserByLoginAndPassword("a", "b");
        //then
        assertEquals(user1, user2);
    }

    @Test
    public void get_user_by_login_and_password_does_not_return_user_when_non_exists() {
        //given
        userController.addUser(new User("Piter", "Pen").setLogin("a").setPassword("b"));
        //when
        //then
        assertNull(userController.getUserByLoginAndPassword("aa", "b"));
    }
}
