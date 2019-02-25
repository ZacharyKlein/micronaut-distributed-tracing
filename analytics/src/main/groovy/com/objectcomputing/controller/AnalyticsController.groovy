package com.objectcomputing.controller

import groovy.util.logging.Slf4j
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.tracing.annotation.NewSpan
import io.micronaut.tracing.annotation.SpanTag
import io.opentracing.Tracer

import javax.inject.Inject

@Slf4j
@Controller("/analytics")
class AnalyticsController {

    Map<String, Integer> hits = [:]

    @Inject Tracer tracer

    @Post("/{partNumber}")
    HttpResponse hit(String partNumber) {
        String username = tracer.activeSpan().getBaggageItem("username")

        log.info "Recording hit for ${partNumber} by ${username}"

        HttpResponse.ok()
    }


    @NewSpan("hits")
    @Get("/{partNumber}")
    HttpResponse<Integer> hits(@SpanTag("product") String partNumber) {
        log.info "Returning hits for ${partNumber}"

        Integer result = hits.get(partNumber)

        if(!result) hits.put(partNumber, 1)
        else { hits.put(partNumber, result + 1)}

        HttpResponse.ok(result)
    }

}
