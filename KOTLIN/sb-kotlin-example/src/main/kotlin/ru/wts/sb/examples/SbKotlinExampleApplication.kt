package ru.wts.sb.examples

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SbKotlinExampleApplication

fun main(args: Array<String>) {
	runApplication<SbKotlinExampleApplication>(*args)
}
