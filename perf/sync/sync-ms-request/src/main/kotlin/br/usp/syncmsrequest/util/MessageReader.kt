package br.usp.syncmsrequest.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

@Component
class MessageReader {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Value("\${files.path}")
    lateinit var filesPath: String

    fun loadMessage(msgSize: String): String {
        log.info("loadMessage() : {} MB", msgSize)

        val fileName = filesPath + msgSize + "mb.txt"

        try {
            BufferedReader(
                InputStreamReader(FileInputStream(fileName))
            ).use {
                val line = it.readLine()

                log.info("loadMessage() : successfully loaded message with {} MB", msgSize)

                return line
            }
        } catch (e: Exception) {
            log.error("loadMessage() : failed", e)

            throw e
        }
    }
}
