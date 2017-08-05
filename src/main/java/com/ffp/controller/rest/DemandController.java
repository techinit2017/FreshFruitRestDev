package com.ffp.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ffp.data.Demand;
import com.ffp.service.DemandService;

@RestController
@RequestMapping(value = "/demand")
@CrossOrigin(origins = "*")
public class DemandController {

	@Autowired
	private DemandService demandService;
	
	@RequestMapping(value = "/getAllDemands", method = RequestMethod.GET)
	public ResponseEntity<List<Demand>> getAllDemands() {
		List<Demand> allDemands = demandService.findAll();
		return new ResponseEntity<List<Demand>>(allDemands, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getDemandByID/{id}", method = RequestMethod.GET)
	public ResponseEntity<Demand> getDemandByID(@PathVariable("id") Integer id) {
		Demand demand = demandService.findOne(id);
		return new ResponseEntity<Demand>(demand, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Demand demand, UriComponentsBuilder ucBuilder) {
		demandService.delete(demand);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Demand demand, UriComponentsBuilder ucBuilder) {
		Demand newDemand = demandService.save(demand);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/getDemandByID/{id}").buildAndExpand(newDemand.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Demand demand, UriComponentsBuilder ucBuilder) {
		Demand newDemand = demandService.save(demand);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/getDemandByID/{id}").buildAndExpand(newDemand.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.ACCEPTED);
	}
}
