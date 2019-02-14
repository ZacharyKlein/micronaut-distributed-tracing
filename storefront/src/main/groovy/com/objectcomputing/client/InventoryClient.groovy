package com.objectcomputing.client

import com.objectcomputing.api.ProductDetails
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

import javax.annotation.Nullable

@Client(id = "inventory", path = "/inventory")
interface InventoryClient {

    @Get("/{id}")
    HttpResponse<ProductDetails> show(Serializable id)

    @Get("/{?max,offset}")
    HttpResponse<List<ProductDetails>> list(@Nullable Long max, @Nullable Long offset)
}