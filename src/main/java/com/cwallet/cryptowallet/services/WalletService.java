package com.cwallet.cryptowallet.services;

import com.cwallet.cryptowallet.controllers.responses.ListWalletResponse;
import com.cwallet.cryptowallet.controllers.responses.WalletResponse;
import com.cwallet.cryptowallet.domain.dtos.Wallet;
import com.cwallet.cryptowallet.domain.repositories.CoinAmountRepository;
import com.cwallet.cryptowallet.domain.repositories.CoinRepository;
import com.cwallet.cryptowallet.domain.repositories.WalletRepository;
import com.cwallet.cryptowallet.exceptions.DuplicateEntityException;
import com.cwallet.cryptowallet.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final CoinRepository coinRepository;
    private final CoinAmountRepository coinAmountRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository,
                         CoinRepository coinRepository,
                         CoinAmountRepository coinAmountRepository){
        this.walletRepository = walletRepository;
        this.coinRepository = coinRepository;
        this.coinAmountRepository = coinAmountRepository;
    }

    /** return a list with all wallets */
    public ListWalletResponse getAllWallets(){
        List<Wallet> wallets = walletRepository.findAll();
        List<WalletResponse> walletResponseList = new ArrayList<>();
        for (Wallet wallet : wallets){
            walletResponseList.add(new WalletResponse(wallet.getId(), wallet.getName()));
        }
        return new ListWalletResponse(walletResponseList);
    }

    /** return wallet by id */
    public WalletResponse getWalletById(Long id) throws NotFoundException{
        Optional<Wallet> optionalWallet = walletRepository.findById(id);
        if(optionalWallet.isEmpty()){
            throw new NotFoundException("Wallet not found, try a different id");
        }
        Wallet wallet = optionalWallet.get();
        return new WalletResponse(wallet.getId(), wallet.getName());
    }

    /** add new wallet to the repository */
    public WalletResponse addNewWallet(WalletResponse walletResponse) throws DuplicateEntityException{
        Optional<Wallet> optionalWallet = walletRepository.findByName(walletResponse.getName());
        // check if there is already a wallet with the same name
        if (optionalWallet.isPresent()){
            throw new DuplicateEntityException("Duplicate wallet with name " + walletResponse.getName());
        }
        // create Wallet with WalletResponse data
        Wallet wallet = new Wallet(walletResponse.getName(), new ArrayList<>());
        // save the Wallet entity in repository
        walletRepository.save(wallet);
        return new WalletResponse(wallet.getId(), wallet.getName());
    }

}
