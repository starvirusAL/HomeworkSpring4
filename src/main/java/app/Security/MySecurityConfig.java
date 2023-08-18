package app.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity

public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder enc;
    private final DbUserRepo repo;

    public MySecurityConfig(PasswordEncoder enc, DbUserRepo repo) {
        this.enc = enc;
        this.repo = repo;
       /* repo.saveAll(List.of(
              // new DbUser("admin", enc.encode("admin"), "ADMIN","USER")

        ));*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/navigation/**").hasRole("ADMIN")
                .antMatchers("/accountCreate/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .defaultSuccessUrl("/navigation", true)
                .failureUrl("/login.html?error=true")
                .permitAll()
                .and()
                .logout()
                .deleteCookies("JSESSIONID");
    }


}
