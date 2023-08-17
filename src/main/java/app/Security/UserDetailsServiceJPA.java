package app.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceJPA implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return repo.findByUsername(username)
                .map(this::mapper)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("user %s not found", username)
                ));
    }
    private final DbUserRepo repo;

    private UserDetails mapper(DbUser entry) {
        return User
                .withUsername(entry.getUsername())
                .password(entry.getPassword())
                .roles(entry.getRoles())
                .build();
    }
}
