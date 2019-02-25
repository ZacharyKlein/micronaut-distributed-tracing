package com.objectcomputing.domain

import grails.gorm.annotation.Entity
import org.grails.datastore.gorm.GormEntity

@Entity
class Product implements GormEntity<Product> {

    String name
    String partNumberr
    BigDecimal price

    static constraints = {
        name blank: false
        partNumberr blank: false
    }
}
