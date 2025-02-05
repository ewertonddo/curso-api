package br.com.ewerton.api.config;

import br.com.ewerton.api.domain.Users;
import br.com.ewerton.api.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void startDB(){
        Users u1 = new Users(null, "Ewerton", "ew@teste.com", "123");
        Users u2 = new Users(null, "Sarah", "sa@teste.com", "123");

        repository.saveAll(List.of(u1,u2));
    }

}
