package hoos.mapper;

import hoos.entity.Account;

import java.util.List;

public interface AccountMapper {
    Account getAccount(String id);
    List<Account> getAccounts();
}
