package com.lego.lovers.people.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lego.lovers.people.model.People;
import com.lego.lovers.people.repository.PeopleRepository;

@RestController
@RequestMapping({"/people"})
public class PeopleController {

  private PeopleRepository repository;

  PeopleController(PeopleRepository peopleRepository) {
      this.repository = peopleRepository;      
  }

  @GetMapping
  public List<People> findAll(){
    List<People> res = repository.findAll();
    return res;    
  }
  
  @GetMapping(path = {"/{id}"})
  public ResponseEntity<People> findById(@PathVariable long id){
    return repository.findById(id)
            .map(record -> ResponseEntity.ok().body(record))
            .orElse(ResponseEntity.notFound().build());
  }
  
  @PostMapping
  public People create(@RequestBody People person){
      return repository.save(person);
  }
  
  @PutMapping(value="/{id}")
  public ResponseEntity<People> update(@PathVariable("id") long id,
                                        @RequestBody People person){
    return repository.findById(id)
        .map(record -> {
            record.setName(person.getName());
            record.setEmail(person.getEmail());
            record.setPhone(person.getPhone());
            People updated = repository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
  }
  
  @DeleteMapping(path ={"/{id}"})
  public ResponseEntity<?> delete(@PathVariable("id") long id) {
    return repository.findById(id)
        .map(record -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
  }
}
