package com.cwallet.cryptowallet.domain.repositories;

import com.cwallet.cryptowallet.domain.dtos.Coin;
import com.cwallet.cryptowallet.domain.dtos.CoinAmount;
import com.cwallet.cryptowallet.domain.dtos.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoinAmountRepository extends JpaRepository<CoinAmount, Long> {
    List<CoinAmount> findAllByWallet(Wallet wallet);

    CoinAmount findByWalletAndCoin(Wallet wallet, Coin coin);
}
