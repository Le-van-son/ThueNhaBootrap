package com.example.myhouse.controller;

import com.example.myhouse.model.Myhouse;
import com.example.myhouse.repository.MyhouseRepository;
import com.example.myhouse.service.MyhouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/houses")
public class MyhouseController {
    @Autowired
    MyhouseServiceImpl myhouseRepository;
@GetMapping("")
    public ResponseEntity<List<Myhouse>> findAll(){
    List<Myhouse> myhouses = myhouseRepository.findAll();
    if (myhouses.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
    }
    return new ResponseEntity<>(myhouses, HttpStatus.OK);}
    @PostMapping("")
    public ResponseEntity<Myhouse> create(@Valid @RequestBody Myhouse myhouse){
    Myhouse myhouses = myhouseRepository.save(myhouse);
        return new ResponseEntity<>(myhouses, HttpStatus.CREATED);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Myhouse> findById(@PathVariable("id") int id){
        Myhouse myhouses = myhouseRepository.findById(id).get();
        return new ResponseEntity<>(myhouses, HttpStatus.CREATED);
    }
    @PutMapping( "/{id}")
    public ResponseEntity<Myhouse> updateCustomer(@PathVariable("id") int id, @RequestBody Myhouse myhouse) {
        Myhouse myhouseCurrent = myhouseRepository.findById(id).get();
        if (myhouseCurrent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        myhouse.setId(myhouseCurrent.getId());
        Myhouse myhousess = myhouseRepository.save(myhouse);
        return new ResponseEntity<>(myhousess, HttpStatus.OK);
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
