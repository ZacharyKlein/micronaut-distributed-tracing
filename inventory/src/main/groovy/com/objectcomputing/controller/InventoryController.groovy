package com.objectcomputing.controller

import com.objectcomputing.client.AnalyticsClient
import com.objectcomputing.domain.Product
import com.objectcomputing.service.ProductService
import com.objectcomputing.service.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.opentracing.Tracer

import javax.annotation.Nullable
import javax.inject.Inject

@Controller("/inventory")
class InventoryController {

    @Inject ProductService productService
    @Inject UserService userService

    @Inject AnalyticsClient analyticsClient

    @Inject Tracer tracer

    @Get("/{id}")
    HttpResponse<Product> show(Serializable id) {
        Product product = productService.find(id)
        if(product) {
            tracer.activeSpan().setBaggageItem("username", userService.currentUser())
            analyticsClient.hit(product.partNumberr)

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
