package com.objectcomputing.controller

import groovy.util.logging.Slf4j
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Slf4j
@Controller("/analytics")
class AnalyticsController {

    Map<String, Integer> hits = [:]

    @Post("/{productNumber}")
    HttpResponse hit(String productNumber) {
        log.info "Recording hit for ${productNumber}"

        //TODO:
    }


    @Get("/{productNumber}")
    HttpResponse<Integer> hits(String productNumber) {
        log.info "Returning hits for ${productNumber}"

        Integer result = hits.get(productNumber)

        if(!result) hits.put(productNumber, 1)
        else { hits.put(productNumber, result + 1)}

        HttpResponse.ok(result)
    }

}
