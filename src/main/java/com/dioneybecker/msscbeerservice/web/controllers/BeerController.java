package com.dioneybecker.msscbeerservice.web.controllers;

import java.util.List;
import java.util.UUID;

import com.dioneybecker.msscbeerservice.web.models.BeerDto;
import com.dioneybecker.msscbeerservice.web.services.BeerService;
import com.dioneybecker.brewery.model.BeerDto;
import com.dioneybecker.brewery.model.BeerPagedList;
import com.dioneybecker.brewery.model.BeerStyleEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * BeerController
 */
@RequestMapping("/api/v1/beer/")
@RestController
public class BeerController {

    @Autowired
    BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
        BeerDto beer = this.beerService.getBeerById(beerId);

        // if(beer != null){
            return new ResponseEntity<>(beer, HttpStatus.OK);
        // } else {
            // return new ResponseEntity<>(HttpStatus.OK);
        // }
    }

    @GetMapping("/")
    public ResponseEntity<List<BeerDto>> getAll() {
        return new ResponseEntity<>(this.beerService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@RequestBody @Validated BeerDto beer) {
        BeerDto savedBeer = this.beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beer) {
        this.beerService.updateBeer(beerId, beer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable("beerId") UUID beerId) {
        this.beerService.deleteById(beerId);
    }

}