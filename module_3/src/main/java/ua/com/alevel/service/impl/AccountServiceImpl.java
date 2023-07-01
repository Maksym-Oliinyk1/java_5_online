package ua.com.alevel.service.impl;

import au.com.bytecode.opencsv.CSVWriter;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Operation;
import ua.com.alevel.entity.User;
import ua.com.alevel.repository.AccountRepository;
import ua.com.alevel.repository.OperationRepository;
import ua.com.alevel.service.AccountService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final OperationRepository operationRepository;

    public AccountServiceImpl(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public void create(Account account) {
        Long numberOfNewAccount = generateNumberOfAccount();
        account.setNumber(numberOfNewAccount);
        accountRepository.save(account);
    }

    @Override
    public List<Account> findAllByUserId(Long userId) {
        return accountRepository.findAllByUserId(userId);
    }

    @Override
    public Account findByNumber(Long number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public Boolean checkIfAccountExistByNumber(Long number) {
        return accountRepository.existsAccountByNumber(number);
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        List<Account> accounts = findAllByUserId(userId);
        accounts.forEach(account -> operationRepository.deleteAllByAccountToId(account.getId()));
        accounts.forEach(account -> operationRepository.deleteAllByAccountFromId(account.getId()));
    }

    @Override
    public Account findById(Long accountId) {
        return accountRepository.findById(accountId).get();
    }

    @Override
    public void send(Operation operation) {

        Account accountTo = operation.getAccountTo();
        Account accountFrom = operation.getAccountFrom();

        accountFrom.setBalance(accountFrom.getBalance() - operation.getSum());
        accountTo.setBalance(accountTo.getBalance() + operation.getSum());

        operationRepository.save(operation);
        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
    }

    @Override
    public User findUserByAccountId(Long accountId) {
        Account account = findById(accountId);
        return account.getUser();
    }

    @Override
    public void topUp(Long accountId, Long sum) {
        if (sum > 0) {
            Account account = findById(accountId);
            account.setBalance(account.getBalance() + sum);
        }
    }

    @Override
    public void exportInFile(Long accountId) {
        File outgoingOperationsFile = new File("outgoingOperations.csv");
        File incomingOperationsFile = new File("incomingOperations.csv");

        List<Operation> outgoingOperations = operationRepository.findAllByAccountFromId(accountId);
        List<Operation> incomingOperations = operationRepository.findAllByAccountToId(accountId);

        List<String[]> listStrOutgoingOperations = listStringArrOfOutgoingOperations(outgoingOperations);
        List<String[]> listStrIncomingOperations = listStringArrOfIncomingOperations(incomingOperations);

        writeToFile(listStrOutgoingOperations, outgoingOperationsFile);
        writeToFile(listStrIncomingOperations, incomingOperationsFile);
    }

    private Long generateNumberOfAccount() {
        Long number = (long) new Random().nextInt(9000) + 1000;
        if (checkIfAccountExistByNumber(number)) {
            return generateNumberOfAccount();
        }
        return number;
    }

    private List<String[]> listStringArrOfOutgoingOperations(List<Operation> operations) {
        var listStringArr = new ArrayList<String[]>();
        List<Long> outgoingAccountNumbers = new ArrayList<>();
        List<String> usersFullName = new ArrayList<>();
        for (Operation operation : operations) {
            Account account = findById(operation.getAccountTo().getId());
            Long accNumber = account.getNumber();
            User user = findUserByAccountId(account.getId());
            outgoingAccountNumbers.add(accNumber);
            usersFullName.add(user.getFirstName().trim() + " " + user.getLastName().trim());
        }

        for (int i = 0; i < operations.size(); i++) {
            String[] row = new String[]{
                    String.valueOf(operations.get(i).getId()),
                    operations.get(i).getCategory().trim(),
                    String.valueOf(operations.get(i).getSum()),
                    String.valueOf(outgoingAccountNumbers.get(i)),
                    String.valueOf(usersFullName.get(i))
            };
            listStringArr.add(row);
        }
        return listStringArr;
    }

    private List<String[]> listStringArrOfIncomingOperations(List<Operation> operations) {
        var listStringArr = new ArrayList<String[]>();
        List<Long> incomingAccountNumbers = new ArrayList<>();
        List<String> usersFullName = new ArrayList<>();
        for (Operation operation : operations) {
            Account account = findById(operation.getAccountFrom().getId());
            Long accNumber = account.getNumber();
            User user = findUserByAccountId(account.getId());
            incomingAccountNumbers.add(accNumber);
            usersFullName.add(user.getFirstName().trim() + " " + user.getLastName().trim());
        }

        for (int i = 0; i < operations.size(); i++) {
            String[] row = new String[]{
                    String.valueOf(operations.get(i).getId()),
                    operations.get(i).getCategory().trim(),
                    String.valueOf(operations.get(i).getSum()),
                    String.valueOf(incomingAccountNumbers.get(i)),
                    String.valueOf(usersFullName.get(i))
            };
            listStringArr.add(row);
        }
        return listStringArr;
    }

    private void writeToFile(List<String[]> stringsArr, File file) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(file))) {
            csvWriter.writeAll(stringsArr);
            csvWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
