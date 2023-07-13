package com.cwallet.cryptowallet.domain.repositories;

import com.cwallet.cryptowallet.domain.dtos.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoinRepository extends JpaRepository<Coin, Long> {
    Optional<Coin> findByName(String name);
}
