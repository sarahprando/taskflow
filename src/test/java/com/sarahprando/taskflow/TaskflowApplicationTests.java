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
				.jsonPath("$.title").isEqualTo(task.getTitle())
				.jsonPath("$.description").isEqualTo(task.getDescription())
				.jsonPath("$.status").isEqualTo(task.getStatus())
				.jsonPath("$.priority").isEqualTo(task.getPriority())
				.jsonPath("$.deadline").isEqualTo(task.getDeadline());
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
	void testGetByIdTaskSuccess() {
		var task = new Task(
				"task 1",
				"description 1",
				"PENDING",
				"MEDIUM",
				"2024-06-30T12:00:00");

		var createdTask = webTestClient
				.post()
				.uri("/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Task.class)
				.returnResult()
				.getResponseBody();

		webTestClient
				.get()
				.uri("/tasks/{id}", createdTask.getId())
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.id").isEqualTo(createdTask.getId());

	}

	@Test
	void testGetByIdTaskInvalidRoute() {
		webTestClient
				.get()
				.uri("/tasks/")
				.exchange()
				.expectStatus().isNotFound()
				.expectBody();
	}

	@Test
	void testGetByIdTaskNotFound() {
		var task = new Task(
				"task 1",
				"description 1",
				"PENDING",
				"MEDIUM",
				"2024-06-30T12:00:00");

		var createdTask = webTestClient
				.post()
				.uri("/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Task.class)
				.returnResult()
				.getResponseBody();

		webTestClient
				.get()
				.uri("/tasks/{id}", createdTask.getId() + 1)
				.exchange()
				.expectStatus().isNotFound();
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

		var createdTask = webTestClient
				.post()
				.uri("/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Task.class)
				.returnResult()
				.getResponseBody();

		webTestClient
				.get()
				.uri("/tasks/{id}", createdTask.getId())
				.exchange()
				.expectBody()
				.jsonPath("$.id").isEqualTo(createdTask.getId());

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

	@Test
	void testDeleteTaskNotFound() {
		var task = new Task(
				"task 1",
				"description 1",
				"PENDING",
				"MEDIUM",
				"2024-06-30T12:00:00");

		var createdTask = webTestClient
				.post()
				.uri("/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Task.class)
				.returnResult()
				.getResponseBody();

		webTestClient
				.delete()
				.uri("/tasks/{id}", createdTask.getId() + 1)
				.exchange()
				.expectStatus().isNotFound();
	}
}