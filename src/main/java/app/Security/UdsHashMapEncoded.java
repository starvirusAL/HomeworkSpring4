package app.Security;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UdsHashMapEncoded {

    public  final PasswordEncoder enc;

    private  final Map<String,String> storage = new HashMap<>();
    {
        storage.put("jim", "1231");
        storage.put("jim1", "124");
    }




    @Bean
    public UserDetailsService createUserDetailsService(){
       Collection<UserDetails> data = storage.entrySet().stream().map(this::mapper).collect(Collectors.toSet());
       return new InMemoryUserDetailsManager(data);

    }

    private UserDetails mapper (Map.Entry<String,String> entry){
        return User
                .withUsername(entry.getKey())
                .password(entry.getValue())
                .passwordEncoder(enc::encode)
                .roles()
                .build();
    }
}
