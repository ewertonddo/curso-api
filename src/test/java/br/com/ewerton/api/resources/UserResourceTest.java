package br.com.ewerton.api.resources;

import br.com.ewerton.api.domain.Users;
import br.com.ewerton.api.domain.dto.UsersDTO;
import br.com.ewerton.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UserResourceTest {

    public static final int ID = 1;
    public static final String NAME = "nome";
    public static final String MAIL = "email@teste.com";
    public static final String PASSWORD = "123";
    @InjectMocks
    private UserResource resource;

    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;

    private Users users;
    private UsersDTO usersDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startMock();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(users);
        when(mapper.map(any(), any())).thenReturn(usersDTO);

        ResponseEntity<UsersDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UsersDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(MAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startMock() {
        users = new Users(ID, NAME, MAIL, PASSWORD);
        usersDTO = new UsersDTO(ID, NAME, MAIL, PASSWORD);
    }
}