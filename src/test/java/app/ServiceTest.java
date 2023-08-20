package app;

import app.Service.AccountService;
import app.dto.AccountRequest;
import app.dto.AccountResponseDto;
import app.enums.Currency;
import app.models.Account;
import app.repo.AccountRepo;
import app.repo.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ServiceTest {

    @Mock
    private AccountRepo accountRepo;

    @Mock
    private CustomerRepo customerRepo;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(Currency.EUR));
        accounts.add(new Account(Currency.UAH));

        assertEquals(2, accounts.size());
        assertEquals(Currency.EUR, accounts.get(0).getCurrency());
        assertEquals(Currency.UAH, accounts.get(1).getCurrency());
    }


    @Test
    public void testShow() {
        Long accountId = 1L;
        Account account = new Account(Currency.EUR);
        when(accountRepo.findById(accountId)).thenReturn(Optional.of(account));

        List<AccountResponseDto> responses = accountService.show(accountId);

        assertEquals(1, responses.size());
        assertEquals(Currency.EUR, responses.get(0).getCurrency());

        verify(accountRepo, times(1)).findById(accountId);
    }

    @Test
    public void testDeleteAll() {
        accountService.deleteAll();

        verify(accountRepo, times(1)).deleteAll();
    }

}
