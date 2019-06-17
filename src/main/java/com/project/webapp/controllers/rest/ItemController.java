package com.project.webapp.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.webapp.dto.response.ItemDto;
import com.project.webapp.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	ItemService itemService;

	@GetMapping("")
	public ResponseEntity<List<ItemDto>> getAllItems() {
		List<ItemDto> response = itemService.getAllItems();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemDto> getItemById(@PathVariable int id) {
		ItemDto response = itemService.getItemById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
