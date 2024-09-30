package kr.co.vacgom.core.global.security.config

import kr.co.vacgom.core.global.security.jwt.JwtTokenFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenFactory: JwtTokenFactory
) {
    @Bean
    fun authFilterChain(
        httpSecurity: HttpSecurity
    ): SecurityFilterChain =
        httpSecurity
            .apply(::disableSecurityConfig)
            .authorizeHttpRequests { auth -> auth.anyRequest().permitAll() }
            .build()


    private fun disableSecurityConfig(
        httpSecurity: HttpSecurity
    ): HttpSecurity =
        httpSecurity
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }
            .httpBasic { it.disable() }
}