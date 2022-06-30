package com.example.myhouse.controller;

import com.example.myhouse.model.Myhouse;
import com.example.myhouse.repository.MyhouseRepository;
import com.example.myhouse.service.MyhouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/houses")
public class MyhouseController {
    @Autowired
    MyhouseServiceImpl myhouseService;
//@GetMapping("")
//    public ResponseEntity<List<Myhouse>> findAll(){
//    List<Myhouse> myhouses = myhouseService.findAll();
//    if (myhouses.isEmpty()) {
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
//    }
//    return new ResponseEntity<>(myhouses, HttpStatus.OK);}
@GetMapping
public ResponseEntity<Page<Myhouse>> findAllHouse(@PageableDefault(value =3) Pageable pageable) {
    Page<Myhouse> houses = myhouseService.findAll(pageable);
    if (houses.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(houses, HttpStatus.OK);
}
    @PostMapping("")
    public ResponseEntity<Myhouse> create( @RequestParam("file") MultipartFile file, Myhouse myhouse){
        String fileName = file.getOriginalFilename();
        myhouse.setImage(fileName);
        try {
            file.transferTo(new File("D:\\myhouse\\src\\main\\resources\\image\\" + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    Myhouse myhouses = myhouseService.save(myhouse);

        return new ResponseEntity<>(myhouses, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Myhouse> findById(@PathVariable("id") int id){
        Myhouse myhouses = myhouseService.findById(id).get();
        return new ResponseEntity<>(myhouses, HttpStatus.CREATED);
    }
//    @PutMapping( "/{id}")
//    public ResponseEntity<Myhouse> updateCustomer(@PathVariable("id") int id, @RequestBody Myhouse myhouse) {
//        Myhouse myhouseCurrent =myhouseService.findById(id).get();
//        if (myhouseCurrent == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        myhouse.setId(myhouseCurrent.getId());
//        Myhouse myhousess = myhouseService.save(myhouse);
//        return new ResponseEntity<>(myhousess, HttpStatus.OK);
//    }
    @PutMapping( "/{id}")
    public ResponseEntity<Myhouse> updateCustomer(@PathVariable("id") int id, @RequestParam("file") MultipartFile file,@Valid Myhouse myhouse) {
        String fileName = file.getOriginalFilename();
        if (fileName.equals("")) {
            fileName = myhouseService.findById(id).get().getImage();
            myhouse.setImage(fileName);

        } else {
            myhouse.setImage(fileName);
            try {
                file.transferTo(new File("D:\\myhouse\\src\\main\\resources\\image\\" + fileName));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        myhouse.setId(id);
        return new ResponseEntity<>(myhouseService.save(myhouse), HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Myhouse> deleteCustomer(@PathVariable("id") int id) {
        Myhouse myhouse = myhouseService.findById(id).get();

        if (myhouse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        myhouseService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
