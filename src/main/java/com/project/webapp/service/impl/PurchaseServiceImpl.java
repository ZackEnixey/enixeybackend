package com.project.webapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.webapp.dto.request.PurchaseRequestDTO;
import com.project.webapp.dto.response.ProductDto;
import com.project.webapp.dto.response.PurchaseDto;
import com.project.webapp.model.AppUser;
import com.project.webapp.model.CourierService;
import com.project.webapp.model.Item;
import com.project.webapp.model.Product;
import com.project.webapp.model.Purchase;
import com.project.webapp.repository.AppUserRepository;
import com.project.webapp.repository.CourierServiceRepository;
import com.project.webapp.repository.ItemRepository;
import com.project.webapp.repository.ProductRepository;
import com.project.webapp.repository.PurchaseRepository;
import com.project.webapp.service.PurchaseService;
import com.project.webapp.util.WebShopException;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Autowired
	CourierServiceRepository courierServiceRepository;
	
	@Autowired
	ItemRepository ItemRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<PurchaseDto> getAllPurchases() {
		System.out.println(" 0-0 ");
		List<Purchase> purchases = purchaseRepository.findAll();
		List<PurchaseDto> purchasesDto = new ArrayList<PurchaseDto>();
		for(Purchase purchase: purchases) {
			purchasesDto.add(new PurchaseDto(purchase));
		}
		System.out.println("purchase: " + purchases.size());
		return purchasesDto;
	}

	@Override
	public PurchaseDto getProductById(int id) {
		Purchase purchase = purchaseRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no purchase with id: " + id));
		return new PurchaseDto(purchase);
	}

	@Override
	public PurchaseDto editProduct(int id, PurchaseDto purchaseDto) {
		Purchase purchase = purchaseRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no purchase with id: " + id));
		purchase = purchaseRepository.saveAndFlush(purchase);
		return new PurchaseDto(purchase);
	}

	@Override
	public PurchaseDto createNewProduct(PurchaseDto purchaseDto) {
		Purchase purchase = new Purchase(purchaseDto);
		purchase = purchaseRepository.save(purchase);
		return new PurchaseDto(purchase);
	}

	@Override
	public PurchaseDto deleteProduct(int id) {
		Purchase purchase = purchaseRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no purchase with id: " + id));
		purchase.setDeleted(false);
		purchase = purchaseRepository.saveAndFlush(purchase);//logicki!
		return new PurchaseDto(purchase);
	}

	@Override
	@Transactional
	public PurchaseDto buy(PurchaseRequestDTO purchaseRequestDTO, String name) {
		System.out.println( purchaseRequestDTO);
		System.out.println(name);
		
		if(purchaseRequestDTO.getItemCounts().size() != purchaseRequestDTO.getItemIds().size()) {
			throw new WebShopException(HttpStatus.BAD_REQUEST, "Sizes of items' arrays do not match" );
		}
		Purchase purchase = new Purchase();
		
		purchase.setDate(new Date());
		purchase.setStorned(false);
		purchase.setDeleted(false);
		
		AppUser appUser = appUserRepository.findByEmail(name);
		purchase.setAppUser(appUser);
		
		CourierService courierService = courierServiceRepository.findById(purchaseRequestDTO.getIdCourierService()).orElseThrow( () -> new WebShopException(HttpStatus.NOT_FOUND, "courierSErvice was not found"));
		purchase.setCourierService(courierService);
		purchaseRepository.saveAndFlush(purchase);

		
		int totalPrice = 0;
		for(int i=0; i<purchaseRequestDTO.getItemIds().size(); i++) {
			Item item = new Item();
			item.setQuantity(purchaseRequestDTO.getItemCounts().get(i));
			item.setDeleted(false);
			Product product = productRepository.findById(purchaseRequestDTO.getItemIds().get(i)).orElseThrow( () -> new WebShopException(HttpStatus.NOT_FOUND, "product was not found"));
			item.setProduct(product);
			item.setPurchase(purchase);
			ItemRepository.saveAndFlush(item);
			totalPrice+= product.getPrice();
		}

	
		return new PurchaseDto(purchase);
	}

}
