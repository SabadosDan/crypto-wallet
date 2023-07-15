package com.cwallet.cryptowallet.controllers;

import com.cwallet.cryptowallet.controllers.requests.BuyCoinRequest;
import com.cwallet.cryptowallet.controllers.requests.ExchangeCoinsRequest;
import com.cwallet.cryptowallet.controllers.responses.BaseResponse;
import com.cwallet.cryptowallet.controllers.responses.ErrorResponse;
import com.cwallet.cryptowallet.controllers.responses.WalletResponse;
import com.cwallet.cryptowallet.exceptions.DuplicateEntityException;
import com.cwallet.cryptowallet.exceptions.NotEnoughFundsException;
import com.cwallet.cryptowallet.exceptions.NotFoundException;
import com.cwallet.cryptowallet.services.WalletService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @GetMapping(value = "")
    public ResponseEntity<BaseResponse> getAllWallets(){
        return new ResponseEntity<>(walletService.getAllWallets(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BaseResponse> getWalletById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(walletService.getWalletById(id), HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<BaseResponse> addWallet(@RequestBody WalletResponse walletResponse){
        try {
            return new ResponseEntity<>(walletService.addNewWallet(walletResponse), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/show_coins/{id}")
    public ResponseEntity<BaseResponse> getAllCoinsFromWalletById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(walletService.getAllCoinsFromWalletById(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "values/{id}")
    public ResponseEntity<BaseResponse> totalValueOfCoinsFromWallet(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(walletService.totalValueOfCoinsFromWallet(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/buy")
    public ResponseEntity<BaseResponse> buyCoin(@RequestBody BuyCoinRequest buyCoinRequest){
        try{
            return new ResponseEntity<>(walletService.buyCoin(buyCoinRequest), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/exchange")
    public ResponseEntity<BaseResponse> exchangeCoins(@RequestBody ExchangeCoinsRequest exchangeCoinsRequest){
        try{
            return new ResponseEntity<>(walletService.exchangeCoins(exchangeCoinsRequest), HttpStatus.OK);
        } catch (NotFoundException | NotEnoughFundsException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
