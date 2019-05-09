package com.lego.lovers.people.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lego.lovers.people.model.People;

@Repository
public interface PeopleRepository 
    extends JpaRepository<People, Long> { }
