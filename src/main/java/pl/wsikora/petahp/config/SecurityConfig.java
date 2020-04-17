//package pl.wsikora.petahp.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.sql.DataSource;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final DataSource dataSource;
//    private final PasswordEncoder passwordEncoder;
//
//    public SecurityConfig(DataSource dataSource, PasswordEncoder passwordEncoder) {
//        this.dataSource = dataSource;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder)
//                .usersByUsernameQuery("select * from users where email = ?");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/**").permitAll();
//                .csrf()
//                .disable();
//                .authorizeRequests().antMatchers("/**").permitAll();
//                .authorizeRequests()
//                .antMatchers("/webapp/static/**", "/", "rejestracja", "rejestrowanie",
//                        "logowanie", "zalogowanie")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and().formLogin().loginPage("/logowanie")
//                .loginProcessingUrl("/zalogowanie")
//                .defaultSuccessUrl("/panel", true)
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
//                .and().csrf();
//    }
//
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        return super.userDetailsService();
//    }
//
//}
