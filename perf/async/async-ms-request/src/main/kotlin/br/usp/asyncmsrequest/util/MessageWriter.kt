package br.usp.asyncmsrequest.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.OutputStreamWriter

@Component
class MessageWriter {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @Value("\${files.path}")
    lateinit var filesPath: String

    fun writeMessage(msgSize: Double) {
        log.info("writeMessage() : {} MB", msgSize)

        val fileName = filesPath + msgSize + "mb.txt"

        try {
            val msg = StringGenerator.withSizeInMegaBytes(msgSize)

            BufferedWriter(
                OutputStreamWriter(FileOutputStream(fileName))
            ).use {
                it.write(msg)
            }

            log.info("writeMessage() : successfully wrote message with {} MB", msgSize)
        } catch (e: Exception) {
            log.error("writeMessage() : failed", e)

            throw e
        }
    }
}
