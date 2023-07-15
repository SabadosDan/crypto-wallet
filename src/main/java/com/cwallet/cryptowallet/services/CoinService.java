package com.cwallet.cryptowallet.services;

import com.cwallet.cryptowallet.controllers.responses.CoinResponse;
import com.cwallet.cryptowallet.controllers.responses.ListCoinResponse;
import com.cwallet.cryptowallet.domain.dtos.Coin;
import com.cwallet.cryptowallet.domain.repositories.CoinRepository;
import com.cwallet.cryptowallet.exceptions.DuplicateEntityException;
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

    /** return a list with all coins */
    public ListCoinResponse getAllCoins() {
        List<Coin> coinList = this.coinRepository.findAll();
        List<CoinResponse> coinResponseList = new ArrayList<>();
        for (Coin coin : coinList) {
            coinResponseList.add(new CoinResponse(coin.getId(), coin.getName(), coin.getValue()));
        }
        return new ListCoinResponse(coinResponseList);
    }

    /** return coin by id */
    public CoinResponse getCoinById(Long id) throws NotFoundException {
        Optional<Coin> optionalCoin = coinRepository.findById(id);
        if(optionalCoin.isEmpty()){
            throw new NotFoundException("Coin not found, try a different id.");
        }
        Coin coin = optionalCoin.get();
        return new CoinResponse(coin.getId(), coin.getName(), coin.getValue());

    }

    /** add new coin to the repository */
    public CoinResponse addNewCoin(CoinResponse coinResponse) throws DuplicateEntityException {
        Optional<Coin> optionalCoin = coinRepository.findByName(coinResponse.getName());
        // check if there is already a coin with the same name
        if (optionalCoin.isPresent()) {
            throw new DuplicateEntityException("Duplicate coin with name " + coinResponse.getName());
        }
        // create a Coin with CoinResponse data
        Coin coin = new Coin(coinResponse.getName(), coinResponse.getValue());
        // save the Coin entity in repository
        coinRepository.save(coin);
        return new CoinResponse(coin.getId(), coin.getName(), coin.getValue());
    }
}
