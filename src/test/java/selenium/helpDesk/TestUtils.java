package selenium.helpDesk;

import com.github.javafaker.Faker;

public class TestUtils {
    private static final Faker faker = new Faker();

    public static String generateFakeEmail() {
        return faker.internet().emailAddress();
    }
}
