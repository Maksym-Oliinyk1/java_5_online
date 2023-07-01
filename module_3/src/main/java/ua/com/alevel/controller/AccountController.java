package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.OperationDto;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Operation;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.UserService;

import java.util.Objects;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    private final UserService userService;

    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping("/byUserId/{userId}")
    public String findAllByUserId(Model model, @PathVariable Long userId) {
        model.addAttribute("user", userService.findUserById(userId));
        model.addAttribute("accounts", accountService.findAllByUserId(userId));
        return "accounts_by_user_id";
    }

    @GetMapping("/createNewUserAccount/{userId}")
    public String createAccountByUser(Model model, @PathVariable Long userId) {
        Account account = new Account();
        User user = userService.findUserById(userId);
        model.addAttribute("account", account);
        model.addAttribute("user", user);
        return "account_new_by_user";
    }

    @PostMapping("/createNewUserAccount/{userId}")
    public String createAccountByUser(@ModelAttribute("account") Account account,
                                      @PathVariable Long userId) {
        account.setUser(userService.findUserById(userId));
        accountService.create(account);
        return "redirect:/accounts/byUserId/{userId}";
    }

    @GetMapping("/send/{accountFromId}")
    public String sendRequest(Model model, @PathVariable Long accountFromId) {
        OperationDto operationDto = new OperationDto();
        model.addAttribute("operationDto", operationDto);
        model.addAttribute("accFromId", accountFromId);
        return "account_send_form";
    }

    @GetMapping("/confirmOperation/{accountFromId}")
    public String confirmOperation(@ModelAttribute("operationDto") OperationDto operationDto, @PathVariable Long accountFromId, Model model) {
        if (accountService.checkIfAccountExistByNumber(operationDto.getAccountToNumber())
                && operationDto.getOperationSum() > 0
                && accountService.findById(operationDto.getAccountFromId()).getBalance() >= operationDto.getOperationSum()
                && !operationDto.getOperationCategory().trim().isEmpty()
                && !Objects.equals(operationDto.getAccountFromId(), accountService.findByNumber(operationDto.getAccountToNumber()).getId())) {
            Account accountToEntity = accountService.findByNumber(operationDto.getAccountToNumber());
            User userTo = accountService.findUserByAccountId(accountToEntity.getId());
            model.addAttribute("accFromId", accountFromId);
            model.addAttribute("userTo", userTo);
            model.addAttribute("operationDto", operationDto);
            return "confirm_operation";
        }
        return "error";
    }

    @PostMapping("/sendFinally/{accountFromId}")
    public String sendFinally(@ModelAttribute("operationDto") OperationDto operationDto, @PathVariable Long accountFromId) {
        User user = accountService.findUserByAccountId(accountFromId);
        Operation operation = new Operation();
        operation.setSum(operationDto.getOperationSum());
        operation.setCategory(operationDto.getOperationCategory());
        operation.setAccountFrom(accountService.findById(accountFromId));
        operation.setAccountTo(accountService.findByNumber(operationDto.getAccountToNumber()));
        accountService.send(operation);
        return "redirect:/accounts/byUserId/" + user.getId();
    }

    @GetMapping("/topUp/{accountId}")
    public String topUpForm(@PathVariable Long accountId, Model model) {
        Account account = accountService.findById(accountId);
        OperationDto sum = new OperationDto();
        model.addAttribute("account", account);
        model.addAttribute("sum", sum);
        return "topUp";
    }

    @GetMapping("/topUpFinally/{accountId}")
    public String topUpFinally(@PathVariable Long accountId, @ModelAttribute("sum") OperationDto sum) {
        User user = accountService.findUserByAccountId(accountId);
        accountService.topUp(accountId, sum.getOperationSum());
        return "redirect:/accounts/byUserId/" + user.getId();
    }

    @GetMapping("/exportOperations/{accountId}")
    public String exportInFile(@PathVariable Long accountId) {
        User user = accountService.findUserByAccountId(accountId);
        accountService.exportInFile(accountId);
        return "redirect:/accounts/byUserId/" + user.getId();
    }
}
