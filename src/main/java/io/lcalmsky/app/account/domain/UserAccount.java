package io.lcalmsky.app.account.domain;

import io.lcalmsky.app.account.domain.entity.Account;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserAccount extends User {
    @Getter
    private final Account account;

    public UserAccount(Account account) {
        super(account.getName(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.account = account;
    }
}
