package kr.co.vacgom.core.global.security.config

import kr.co.vacgom.core.global.security.constants.CorsAllowedHeaders
import kr.co.vacgom.core.global.security.constants.CorsAllowedOrigins
import kr.co.vacgom.core.global.security.handler.ForbiddenHandler
import kr.co.vacgom.core.global.security.handler.UnauthorizedHandler
import kr.co.vacgom.core.global.security.jwt.JwtTokenFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenFactory: JwtTokenFactory,
    private val forbiddenHandler: ForbiddenHandler,
    private val unauthorizedHandler: UnauthorizedHandler
) {

    @Bean
    fun authFilterChain(
        httpSecurity: HttpSecurity
    ): SecurityFilterChain =
        httpSecurity
            .also(::disableDefaultSecurityConfiguration)
            .also(::enableCoreSecurityConfiguration)
            .also(::applyHttpRequestMatchers)
            .build()

    private fun disableDefaultSecurityConfiguration(
        httpSecurity: HttpSecurity
    ): HttpSecurity =
        httpSecurity
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }
            .httpBasic { it.disable() }

    private fun enableCoreSecurityConfiguration(
        httpSecurity: HttpSecurity
    ): HttpSecurity =
        httpSecurity
            .cors { corsConfiguration() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling {
                it.accessDeniedHandler(forbiddenHandler)
                it.authenticationEntryPoint(unauthorizedHandler)
            }

    private fun applyHttpRequestMatchers(
        httpSecurity: HttpSecurity
    ) {
        httpSecurity.authorizeHttpRequests {
            it.requestMatchers(HttpMethod.GET, "/").anonymous()
            it.anyRequest().denyAll()
        }
    }

    private fun corsConfiguration(): CorsConfigurationSource {
        val cors = CorsConfiguration()
        cors.allowedOrigins = CorsAllowedOrigins.entries.map(CorsAllowedOrigins::value)
        cors.allowedHeaders = CorsAllowedHeaders.entries.map(CorsAllowedHeaders::value)
        cors.allowedMethods = listOf(CorsConfiguration.ALL)
        cors.allowCredentials = true

        val urlBasedCorsConfigurationSource = UrlBasedCorsConfigurationSource()
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", cors)

        return urlBasedCorsConfigurationSource
    }
}