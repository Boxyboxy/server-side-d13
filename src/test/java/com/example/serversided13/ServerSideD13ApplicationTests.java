package com.example.serversided13;

import com.example.serversided13.models.ContactModel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerSideD13ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testContact() throws Exception {
		ContactModel c = new ContactModel();
		c.setName("Eugene");
		c.setEmail("a@a.com");
		c.setPhoneNumber("82232756");
		// assert equals to the setter value
		// assertEquals(c.getEmail(), "a@a.com");
	}
}
