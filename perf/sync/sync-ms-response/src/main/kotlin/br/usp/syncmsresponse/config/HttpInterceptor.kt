package br.usp.syncmsresponse.config

import br.usp.syncmsresponse.enums.CustomHeaders
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class HttpInterceptor: HandlerInterceptor {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any): Boolean {
        log.info("preHandle() : new request received")

        val receiveTime = System.currentTimeMillis()

        response.setHeader(
            CustomHeaders.REQ_RECEIVE_TIME.value,
            receiveTime.toString()
        )

        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        log.info("postHandle() : new response sent")

        val sendTime = System.currentTimeMillis()

        response.setHeader(
            CustomHeaders.RES_SEND_TIME.value,
            sendTime.toString()
        )
    }
}
