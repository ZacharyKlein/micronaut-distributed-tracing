package com.objectcomputing.service

import com.objectcomputing.domain.Product
import grails.gorm.services.Service

@Service(Product)
interface ProductService {

    Product save(String name, String partNumber, BigDecimal price)

    Product find(Serializable id)

    List<Product> list(Map args)

}