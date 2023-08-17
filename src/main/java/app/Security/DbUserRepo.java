package app.Security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DbUserRepo  extends JpaRepository<DbUser, Integer> {


    Optional<DbUser> findByUsername(String username);
}
