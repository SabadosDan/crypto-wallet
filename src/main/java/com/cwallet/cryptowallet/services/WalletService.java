package com.cwallet.cryptowallet.services;

import com.cwallet.cryptowallet.controllers.requests.BuyCoinRequest;
import com.cwallet.cryptowallet.controllers.responses.*;
import com.cwallet.cryptowallet.domain.dtos.Coin;
import com.cwallet.cryptowallet.domain.dtos.CoinAmount;
import com.cwallet.cryptowallet.domain.dtos.Transaction;
import com.cwallet.cryptowallet.domain.dtos.Wallet;
import com.cwallet.cryptowallet.domain.repositories.CoinAmountRepository;
import com.cwallet.cryptowallet.domain.repositories.CoinRepository;
import com.cwallet.cryptowallet.domain.repositories.TransactionRepository;
import com.cwallet.cryptowallet.domain.repositories.WalletRepository;
import com.cwallet.cryptowallet.exceptions.DuplicateEntityException;
import com.cwallet.cryptowallet.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final CoinRepository coinRepository;
    private final CoinAmountRepository coinAmountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository,
                         CoinRepository coinRepository,
                         CoinAmountRepository coinAmountRepository,
                         TransactionRepository transactionRepository){
        this.walletRepository = walletRepository;
        this.coinRepository = coinRepository;
        this.coinAmountRepository = coinAmountRepository;
        this.transactionRepository = transactionRepository;
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

    /** return all coins from a wallet, showing the coin details and the amount  */
    public AllCoinsFromWalletResponse getAllCoinsFromWalletById(Long id) throws NotFoundException{
        Optional<Wallet> optionalWallet = walletRepository.findById(id);
        if(optionalWallet.isEmpty()){
            throw new NotFoundException("Wallet not found, try a different id");
        }
        List<CoinAmount> coinAmounts = coinAmountRepository.findAllByWallet(optionalWallet.get());
        List<CoinAmountResponse> coinAmountResponses = new ArrayList<>();
        for (CoinAmount coinAmount : coinAmounts){
            coinAmountResponses.add(new CoinAmountResponse(coinAmount.getCoin(), coinAmount.getAmount()));
        }
        return new AllCoinsFromWalletResponse(coinAmountResponses);
    }

    /** return total values for each coin and total value of the wallet*/
    public WalletValueResponse totalValueOfCoinsFromWallet(Long id) throws NotFoundException{
        Optional<Wallet> optionalWallet = walletRepository.findById(id);
        if(optionalWallet.isEmpty()){
            throw new NotFoundException("Wallet not found, try a different id");
        }

        Wallet wallet =optionalWallet.get();
        List<CoinAmount> coinAmounts = coinAmountRepository.findAllByWallet(wallet);
        List<TotalValueForACoinResponse> totalValuesForEachCoin = new ArrayList<>();
        Double totalWalletValue = 0d;
        for (CoinAmount coinAmount : coinAmounts) {
            String nameCoin = coinAmount.getCoin().getName();
            Double totalValue = coinAmount.getAmount() * coinAmount.getCoin().getValue();
            totalValuesForEachCoin.add(new TotalValueForACoinResponse(nameCoin, totalValue));
            totalWalletValue += totalValue;
        }

        return new WalletValueResponse(totalValuesForEachCoin, totalWalletValue);
    }

    /** save a new CoinAmount in a wallet, create a new Transactions
     * and return a BuyCoinResponse type data
     * param: BuyCoinRequest */
    public BuyCoinResponse buyCoin(BuyCoinRequest buyCoinRequest) throws NotFoundException{
        Optional<Wallet> optionalWallet = walletRepository.findById(buyCoinRequest.getWalletId());
        Optional<Coin> optionalCoin = coinRepository.findById(buyCoinRequest.getCoinId());
        if(optionalWallet.isEmpty()){
            throw new NotFoundException("Wallet not found!");
        }
        if(optionalCoin.isEmpty()){
            throw new NotFoundException("Coin not found!");
        }

        Coin coin = optionalCoin.get();
        Wallet wallet = optionalWallet.get();
        // total value of purchase
        Double totalValue = coin.getValue() * buyCoinRequest.getAmount();
        // create the date data for Transaction
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        // create and save the transaction to the repository
        Transaction transaction = new Transaction(formattedDate, coin, buyCoinRequest.getAmount(),totalValue);
        transactionRepository.save(transaction);
        // get or create coin amount for the specific coin and wallet and update it
        CoinAmount coinAmount = getOrCreateCoinAmount(coin, wallet);
        coinAmount.setAmount(coinAmount.getAmount() + buyCoinRequest.getAmount());
        coinAmountRepository.save(coinAmount);

        String status = "success";
        String message = "You bought " + buyCoinRequest.getAmount() + coin.getName();

        CoinAmountResponse coinAmountResponse = new CoinAmountResponse(coin, coinAmount.getAmount());
        return new BuyCoinResponse(coinAmountResponse, status, message);
    }

    /** returns an existing or a new coin amount
     * params: Coin, Wallet */
    public CoinAmount getOrCreateCoinAmount(Coin coin, Wallet wallet){
        CoinAmount coinAmount = coinAmountRepository.findByWalletAndCoin(wallet, coin);
        if (coinAmount == null){
            // create new coin amount if it doesn't exist already
            coinAmount = new CoinAmount(wallet, coin, 0d);
        }
        return coinAmount;
    }
}
