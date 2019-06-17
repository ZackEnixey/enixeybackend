package com.project.webapp.service;

import java.util.List;

import com.project.webapp.dto.response.ItemDto;

public interface ItemService {

	List<ItemDto> getAllItems();

	ItemDto getItemById(int id);

}
