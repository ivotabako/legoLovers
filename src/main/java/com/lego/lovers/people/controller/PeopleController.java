package com.lego.lovers.people.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lego.lovers.people.repository.PeopleRepository;

@RestController
@RequestMapping({"/people"})
public class PeopleController {

  private PeopleRepository repository;

  PeopleController(PeopleRepository peopleRepository) {
      this.repository = peopleRepository;
  }

  @GetMapping
  public List findAll(){
    return repository.findAll();
  }
}
