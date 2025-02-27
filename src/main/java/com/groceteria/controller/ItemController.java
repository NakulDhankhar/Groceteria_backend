package com.groceteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groceteria.entity.Category;
import com.groceteria.entity.Item;
import com.groceteria.entity.ItemPaging;
import com.groceteria.service.ItemService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/additems")
	public ResponseEntity<Item> addItem(@Valid @RequestBody Item item){
		return new ResponseEntity<Item>(itemService.addItem(item),HttpStatus.CREATED);	
	}
	
	@GetMapping()
	public List<Item> getAllItems(){
		return itemService.getAllItems();
	}
	
	@GetMapping("/items/{itemId}")
	public ResponseEntity<Item> getItemById(@PathVariable("itemId")long itemId){
		return new ResponseEntity<Item>(itemService.getItemByItemId(itemId),HttpStatus.OK);
	}
	
	@PutMapping("{itemId}")
	public ResponseEntity<Item> updateBook(@Valid@PathVariable("itemId")long itemId, @RequestBody Item item){
		return new ResponseEntity<Item>(itemService.updateItem(item, itemId),HttpStatus.OK);
	}
	
	@DeleteMapping("{itemId}")
	public ResponseEntity<Boolean> deleteItem(@PathVariable("itemId")long itemId){
		itemService.deleteItem(itemId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public List<Item> getAllItemsByCategory(@PathVariable("categoryId") int categoryId){
		Category c = Category.valueOf(categoryId);
		return itemService.findByCategory(c);
	}
	
	@GetMapping("/{categoryId}/{pageNo}/{pageSize}")
	public ItemPaging getAllItemsByCategory(@PathVariable("categoryId") int categoryId, @PathVariable("pageNo")int pageNo,@PathVariable("pageSize")int pageSize) {
		Category c=Category.valueOf(categoryId);
		return itemService.findByCategory(c, pageNo, pageSize);
		
	}
	@GetMapping("/{pageNo}/{pageSize}")
	public ItemPaging getAllItems(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
		return itemService.getAllItems(pageNo, pageSize);
	}
	
	@GetMapping("/mrp/{mrpPrice}")
	public List<Item> getByMRPPrice(@PathVariable("mrpPrice") double mrpPrice) {
		return itemService.findByMrpPrice(mrpPrice);
	}
	
	@GetMapping("/ItemSearch/{keyword}/{pageNo}/{pageSize}")
	public ItemPaging getItemByName (@PathVariable("keyword") String keyword,
			@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
		return itemService.findByItemName(keyword, pageNo, pageSize);
	}

}
