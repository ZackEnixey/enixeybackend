package com.project.webapp.service;

import java.util.List;

import com.project.webapp.dto.response.CourierServiceDto;
import com.project.webapp.model.CourierService;

public interface CourierServiceService {

	CourierServiceDto createCourierService(CourierServiceDto courierServiceDto);

	List<CourierServiceDto> getAllCourierServices();

	CourierServiceDto getCountryById(int id);

	CourierServiceDto editCourierService(int id, CourierServiceDto courierServiceDto);

	CourierServiceDto deleteCountryById(int id);

}
