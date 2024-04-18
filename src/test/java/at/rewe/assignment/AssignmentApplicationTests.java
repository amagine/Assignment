package at.rewe.assignment;

import at.rewe.assignment.controller.EmailController;
import at.rewe.assignment.controller.StatsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AssignmentApplicationTests {

	@Autowired
	private EmailController emailController;

	@Autowired
	private StatsController statsController;

	@Test
	void contextLoads() throws Exception {
		assertThat(emailController).isNotNull();
		assertThat(statsController).isNotNull();
	}

}
