package com.ffp.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ffp.data.Negotiation;
import com.ffp.service.NegotiationService;

@RestController
@RequestMapping(value = "/negotiation")
@CrossOrigin(origins = "*")
public class NegotiationController {
	
	@Autowired
	private NegotiationService negotiationService;
	
	@RequestMapping (value = "/getAllNegotiation", method = RequestMethod.GET)
	public ResponseEntity<List<Negotiation>> getAllNegotiation() {
		HttpStatus status = HttpStatus.OK;
		List<Negotiation> negotiations = negotiationService.findAll();
		if (negotiations != null && !negotiations.isEmpty()) {
			return new ResponseEntity<List<Negotiation>>(negotiations, status);
		} else {
			return new ResponseEntity<List<Negotiation>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping (value = "/getNegotiationById/{Id}", method = RequestMethod.GET)
	public ResponseEntity<Negotiation> getNegotiationById(@PathVariable("Id") Integer Id) {
		HttpStatus status = HttpStatus.OK;
		Negotiation negotiation = negotiationService.findById(Id);
		if (negotiation != null) {
			return new ResponseEntity<Negotiation>(negotiation, status);
		} else {
			return new ResponseEntity<Negotiation>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping (value = "/getNegotiationBySellerID/{sellerId}", method = RequestMethod.GET)
	public ResponseEntity<List<Negotiation>> getNegotiationBySellerID(@PathVariable("sellerId") Integer sellerId) {
		HttpStatus status = HttpStatus.OK;
		List<Negotiation> negotiations = negotiationService.findBySellerId(sellerId);
		if (negotiations != null && !negotiations.isEmpty()) {
			return new ResponseEntity<List<Negotiation>>(negotiations, status);
		} else {
			return new ResponseEntity<List<Negotiation>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping (value = "/getNegotiationByBuyerID/{buyerId}", method = RequestMethod.GET)
	public ResponseEntity<List<Negotiation>> getNegotiationByBuyerID(@PathVariable("buyerId") Integer buyerId) {
		HttpStatus status = HttpStatus.OK;
		List<Negotiation> negotiations = negotiationService.findByBuyerId(buyerId);
		if (negotiations != null && !negotiations.isEmpty()) {
			return new ResponseEntity<List<Negotiation>>(negotiations, status);
		} else {
			return new ResponseEntity<List<Negotiation>>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@RequestMapping (value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Negotiation> create(@RequestBody Negotiation negotiation) {
		Negotiation newNegotiation = negotiationService.save(negotiation);
		if (newNegotiation != null) {
			return new ResponseEntity<Negotiation>(newNegotiation, HttpStatus.OK);
		} else {
			return new ResponseEntity<Negotiation>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping (value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Negotiation> update(@RequestBody Negotiation negotiation) {
		Negotiation newNegotiation = negotiationService.save(negotiation);
		if (newNegotiation != null) {
			return new ResponseEntity<Negotiation>(newNegotiation, HttpStatus.OK);
		} else {
			return new ResponseEntity<Negotiation>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping (value = "/getNegotiationByStatus/{status}", method = RequestMethod.GET)
	public ResponseEntity<List<Negotiation>> getNegotiationByStatus(@PathVariable("status") String status) {
		HttpStatus httpStatus = HttpStatus.OK;
		List<Negotiation> negotiations = negotiationService.findByStatus(status);
		if (negotiations != null && !negotiations.isEmpty()) {
			return new ResponseEntity<List<Negotiation>>(negotiations, httpStatus);
		} else {
			return new ResponseEntity<List<Negotiation>>(HttpStatus.NOT_FOUND);
		}	
	}
}
