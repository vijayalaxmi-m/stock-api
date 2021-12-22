package com.assignment.controller;

import com.assignment.exception.ResourceNotFoundException;
import com.assignment.model.Stock;
import com.assignment.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;
    @GetMapping
    public List<Stock> getAll(){
        return stockService.getAllStocks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Stock> create(@RequestBody Stock stock){
        return new ResponseEntity<Stock>(stockService.saveStock(stock),HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Stock> update(@PathVariable(value="id") Long id, @Validated @RequestBody Stock stock){

        try {
         /*   Stock stockToUpdate = stockService.getStockById(id);
            stockToUpdate.setCurrentPrice(stock.getCurrentPrice());*/
            return new ResponseEntity<Stock>(stockService.updateStock(id, stock),HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Stock Not Found", e);
        }

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Stock> findById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<Stock>(stockService.getStockById(id),HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Stock Not Found", e);
        }
    }

    @DeleteMapping (value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            stockService.deleteStock(id);
            return new ResponseEntity<String>("Deleted",HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Stock Not Found", e);
        }

    }



}
