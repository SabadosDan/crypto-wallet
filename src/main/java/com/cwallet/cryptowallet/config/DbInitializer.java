package com.cwallet.cryptowallet.config;

import com.cwallet.cryptowallet.domain.dtos.Coin;
import com.cwallet.cryptowallet.domain.dtos.CoinAmount;
import com.cwallet.cryptowallet.domain.dtos.Wallet;
import com.cwallet.cryptowallet.domain.repositories.CoinRepository;
import com.cwallet.cryptowallet.domain.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbInitializer {
    private final CoinRepository coinRepository;
    private final WalletRepository walletRepository;

    @Autowired
    public DbInitializer(CoinRepository coinRepository, WalletRepository walletRepository){
        this.coinRepository = coinRepository;
        this.walletRepository = walletRepository;
    }

    @PostConstruct
    public void initializer() {
        Wallet wallet = new Wallet("Wallet Andrei");
        walletRepository.save(wallet);
        Coin coinBTC = new Coin("Bitcoin", 30361.30);
        coinRepository.save(coinBTC);
        Coin coinETH = new Coin("Ethereum", 1943.52);
        coinRepository.save(coinETH);
        Coin coinBNB = new Coin("Binance Coin", 252.48);
        coinRepository.save(coinBNB);

        CoinAmount coinAmountBTC = new CoinAmount(wallet, coinBTC, 3d);
        CoinAmount coinAmountETH = new CoinAmount(wallet, coinETH, 11d);
        CoinAmount coinAmountBNB = new CoinAmount(wallet, coinBNB, 40.5d);

        wallet.getCoinAmounts().add(coinAmountBTC);
        wallet.getCoinAmounts().add(coinAmountETH);
        wallet.getCoinAmounts().add(coinAmountBNB);

        walletRepository.save(wallet);
    }
}
