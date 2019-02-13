package com.objectcomputing.domain

import grails.gorm.annotation.Entity
import org.grails.datastore.gorm.GormEntity

@Entity
class Product implements GormEntity<Product> {

    String name
    String partNumber
    BigDecimal price

    static constraints = {
        name blank: false
        partNumber blank: false
    }
}
