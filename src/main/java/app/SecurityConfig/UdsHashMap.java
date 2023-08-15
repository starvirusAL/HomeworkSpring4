package app.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class UdsHashMap {

    private final Map<String, String> storage = new HashMap<>();

    {
        storage.put("jim", "123");
        storage.put("john", "234");
    }

    private final PasswordEncoder enc;

    public UdsHashMap(PasswordEncoder enc) {
        this.enc = enc;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        Collection<UserDetails> data = storage.entrySet().stream().map(this::mapper)
                .collect(Collectors.toSet());
        return new InMemoryUserDetailsManager(data);
    }

    private UserDetails mapper(Map.Entry<String, String> entry) {
        return User
                .withUsername(entry.getKey())
                .password(entry.getValue())
                .passwordEncoder(enc::encode)
                .roles().build();
    }
}
