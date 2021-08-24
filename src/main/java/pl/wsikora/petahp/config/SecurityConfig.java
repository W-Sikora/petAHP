package pl.wsikora.petahp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select email, password, 1 as enabled from users where email = ?")
                .authoritiesByUsernameQuery("select email, 'ROLE_USER' from users where email = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/rejestracja", "/webapp/static/**", "/przekierowanie").permitAll()
                .antMatchers("/panel", "/panel/**", "/ankieta", "/ankieta/**").authenticated()
                .and()
                    .formLogin()
                    .loginPage("/logowanie")
                    .loginProcessingUrl("/logowanie")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/panel", true)
                    .failureUrl("/logowanie?error")
                .and()
                    .logout()
                    .logoutUrl("/wyloguj")
                    .logoutSuccessUrl("/")
                .and()
                    .csrf()
                    .disable();
    }

}
