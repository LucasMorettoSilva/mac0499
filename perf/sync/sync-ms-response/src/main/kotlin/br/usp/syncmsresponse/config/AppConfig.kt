package br.usp.syncmsresponse.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AppConfig : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(HttpInterceptor())
        // registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/account/login"); you can add specific end point as well.
    }
}
