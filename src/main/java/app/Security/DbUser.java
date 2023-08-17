package app.Security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class DbUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;

    private String username;
    private String password;

    private String roles;

    private  final  String  DELIMITER = ":";

    public DbUser(String username, String password, String... roles){
        this.username = username;
        this.password = password;
        setRoles(roles);
    }
    public void  setRoles(String[] roles){
        this.roles = String.join(DELIMITER, roles);
    }

    public String[] getRoles() {
        return this.roles.split(DELIMITER);
    }
}
