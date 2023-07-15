package com.cwallet.cryptowallet.domain.repositories;

import com.cwallet.cryptowallet.domain.dtos.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findAll();
    Optional<Wallet> findByName(String name);
}
