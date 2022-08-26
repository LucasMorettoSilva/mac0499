package br.usp.syncmsresponse.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

@Component
class MessageReader {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun readFile(fileName: String): String {
        log.info("readFile() : {}", fileName)

        try {
            BufferedReader(
                InputStreamReader(FileInputStream(fileName))
            ).use {
                val line = it.readLine()

                log.info("readFile() : successfully read file {}", fileName)

                return line
            }
        } catch (e: Exception) {
            log.error("readFile() : failed to read file {}", fileName)

            throw e
        }
    }
}
