package br.com.andreas.services;

import br.com.andreas.controllers.BookController;
import br.com.andreas.data.dto.BookDTO;
import br.com.andreas.exception.RequiredObjectIsNullException;
import br.com.andreas.exception.ResourceNotFoundException;
import br.com.andreas.model.Book;
import br.com.andreas.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.andreas.mapper.ObjectMapper.parseListObjects;
import static br.com.andreas.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookServices {

    private Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    public List<BookDTO> findAll(){
        logger.info("finding all book" );
        var books = parseListObjects(repository.findAll(), BookDTO.class);
        books.forEach(this::addHateoasLinks);
        return books;
    }

    public BookDTO findById(Long id){
        logger.info("finding one book" );
        var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO create(BookDTO book){

        if(book == null)throw new RequiredObjectIsNullException();
        logger.info("Creating one book" );
        var entity = parseObject(book, Book.class);
        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO book){
        if(book == null)throw new RequiredObjectIsNullException();
        logger.info("Updating one book");
        Book entity = repository.findById(book.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setTitle(book.getTitle());
        entity.setPrice(book.getPrice());
        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id){
        logger.info("Excluding one book" );
        Book entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this Id"));
        repository.delete(entity);
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
