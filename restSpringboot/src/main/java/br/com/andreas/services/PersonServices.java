package br.com.andreas.services;

import br.com.andreas.data.dto.PersonDTO;
import br.com.andreas.exception.ResourceNotFoundException;
import static br.com.andreas.mapper.ObjectMapper.parseListObjects;
import static br.com.andreas.mapper.ObjectMapper.parseObject;
import br.com.andreas.model.Person;
import br.com.andreas.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll(){
        logger.info("finding all people" );
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id){
        logger.info("finding one person" );
        var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));
        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Creating one person" );
        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person){
        logger.info("Updating one person");
        Person entity = repository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("Excluding one person" );
        Person entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));
        repository.delete(entity);
    }
}
