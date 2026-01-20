package com.devsuperior.dscatalog.entities.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

public class ProductDto {

	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	private Instant date; 
	
	private List<CategoryDto> categories = new ArrayList<>();
	
	public ProductDto() {
		
	}

	public ProductDto(Long id, String name, String description, Double price, String imgUrl, Instant date) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.date = date;
	}
	
	public ProductDto(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.imgUrl = entity.getImgUrl();
		this.date = entity.getDate();
	}
	
	public ProductDto(Product entity, Set<Category> categories) {
		this(entity); //esse this entity faz as atribuicoes automaticamente
		categories.forEach(cat -> this.categories.add(new CategoryDto(cat)));
		
		//pra cada elemento passado pelo argumento categories cat; this.categories q é o categories da classe, adiciona um new passando cat
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public Instant getDate() {
		return date;
	}

	public List<CategoryDto> getCategories() {
		return categories;
	}

	

	
	
}
