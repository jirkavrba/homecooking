package dev.vrba.homecooking.server.security

import dev.vrba.homecooking.server.model.User
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils

class UserTokenAuthentication(private val user: User) : Authentication {
    override fun getAuthorities(): Collection<GrantedAuthority> =
        AuthorityUtils.createAuthorityList(SecurityConfiguration.USER_ROLE)

    override fun getPrincipal(): User = user
    override fun getName(): String = user.username
    override fun getCredentials(): String? = user.token


    override fun getDetails() = null
    override fun isAuthenticated(): Boolean = true
    override fun setAuthenticated(isAuthenticated: Boolean) {
        // Intentionally left blank
    }
}