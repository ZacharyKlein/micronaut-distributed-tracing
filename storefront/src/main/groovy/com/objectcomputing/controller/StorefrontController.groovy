package com.objectcomputing.controller

import com.objectcomputing.api.ProductDetails
import com.objectcomputing.client.AnalyticsClient
import com.objectcomputing.client.InventoryClient
import com.objectcomputing.service.StorefrontService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

import javax.inject.Inject

@Controller("/")
class StorefrontController {

    @Inject
    StorefrontService storefrontService

    @Get("/")
    HttpResponse<List<ProductDetails>> index() {

        HttpResponse.ok(storefrontService.productList())
    }

}
