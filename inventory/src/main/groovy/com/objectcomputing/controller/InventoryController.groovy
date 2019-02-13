package com.objectcomputing.controller

import com.objectcomputing.domain.Product
import com.objectcomputing.service.ProductService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

import javax.annotation.Nullable
import javax.inject.Inject

@Controller("/inventory")
class InventoryController {

    @Inject ProductService productService

    @Get("/{id}")
    HttpResponse<Product> show(Serializable id) {
        Product product = productService.find(id)
        if(product) {
            HttpResponse.ok(product)
        } else {
            HttpResponse.notFound()
        }
    }

    @Get("/{?max,offset}")
    HttpResponse<List<Product>> list(@Nullable Long max, @Nullable Long offset) {
        HttpResponse.ok(productService.list([max: max, offset: offset]))
    }

}
