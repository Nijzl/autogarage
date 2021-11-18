package nl.lnijzink.autogarage.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("mechanic1").password("{noop}peer").roles("MECHANIC", "EMPLOYEE")
                .and()
                .withUser("mechanic2").password("{noop}peer").roles("MECHANIC", "EMPLOYEE")
                .and()
                .withUser("backoffice1").password("{noop}peer").roles("BACKOFFICE", "EMPLOYEE")
                .and()
                .withUser("cashier1").password("{noop}peer").roles("CASHIER", "EMPLOYEE")
                .and()
                .withUser("administrator1").password("{noop}peer").roles("ADMINISTRATOR", "EMPLOYEE")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .defaultSuccessUrl("/home")
                .and()
                .authorizeRequests()
                .antMatchers("/home").hasRole("EMPLOYEE")
                .antMatchers("/employees/**").hasRole("EMPLOYEE")
                .antMatchers("/files/**").hasRole("EMPLOYEE")
                .antMatchers("/customers/**").hasAnyRole("MECHANIC", "ADMINISTRATOR")
                .antMatchers("/cars/**").hasAnyRole("MECHANIC", "ADMINISTRATOR")
                .antMatchers("/workUnit/**").hasAnyRole("MECHANIC")
                .antMatchers("/workUnitParts/**").hasAnyRole("MECHANIC")
                .antMatchers("/parts/**").hasAnyRole("MECHANIC", "BACKOFFICE")
                .antMatchers("/actions/**").hasAnyRole("MECHANIC", "BACKOFFICE")
                .antMatchers("/invoices/**").hasAnyRole("MECHANIC", "ADMINISTRATOR")
                .antMatchers("/appointments/**").hasAnyRole("MECHANIC", "ADMINISTRATOR")
                .antMatchers("/stock/**").hasAnyRole("MECHANIC", "ADMINISTRATOR", "BACKOFFICE")
                .antMatchers("/").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

}
