package com.project.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.webapp.model.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

}
