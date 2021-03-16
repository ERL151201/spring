package com.emanuel.market.persistence;

import com.emanuel.market.persistence.crud.ProductoCrudRepository;
import com.emanuel.market.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }
}
