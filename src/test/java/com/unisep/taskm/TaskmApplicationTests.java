package com.unisep.taskm;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.core.ParameterizedTypeReference;
// import org.springframework.data.domain.Page;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import com.unisep.taskm.dto.UserDTO;
// import com.unisep.taskm.models.User;
// import com.unisep.taskm.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskmApplicationTests {

	// @Autowired
	// private TestRestTemplate restTemplate;

	// @Autowired
	// private UserRepository userRepository;

	// @BeforeEach
	// public void setup() {
	// 	User user1 = new User("user1nome", "user1email", "user1senha");
	// 	User user2 = new User("user2nome", "user2email", "user2senha");

	// 	userRepository.saveAll(List.of(user1, user2));
	// }

	// @Test
	// void testGetAllUsers() {
	// 	ResponseEntity<Page<UserDTO>> response = restTemplate.exchange(
	// 		"/users",
	// 		HttpMethod.GET,
	// 		null,
	// 		new ParameterizedTypeReference<>() {}
	// 	);

	// 	assertEquals(HttpStatus.OK, response.getStatusCode());
	// 	Page<UserDTO> page = response.getBody();
	// 	assertNotNull(page);
	// 	List<UserDTO> users = page.getContent();
	// 	assertNotNull(users);
	// 	assertEquals(2, users.size());
	// }

	// @AfterEach
	// public void deleteSetup() {
	// 	Optional<User> user1 = userRepository.findByNome("user1");
	// 	Optional<User> user2 = userRepository.findByNome("user2");

	// 	user1.ifPresent(userRepository::delete);
	// 	user2.ifPresent(userRepository::delete);
	// } 
}
