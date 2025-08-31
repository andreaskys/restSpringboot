package br.com.andreas;

import br.com.andreas.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll(){
        logger.info("finding all people" );
        List<Person> persons = new ArrayList<Person>();
        for(int i = 0; i < 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(String id){
        logger.info("finding one person" );

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("niki");
        person.setLastName("lauda");
        person.setAddress("austria");
        person.setGender("Male");
        return person;
    }

    public Person create(Person person){
        logger.info("Creating one person" );
        return person;
    }

    public Person update(Person person){
        logger.info("Updating one person" );
        return person;
    }

    public void delete(String id){
        logger.info("Excluding one person" );

    }




    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person" + i);
        person.setLastName("lastName" + i);
        person.setAddress("address" + i);
        person.setGender("Male");
        return person;

    }

}
