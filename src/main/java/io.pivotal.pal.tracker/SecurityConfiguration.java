package io.pivotal.pal.tracker;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String forceHttps = System.getenv("SECURITY_FORCE_HTTPS");
        http.authorizeRequests().antMatchers("/**").hasRole("USER")
                .and()
                .httpBasic().and()
                .csrf().disable();

        if (forceHttps != null && forceHttps.equals("true")) {
            http.requiresChannel().anyRequest().requiresSecure();
        }
    }
}
