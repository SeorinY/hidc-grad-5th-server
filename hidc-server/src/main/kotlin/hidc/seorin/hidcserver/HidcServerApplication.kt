package hidc.seorin.hidcserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HidcServerApplication

fun main(args: Array<String>) {
    runApplication<HidcServerApplication>(*args)
}
