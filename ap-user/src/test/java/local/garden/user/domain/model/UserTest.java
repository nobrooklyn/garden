package local.garden.user.domain.model;

import java.time.LocalDate;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class UserTest {
    @Test
    public void test_age() {
        User u = new User();
        u.setId("A");
        u.setFirstName("john");
        u.setLastName("Dowe");
        u.setWeight(65);
        u.setHeight(174);
        u.setBirthday(LocalDate.of(1980, 1, 8));
        Assert.assertThat("test age", u.age(), CoreMatchers.is(38L));
    }
}