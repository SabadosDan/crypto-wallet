package com.cwallet.cryptowallet.domain.repositories;

import com.cwallet.cryptowallet.domain.dtos.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long > {
}
