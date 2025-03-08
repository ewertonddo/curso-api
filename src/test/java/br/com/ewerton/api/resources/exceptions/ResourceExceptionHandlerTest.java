package br.com.ewerton.api.resources.exceptions;

import br.com.ewerton.api.service.exceptions.DataIntegrityViolationException;
import br.com.ewerton.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class ResourceExceptionHandlerTest {

    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";
    public static final String EMAIL_JA_CADASTRADO = "Email já cadastrado!";
    @InjectMocks
    private ResourceExceptionHandler exception;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exception
                .objectNotFound(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());

    }
    @Test
    void whenDataIntegrityViolationThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exception.dataIntegratyViolation(
            new DataIntegrityViolationException(EMAIL_JA_CADASTRADO),
            new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(EMAIL_JA_CADASTRADO, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}