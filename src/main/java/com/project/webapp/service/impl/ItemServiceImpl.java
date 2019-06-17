package com.project.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.webapp.dto.response.ItemDto;
import com.project.webapp.model.Item;
import com.project.webapp.repository.ItemRepository;
import com.project.webapp.service.ItemService;
import com.project.webapp.util.WebShopException;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	ItemRepository itemRepository;
	
	@Override
	public List<ItemDto> getAllItems() {
		List<Item> items = itemRepository.findAll();
		System.out.println(items.size());
		List<ItemDto> itemsDto = new ArrayList<ItemDto>();
		for(Item item: items) {
			itemsDto.add(new ItemDto(item));
		}
		System.out.println(itemsDto.size());
		return itemsDto;
	}

	@Override
	public ItemDto getItemById(int id) {
		Item item = itemRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no ITEM with id: " + id ));
		return new ItemDto(item);
	}
	
	
	
}
