package com.cwallet.cryptowallet.controllers;

import com.cwallet.cryptowallet.controllers.responses.BaseResponse;
import com.cwallet.cryptowallet.controllers.responses.CoinResponse;
import com.cwallet.cryptowallet.controllers.responses.ErrorResponse;
import com.cwallet.cryptowallet.exceptions.DuplicateEntityException;
import com.cwallet.cryptowallet.exceptions.NotFoundException;
import com.cwallet.cryptowallet.services.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coins")
public class CoinController {
    private final CoinService coinService;

    @Autowired
    public CoinController(CoinService coinService){
        this.coinService = coinService;
    }

    @GetMapping(value = "")
    public ResponseEntity<BaseResponse> getAllCoins(){
        return new ResponseEntity<>(coinService.getAllCoins(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BaseResponse> getCoinById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(coinService.getCoinById(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<BaseResponse> addCoin(@RequestBody CoinResponse coinResponse){
        try {
            return new ResponseEntity<>(coinService.addNewCoin(coinResponse), HttpStatus.OK);
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
