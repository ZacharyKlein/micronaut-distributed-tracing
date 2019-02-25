package com.objectcomputing.client

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client(id = "analytics", path = "/analytics")
interface AnalyticsClient {

    @Get("/{partNumber}")
    HttpResponse<Integer> hits(String partNumber)
}