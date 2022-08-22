package br.usp.asyncmschat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SyncMsChatApplication

fun main(args: Array<String>) {
	runApplication<SyncMsChatApplication>(*args)
}
