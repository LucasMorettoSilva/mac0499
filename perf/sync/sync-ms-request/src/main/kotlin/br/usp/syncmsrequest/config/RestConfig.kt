package br.usp.syncmsrequest.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.CollectionUtils
import org.springframework.web.client.RestTemplate


@Configuration
class RestConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        val restTemplate = RestTemplate()

        var interceptors = restTemplate.interceptors
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = ArrayList()
        }
        interceptors.add(RestTemplateInterceptor())
        restTemplate.interceptors = interceptors

        return restTemplate
    }
}
