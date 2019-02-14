package com.objectcomputing.controller

import groovy.util.logging.Slf4j
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.tracing.annotation.NewSpan
import io.micronaut.tracing.annotation.SpanTag

@Slf4j
@Controller("/analytics")
class AnalyticsController {

    Map<String, Integer> hits = [:]

    @Post("/{productNumber}")
    HttpResponse hit(String productNumber) {
        log.info "Recording hit for ${productNumber}"

        //TODO:
    }


    @NewSpan("hits")
    @Get("/{productNumber}")
    HttpResponse<Integer> hits(@SpanTag("product") String productNumber) {
        log.info "Returning hits for ${productNumber}"

        Integer result = hits.get(productNumber)

        if(!result) hits.put(productNumber, 1)
        else { hits.put(productNumber, result + 1)}

        HttpResponse.ok(result)
    }

}
