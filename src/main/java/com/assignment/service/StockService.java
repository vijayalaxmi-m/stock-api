package com.assignment.service;

import com.assignment.exception.ResourceNotFoundException;
import com.assignment.model.Stock;
import com.assignment.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    /**
     * saves to database
     */
    public Stock saveStock(Stock stock){
        return stockRepository.save(stock);
    }

    /**
     * get all stocks
     */
    public List<Stock> getAllStocks(){
        return stockRepository.findAll();
    }

    /**
     * get stock by id
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    public Stock getStockById(long id) throws ResourceNotFoundException{
        Optional<Stock> stock = stockRepository.findById(id);
        if(stock.isPresent()){
            return stock.get();
        }else{
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    /**
     * delete stock
     * @param id
     * @throws ResourceNotFoundException
     */
    public void deleteStock(long id) throws ResourceNotFoundException{
        Optional<Stock> stock = stockRepository.findById(id);
        if(stock.isPresent()){
            stockRepository.delete(stock.get());
        }else{
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    /**
     * update stock
     */
    public Stock updateStock(long id, Stock stock) throws ResourceNotFoundException{
        Optional<Stock> stockToUpdate = stockRepository.findById(id);
        if(!stockToUpdate.isPresent()){
            throw new ResourceNotFoundException("Resource Not Found!!!!");
        }else{
            Stock tempStock = stockToUpdate.get();
            tempStock.setCurrentPrice(stock.getCurrentPrice());
            stockRepository.save(tempStock);
            return tempStock;
        }
    }
}
