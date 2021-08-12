package recipe.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

import java.net.URI;

public class EntityNotFoundException extends AbstractThrowableProblem {
    public EntityNotFoundException(String uriString, String problemDetails) {
        super(
                URI.create(uriString),
                "Not found",
                Status.NOT_FOUND,
                problemDetails
        );

    }
}
