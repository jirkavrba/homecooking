package dev.vrba.homecooking.server.security

import dev.vrba.homecooking.server.configuration.BotUserConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(BotUserConfiguration::class)
class SecurityConfiguration {

    companion object {
        const val BOT_ROLE = "BOT_ROLE"
        const val USER_ROLE = "USER_ROLE"
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .logout { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.realmName("Homecooking") }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it.requestMatchers("/api/v1/bot/**").hasAuthority(BOT_ROLE)
                it.requestMatchers("/api/v1/user/**").hasAuthority(USER_ROLE)
                it.anyRequest().permitAll()
            }
            .build()
    }

    @Bean
    fun userDetailsService(botUserConfiguration: BotUserConfiguration): UserDetailsService {
        val password = passwordEncoder().encode(botUserConfiguration.password)
        val authorities = AuthorityUtils.createAuthorityList(BOT_ROLE)

        val botUser = User(botUserConfiguration.username, password, authorities)
        val users = listOf(botUser)

        return InMemoryUserDetailsManager(users)
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}