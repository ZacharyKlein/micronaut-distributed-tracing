package com.objectcomputing.service

import com.objectcomputing.api.ProductDetails
import com.objectcomputing.client.AnalyticsClient
import com.objectcomputing.client.InventoryClient

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorefrontService {

    @Inject
    InventoryClient inventoryClient

    @Inject
    AnalyticsClient analyticsClient

    List<ProductDetails> productList() {
        List<ProductDetails> products = inventoryClient
                .list(10, 0)
                .body()

        products = products.collect { p ->
            p.hits = analyticsClient.hits(p.partNumber).body()
            p
        }

        products
    }
}
