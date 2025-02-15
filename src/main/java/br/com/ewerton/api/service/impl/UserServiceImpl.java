package br.com.ewerton.api.service.impl;

import br.com.ewerton.api.domain.Users;
import br.com.ewerton.api.repositories.UserRepository;
import br.com.ewerton.api.service.UserService;
import br.com.ewerton.api.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Object not found!"));
    }

    public List<Users> findAll(){
        return repository.findAll();
    }
}
