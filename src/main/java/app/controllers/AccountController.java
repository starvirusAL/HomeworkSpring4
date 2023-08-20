package app.controllers;

import app.Service.AccountService;
import app.dto.CustomerRequest;
import app.models.Account;
import app.models.InputItems;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("accountCreate")
    public String accountCreate() {
        return "accountCreate";
    }

    @PostMapping("accountCreate")
    public String accountCreate(InputItems form, HttpServletRequest rq,@Valid CustomerRequest customerRequest, BindingResult result) {
        Map<String, String[]> allParams = rq.getParameterMap();
        Account a = new Account(form.getInputCurrency());
        accountService.create(a);
        return "redirect:navigation";
    }


    @GetMapping("putMoney")

    public String moneyPut() {
        return "putMoney";
    }
    @PostMapping("putMoney")
    public String moneyPut(InputItems form, HttpServletRequest rq) {
        Map<String, String[]> allParams = rq.getParameterMap();
        Account account = accountService.getAccountById(form.getIdAccount());
        accountService.replenishTheBalance(account, form.getValueM());
        accountService.create(account);
        return "redirect:navigation";
    }
    @GetMapping("withdrawMoney")
    public String withdrawMoney() {
        return "withdrawMoney";
    }
    @PostMapping("withdrawMoney")
    public String withdrawMoney(InputItems form, HttpServletRequest rq) {
        Map<String, String[]> allParams = rq.getParameterMap();
        Account account = accountService.getAccountById(form.getIdAccount());
        accountService.withdrawBalance(account, form.getValueM());
        accountService.create(account);
        return "redirect:navigation";
    }

    @GetMapping("transferMoney")
    public String transferMoney() {
        return "transferMoney";
    }
    @PostMapping("transferMoney")
    public String transferMoney(InputItems form, HttpServletRequest rq) {
        Map<String, String[]> allParams = rq.getParameterMap();
        Account account1 = accountService.getAccountById(form.getIdAccount());
        Account account2 = accountService.getAccountById(form.getIdAccount2());
        accountService.transferMoney(account1, account2, form.getValueM());
        accountService.create(account1);
        accountService.create(account2);
        return "redirect:navigation";
    }
    @GetMapping("removeAccount")
    public String removeAccount() {
        return "removeAccount";
    }
    @PostMapping("removeAccount")
    public String removeAccount(InputItems form, HttpServletRequest rq) {
        Map<String, String[]> allParams = rq.getParameterMap();
        Account account = accountService.getAccountById(form.getIdAccount());
        accountService.delete(account);
        return "redirect:navigation";
    }
    @GetMapping("balance")
    public String balance() {
        return "balance";
    }

    @PostMapping("balance")
    @ResponseBody
    public String balance(InputItems form, HttpServletRequest rq) {
        Map<String, String[]> allParams = rq.getParameterMap();
        return "Money on your balance:" + accountService.getAccountById(form.getIdAccount()).getBalance().toString();
    }
    public String delete(Account account) {
        accountService.delete(account);
        return "Account deleted";
    }
    public String moneyGet(InputItems form, HttpServletRequest rq) {
        accountService.withdrawBalance(accountService.getAccountById(form.getIdAccount()), form.getValueM());
        return "redirect:navigation";
    }
}
