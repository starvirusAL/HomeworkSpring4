package app.repo;

import app.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Integer> {
    public Account getAccountById(int id);

    public void delete(Account account);

    Optional<Account> findById(Long accountId);
}
