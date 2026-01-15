package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.dto.CategoryDto;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)  
	public List<CategoryDto>findAll(){
		List<Category> list = repository.findAll();
		
		
		/*
		List<CategoryDto> listDto = new ArrayList<>();
		for(Category cat:list) {  //pra cada elemento na lista cat
			listDto.add(new CategoryDto(cat));  
		}
		*/
		
		//lambda
		
		return list.stream().map(x -> new CategoryDto(x)).collect(Collectors.toList());
		
	}

	public CategoryDto findById(long id) {
		
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Categoria nao encontrada"));
		return new CategoryDto(entity);
	}
}
