package br.com.ewerton.api.service;

import br.com.ewerton.api.domain.Users;
import br.com.ewerton.api.domain.dto.UsersDTO;

import java.util.List;

public interface UserService {

        Users findById(Integer id);
        List<Users> findAll();
        Users create(UsersDTO obj);
}
