package br.usp.asyncmsconnection

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AsyncMsConnectionApplication

fun main(args: Array<String>) {
	runApplication<AsyncMsConnectionApplication>(*args)
}
