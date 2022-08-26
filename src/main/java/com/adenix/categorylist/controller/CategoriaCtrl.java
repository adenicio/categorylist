package com.adenix.categorylist.controller;
import com.adenix.categorylist.domain.Categoria;
import com.adenix.categorylist.service.CategoriaServ;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("categoria")
@Log4j2
@RequiredArgsConstructor
public class CategoriaCtrl {
    @Autowired
    private final CategoriaServ categoriaServ;

    @GetMapping
    public ResponseEntity<Page<Categoria>> list(Pageable pageable){

//        log.info(dataUtil.formatDataLocalStilDatabase(LocalDateTime.now()));
        return ResponseEntity.ok(categoriaServ.listAll(pageable));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Categoria>> listAll(){

//        log.info(dataUtil.formatDataLocalStilDatabase(LocalDateTime.now()));
        return ResponseEntity.ok(categoriaServ.listAllNonPageable());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable long id){

//        log.info(dataUtil.formatDataLocalStilDatabase(LocalDateTime.now()));
        return ResponseEntity.ok(categoriaServ.findByIdThrowBadRequestException(id));
    }
}
