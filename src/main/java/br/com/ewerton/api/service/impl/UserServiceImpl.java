package br.com.ewerton.api.service.impl;

import br.com.ewerton.api.domain.Users;
import br.com.ewerton.api.domain.dto.UsersDTO;
import br.com.ewerton.api.repositories.UserRepository;
import br.com.ewerton.api.service.UserService;
import br.com.ewerton.api.service.exceptions.DataIntegratyViolationException;
import br.com.ewerton.api.service.exceptions.ObjectNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    @Override
    public List<Users> findAll() {
        return repository.findAll();
    }

    @Override
    public Users create(UsersDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Users.class));
    }

    private void findByEmail(UsersDTO obj) {
        Optional<Users> user = repository.findByEmail(obj.getEmail());
        if (user.isPresent()) {
            throw new DataIntegratyViolationException("E-mail já cadastrado!");
        }
    }
}