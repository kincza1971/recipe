package recipe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;

public class EntityNotFoundExceptionHandler {

    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae, String uri, String title) {
        Problem problem = Problem.builder()
                .withType(URI.create(uri))
                .withTitle(title)
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);

    }
}
