package com.devsuperior.dscatalog.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.dto.CategoryDto;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	/*
	@Transactional(readOnly = true)  
	public List<CategoryDto>findAll(){
		List<Category> list = repository.findAll();
		
		
		
		List<CategoryDto> listDto = new ArrayList<>();
		for(Category cat:list) {  //pra cada elemento na lista cat
			listDto.add(new CategoryDto(cat));  
		}
		
		
		//lambda
		
		return list.stream().map(x -> new CategoryDto(x)).collect(Collectors.toList());
		
	}*/
	
	@Transactional(readOnly = true)
	public Page<CategoryDto> findAllPaged(Pageable pageable) {
		Page<Category> list = repository.findAll(pageable);
		return list.map(x -> new CategoryDto(x));
	}

	public CategoryDto findById(long id) {
		
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Categoria nao encontrada"));
		return new CategoryDto(entity);
	}
	
	@Transactional
	public CategoryDto insert(CategoryDto dto) {
		Category entity = new Category(null, dto.getName());
		entity = repository.save(entity);
		return new CategoryDto(entity);
	}

	@Transactional
	public CategoryDto update(Long id, CategoryDto dto) {
		try {
			Category entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDto(entity);
		}catch (jakarta.persistence.EntityNotFoundException e) {
			throw new EntityNotFoundException("Id nao encontrado");
		}
		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("Recurso nao encontrado");
		}
		
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Falha na integridade referencial");
		}
		
		
	}

	
}
