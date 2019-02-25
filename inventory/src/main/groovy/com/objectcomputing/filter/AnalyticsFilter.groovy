package com.objectcomputing.filter

import com.objectcomputing.client.AnalyticsClient
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpRequest
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.HttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

import javax.inject.Inject

@Slf4j
@Filter("/inventory/**")
class AnalyticsFilter implements HttpServerFilter {

    @Inject
    AnalyticsClient analyticsClient

    @Override
    Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        //log.info "doFilter..."

        return Flowable.fromCallable({
            //TODO: analyticsClient.hit("001")
            true
        }).subscribeOn(Schedulers.io()).switchMap({ aBoolean -> chain.proceed(request)})

    }
}
