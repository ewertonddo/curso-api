package br.com.ewerton.api.service;

import br.com.ewerton.api.domain.Users;

import java.util.List;

public interface UserService {

        Users findById(Integer id);
        List<Users> findAll();
}
