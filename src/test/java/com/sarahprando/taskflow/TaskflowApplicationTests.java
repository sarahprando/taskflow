package com.sarahprando.taskflow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.sarahprando.taskflow.entity.Task;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskflowApplicationTests {

	private WebTestClient webTestClient;

	@LocalServerPort
	private int port;

	@BeforeEach
	void setup() {
		this.webTestClient = WebTestClient
				.bindToServer()
				.baseUrl("http://localhost:" + port)
				.build();
	}

	@Test
	void testCreateTaskSuccess() {
		var task = new Task(
				"task 1",
				"description 1",
				"PENDING",
				"MEDIUM",
				"2024-06-30T12:00:00");

		webTestClient
				.post()
				.uri("/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].title").isEqualTo(task.getTitle())
				.jsonPath("$[0].description").isEqualTo(task.getDescription())
				.jsonPath("$[0].status").isEqualTo(task.getStatus())
				.jsonPath("$[0].priority").isEqualTo(task.getPriority())
				.jsonPath("$[0].deadline").isEqualTo(task.getDeadline());
	}

	@Test
	void testCreateTaskFailure() {
		webTestClient
				.post()
				.uri("/tasks")
				.bodyValue(
						new Task(
								"",
								"",
								"PENDING",
								"MEDIUM",
								"2024-06-30T12:00:00"))
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void testGetTaskSuccess() {
		webTestClient
				.get()
				.uri("/tasks")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	void testGetTaskEmptyList() {
		webTestClient
				.get()
				.uri("/tasks")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(0);
	}

	@Test
	void testUpdateTaskSuccess() {
		var task = new Task(
				"task 1",
				"description 1",
				"PENDING",
				"MEDIUM",
				"2024-06-30T12:00:00");

		webTestClient
				.put()
				.uri("/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	void testUpdateTaskFailure() {
		var task = new Task(
				"",
				"",
				"PENDING",
				"MEDIUM",
				"2024-06-30T12:00:00");

		webTestClient
				.put()
				.uri("/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void testUpdateTaskInvalidRoute() {
		var task = new Task(
				"task 1",
				"description 1",
				"PENDING",
				"MEDIUM",
				"2024-06-30T12:00:00");

		webTestClient
				.put()
				.uri("/tasks/")
				.bodyValue(task)
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	void testDeleteTaskSuccess() {

		var task = new Task(
				"task 1",
				"desc",
				"PENDING",
				"MEDIUM",
				"2024-06-30T12:00:00");

		webTestClient.post()
				.uri("/tasks")
				.bodyValue(task)
				.exchange();

		webTestClient.get()
				.uri("/tasks")
				.exchange()
				.expectBody()
				.jsonPath("$[0].id").value(id -> {

					webTestClient.delete()
							.uri("/tasks/{id}", id)
							.exchange()
							.expectStatus().isOk();
				});
	}

	@Test
	void testDeleteTaskInvalidRoute() {
		webTestClient
				.delete()
				.uri("/tasks/")
				.exchange()
				.expectStatus().isNotFound()
				.expectBody();
	}
}