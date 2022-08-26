package com.adenix.categorylist.repository;
import com.adenix.categorylist.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepo extends JpaRepository<Categoria,Long>{

}