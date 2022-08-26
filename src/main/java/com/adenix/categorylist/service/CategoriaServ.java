package com.adenix.categorylist.service;
import com.adenix.categorylist.domain.Categoria;
import com.adenix.categorylist.exception.BadRequestException;
import com.adenix.categorylist.repository.CategoriaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoriaServ{
    @Autowired
    private final CategoriaRepo categoriaRepo;
    public List<Categoria> listAll(){
        return categoriaRepo.findAll();

    }
    public Categoria findById(long id){
        return  categoriaRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Categoria não encontrado"));

    }

    public Categoria findByIdThrowBadRequestException(long id){

        return categoriaRepo.findById(id)
                .orElseThrow(()-> new BadRequestException("Categoria Id not found."));
    }

    public Page<Categoria> listAll(Pageable pageable) {

        return categoriaRepo.findAll(pageable);
    }

    public List<Categoria> listAllNonPageable() {
        return categoriaRepo.findAll();
    }
}