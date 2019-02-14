package com.objectcomputing.service

import com.objectcomputing.api.ProductDetails
import com.objectcomputing.client.AnalyticsClient
import com.objectcomputing.client.InventoryClient
import io.micronaut.tracing.annotation.NewSpan
import io.opentracing.Tracer

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorefrontService {

    @Inject
    InventoryClient inventoryClient

    @Inject
    AnalyticsClient analyticsClient

    @Inject
    Tracer tracer

    @NewSpan("productList")
    List<ProductDetails> productList() {
        List<ProductDetails> products = inventoryClient
                .list(10, 0)
                .body()

        products = products.collect { p ->
            p.hits = analyticsClient.hits(p.partNumber).body()
            p
        }

        tracer.activeSpan().setTag("count", products.size())

        products
    }
}
