package at.rewe.assignment;

import at.rewe.assignment.controller.EmailController;
import at.rewe.assignment.controller.StatsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class AssignmentApplicationTest {

	@Autowired
	private EmailController emailController;

	@Autowired
	private StatsController statsController;

	@Test
	void contextLoads() {
		assertThat(emailController).isNotNull();
		assertThat(statsController).isNotNull();
	}

	@Test
	void testMainMethod() {
		assertDoesNotThrow(() -> AssignmentApplication.main(new String[] {}));
	}

}
