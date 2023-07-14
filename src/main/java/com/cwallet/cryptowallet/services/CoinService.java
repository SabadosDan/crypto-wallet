package com.cwallet.cryptowallet.services;

import com.cwallet.cryptowallet.controllers.responses.CoinResponse;
import com.cwallet.cryptowallet.controllers.responses.ListCoinResponse;
import com.cwallet.cryptowallet.domain.dtos.Coin;
import com.cwallet.cryptowallet.domain.repositories.CoinRepository;
import com.cwallet.cryptowallet.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoinService {
    private final CoinRepository coinRepository;

    @Autowired
    public CoinService(CoinRepository coinRepository){
        this.coinRepository = coinRepository;
    }

    public ListCoinResponse getAllCoins() {
        List<Coin> coinList = this.coinRepository.findAll();
        List<CoinResponse> coinResponseList = new ArrayList<>();
        for (Coin coin : coinList) {
            coinResponseList.add(new CoinResponse(coin.getId(), coin.getName(), coin.getValue()));
        }
        return new ListCoinResponse(coinResponseList);
    }

    public CoinResponse getCoinById(Long id) throws NotFoundException {
        Optional<Coin> optionalCoin = coinRepository.findById(id);
        if(optionalCoin.isEmpty()){
            throw new NotFoundException("Coin not found, try a different id.");
        }
        Coin coin = optionalCoin.get();
        return new CoinResponse(coin.getId(), coin.getName(), coin.getValue());

    }



}
