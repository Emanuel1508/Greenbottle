package com.example.greenbottle.bottle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BottleService {

    private final BottleRepository bottleRepository;

    @Autowired
    public BottleService(BottleRepository bottleRepository) {

        this.bottleRepository = bottleRepository;
    }

    public void registerNewBottle(Bottle bottle) {
        bottleRepository.save(bottle);
    }
}
