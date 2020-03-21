package ergaf.step.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    UserController userController;

    @BeforeEach
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
}
