package com.project.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringBootApplicationTests {

	@Test
	void contextLoads()
	{
		assertEquals(true,2%2==0);
	}

}
