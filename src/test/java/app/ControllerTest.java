package app;


import app.Service.AccountService;
import app.Service.CustomerService;
import app.controllers.AccountController;
import app.controllers.CustomerController;
import app.dto.CustomerMapper;
import app.enums.Currency;
import app.models.Account;
import app.models.Customer;
import app.models.InputItems;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@Log4j2
public class ControllerTest {
    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerMapper dtoCustomerMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testDelete() {
        Long accountId = 1L;
        Account account = accountService.getAccountById(1);
        when(accountService.getAccountById(Math.toIntExact(accountId))).thenReturn(new Account(Currency.EUR));

        String response = accountController.delete(account);

        assertEquals("Account deleted", response);

    }



    @Test
    public void testMoneyPut() {
        InputItems form = new InputItems();
        form.setIdAccount(1);
        form.setValueM(50.0);

        Account account = new Account(Currency.EUR);
        when(accountService.getAccountById((int) anyLong())).thenReturn(account);

        String result = accountController.moneyPut(form, new MockHttpServletRequest());

        assertEquals("redirect:navigation", result);
//        verify(accountService, times(1)).refillAccount(account, 50.0);
    }

    @Test
    public void testMoneyGet() {
        InputItems form = new InputItems();
        form.setIdAccount(1);
        form.setValueM(30.0);

        Account account = new Account(Currency.EUR);
        when(accountService.getAccountById((int) anyLong())).thenReturn(account);

        String result = accountController.moneyGet(form, new MockHttpServletRequest());

        assertEquals("redirect:navigation", result);
//        verify(accountService, times(1)).withdrawAccount(account, 30.0);
    }

    @Test
    public void testMoneyTransfer() {
        InputItems form = new InputItems();
        form.setIdAccount(1);
        form.setIdAccount2(2);
        form.setValueM(50.0);

        Account sourceAccount = new Account(Currency.EUR);
        Account targetAccount = new Account(Currency.EUR);

        when(accountService.getAccountById(1)).thenReturn(sourceAccount);
        when(accountService.getAccountById(2)).thenReturn(targetAccount);

        String result = accountController.transferMoney(form, new MockHttpServletRequest());

        assertEquals("redirect:navigation", result);

    }


    @Test
    public void testCustomCreate() {
        InputItems form = new InputItems();
        form.setIdEmployer(1);
        form.setName("John Doe");
        form.setAge(28);
        form.setEmail("john@example.com");
        form.setPhoneNumber("1234567890");
        form.setPassword("password");
        when(passwordEncoder.encode(form.getPassword())).thenReturn("hashedPassword");

        assertEquals("John Doe", form.getName());

    }

    @Test
    public void removeCustomerTest() {
        Customer customer = customerService.getCustomerById(1);
        when(customerService.getCustomerById(Math.toIntExact(1))).thenReturn(new Customer());
        String response = customerController.delete(1);
        assertEquals("Customer deleted", response);

    }
}