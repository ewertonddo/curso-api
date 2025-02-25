package br.com.ewerton.api.service.impl;

import br.com.ewerton.api.domain.Users;
import br.com.ewerton.api.domain.dto.UsersDTO;
import br.com.ewerton.api.repositories.UserRepository;
import br.com.ewerton.api.service.exceptions.DataIntegrityViolationException;
import br.com.ewerton.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "nomeTeste";
    public static final String MAIL = "teste@teste.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;
    public static final String E_MAIL_JA_CADASTRADO = "E-mail já cadastrado!";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private Users users;
    private UsersDTO usersDTO;
    private Optional<Users> optionalUsers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUsers);
        Users response = service.findById(ID);
        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(MAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFounException() {
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException("Object not found!"));
        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Object not found!", ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(users));
        List<Users> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Users.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(MAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(Mockito.any())).thenReturn(users);

        Users response = service.create(usersDTO);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(MAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnADataIntegrityViolation() {
        when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUsers);

        try {
            optionalUsers.get().setId(2);
            Users response = service.create(usersDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(Mockito.any())).thenReturn(users);

        Users response = service.update(usersDTO);

        assertNotNull(response);
        assertEquals(Users.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(MAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnDataIntegrityViolation() {
        when(repository.findByEmail(Mockito.anyString()))
                .thenThrow(new DataIntegrityViolationException(E_MAIL_JA_CADASTRADO));

        try {
            Users response = service.update(usersDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO, ex.getMessage());
        }
    }

    @Test
    void delete() {
    }

    private void startUsers(){
        users = new Users(ID, NAME, MAIL, PASSWORD);
        usersDTO = new UsersDTO(ID, NAME, MAIL, PASSWORD);
        optionalUsers = Optional.of(new Users(ID, NAME, MAIL, PASSWORD));
    }
}