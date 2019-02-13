package com.objectcomputing.bootstrap

import com.objectcomputing.service.ProductService
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerStartupEvent

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Bootstrap implements ApplicationEventListener<ServerStartupEvent> {

    @Inject ProductService productService

    @Override
    void onApplicationEvent(ServerStartupEvent event) {

        [
                [name: "Widget A", partNumber: "001", price: 10.00],
                [name: "Widget B", partNumber: "002", price: 15.00],
                [name: "Widget C", partNumber: "003", price: 20.00],
        ].each { details ->
            productService.save(
                    "${details.name}",
                    "${details.partNumber}",
                    "${details.price}".toBigDecimal())
        }

    }
}
