#Distributed Tracing with Micronaut


The demo consists of three services - `storefront`, `inventory`, and `analytics`.

To run the demo, you will need Docker installed.


## Starting Consul

```
$ docker run -d -p 8500:8500 consul
```

## Starting Jaeger

```
$ docker run -d -e COLLECTOR_ZIPKIN_HTTP_PORT=9411 -p 5775:5775/udp -p 6831:6831/udp -p 6832:6832/udp -p 5778:5778 -p 16686:16686 -p 14268:14268 -p 9411:9411 jaegertracing/all-in-one:latest
```

## Start the services

```
$ ./gradlew run -parallel
```


## Make requests

To retrieve a list of products from the `storefront`: GET http://localhost:8080/ 

To retrieve details on a specific product: GET http://localhost:8082/inventory/{productNumber}  //e.g., `001`

## View Tracing Results

Access the Jaeger UI at `http://localhost:16686`