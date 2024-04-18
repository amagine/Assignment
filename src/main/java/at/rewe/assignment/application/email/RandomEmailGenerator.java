package at.rewe.assignment.application.email;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomEmailGenerator implements EmailGenerator {

    @Override
    public String generate() throws IllegalStateException {
        Random random = new Random();

        String email;
        int provider = random.nextInt(3);

        email = switch (provider) {
            case 0 -> "user" + random.nextInt(1000) + "@gmail.com";
            case 1 -> "user" + random.nextInt(1000) + "@hotmail.com";
            case 2 -> "user" + random.nextInt(1000) + "@outlook.com";
            default -> throw new IllegalStateException("Unexpected value: " + provider);
        };

        return email;
    }
}
