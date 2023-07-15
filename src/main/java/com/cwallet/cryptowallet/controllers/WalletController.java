package com.cwallet.cryptowallet.controllers;

import com.cwallet.cryptowallet.controllers.responses.BaseResponse;
import com.cwallet.cryptowallet.controllers.responses.ErrorResponse;
import com.cwallet.cryptowallet.controllers.responses.WalletResponse;
import com.cwallet.cryptowallet.exceptions.DuplicateEntityException;
import com.cwallet.cryptowallet.exceptions.NotFoundException;
import com.cwallet.cryptowallet.services.WalletService;
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
}
