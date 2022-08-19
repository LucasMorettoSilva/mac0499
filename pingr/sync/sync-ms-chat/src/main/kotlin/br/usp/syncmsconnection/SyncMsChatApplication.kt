package br.usp.syncmsconnection

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SyncMsChatApplication

fun main(args: Array<String>) {
	runApplication<SyncMsChatApplication>(*args)
}
