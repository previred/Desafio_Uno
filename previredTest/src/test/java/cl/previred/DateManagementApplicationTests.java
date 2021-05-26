package cl.previred;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class DateManagementApplicationTests {

	@Test
	public void dataManagementApplicationTests() {
		assertTrue(Boolean.TRUE);
	}
}

