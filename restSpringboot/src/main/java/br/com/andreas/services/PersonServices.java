package br.com.andreas.services;

import br.com.andreas.exception.ResourceNotFoundException;
import br.com.andreas.model.Person;
import br.com.andreas.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll(){
        logger.info("finding all people" );
        return repository.findAll();

    }

    public Person findById(Long id){
        logger.info("finding one person" );

        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));
    }

    public Person create(Person person){
        logger.info("Creating one person" );

        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating one person");
        Person entity = repository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Excluding one person" );

        Person entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));

        repository.delete(entity);

    }


}
