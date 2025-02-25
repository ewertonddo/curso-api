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

import static org.junit.jupiter.api.Assertions.*;

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
    void findById() {
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