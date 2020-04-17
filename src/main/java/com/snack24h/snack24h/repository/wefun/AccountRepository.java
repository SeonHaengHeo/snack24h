package com.snack24h.snack24h.repository.wefun;

import com.snack24h.snack24h.model.domain.wefun.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
