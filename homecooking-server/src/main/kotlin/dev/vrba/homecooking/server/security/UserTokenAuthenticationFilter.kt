package dev.vrba.homecooking.server.security

import dev.vrba.homecooking.server.service.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class UserTokenAuthenticationFilter(
    private val userService: UserService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val user = request.getHeader(HttpHeaders.AUTHORIZATION)
            ?.removePrefix("Bearer ")
            ?.let { userService.findByToken(it) }
            ?: return filterChain.doFilter(request, response)


        val context = SecurityContextHolder.getContext()
        val authentication = UserTokenAuthentication(user)

        context.authentication = authentication

        filterChain.doFilter(request, response)
    }
}