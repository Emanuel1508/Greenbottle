package com.example.greenbottle.bottle;

import com.example.greenbottle.customer.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v2/bottle")
public class BottleController {

    private final BottleService bottleService;

    @Autowired
    public BottleController(BottleService bottleService) {
        this.bottleService = bottleService;
    }


    @PostMapping
    public void registerNewBottle(@RequestBody Bottle bottle) throws MyException {bottleService.registerNewBottle(bottle);}
}
