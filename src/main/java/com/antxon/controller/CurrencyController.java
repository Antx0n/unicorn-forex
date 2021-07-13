package com.antxon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.antxon.model.ExchangeRate;
import com.antxon.services.ForexService;

@RestController
@CrossOrigin
@RequestMapping(path ="api/v1")
public class CurrencyController {
	
	@Autowired
	ForexService forexService;
	
	@GetMapping(value ="/currency/{cur}")
	@ResponseBody
	public ResponseEntity<ExchangeRate> fetchRate(@PathVariable String cur) {
		ResponseEntity<String> xr = this.forexService.getRate(cur);
		if (xr.getStatusCode().equals(HttpStatus.OK)) {
			ExchangeRate exr = new ExchangeRate(cur, Float.parseFloat(xr.getBody()));
			return new ResponseEntity<>(exr, new HttpHeaders(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
