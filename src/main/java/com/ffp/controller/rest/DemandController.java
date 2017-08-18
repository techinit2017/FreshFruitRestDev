package com.ffp.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.ffp.data.PageParam;
import com.ffp.data.SearchProduct;
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
	
	@RequestMapping(value = "/getDemandByBuyerID/{userId}", method = RequestMethod.POST)
	public ResponseEntity<List<Demand>> getDemandByBuyerID(@PathVariable("userId") Integer userId, @RequestBody PageParam pageParam) {
		Page<Demand> demands = demandService.findByUserProfile(userId, pageParam.getPageRequest());
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = HttpStatus.OK;
		if(demands!=null ){
			List<Demand> demandList= demands.getContent();
			headers.set("RECORD_COUNT",String.valueOf( demands.getTotalElements()));
			httpStatus = HttpStatus.OK;
			return new ResponseEntity<List<Demand>>(demandList,headers, httpStatus);
		}
		else  {
			httpStatus = HttpStatus.NOT_FOUND;
			return new ResponseEntity<>(httpStatus);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestBody Demand demand, UriComponentsBuilder ucBuilder) {
		demandService.delete(demand);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Demand> create(@RequestBody Demand demand, UriComponentsBuilder ucBuilder) {
		Demand newDemand = demandService.save(demand);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/getDemandByID/{id}").buildAndExpand(newDemand.getId()).toUri());
		if (newDemand != null) {
			return new ResponseEntity<Demand>(newDemand, HttpStatus.OK);
		} else {
			return new ResponseEntity<Demand>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Demand> update(@RequestBody Demand demand, UriComponentsBuilder ucBuilder) {
		Demand newDemand = demandService.save(demand);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/getDemandByID/{id}").buildAndExpand(newDemand.getId()).toUri());
		if (newDemand != null) {
			return new ResponseEntity<Demand>(newDemand, HttpStatus.OK);
		} else {
			return new ResponseEntity<Demand>(HttpStatus.NOT_FOUND);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getDemandBySearch", method = RequestMethod.POST)
	public ResponseEntity<List<Demand>> getDemandBySearch(@RequestBody SearchProduct searchProduct) {
		HttpStatus httpStatus = HttpStatus.OK;
		Map<String,Object> returnMap= demandService.findSearch(searchProduct, false);
		List<Demand> allDemand = (List<Demand>)returnMap.get("LIST");
		if (allDemand == null || allDemand.size() == 0) {
			returnMap = demandService.findSearch(searchProduct, true);
			allDemand = (List<Demand>)returnMap.get("LIST");
			if (allDemand == null || allDemand.isEmpty()) {
				httpStatus = HttpStatus.NOT_FOUND;
			} else {
				httpStatus = HttpStatus.PARTIAL_CONTENT;
			}
		}
		HttpHeaders headers = new HttpHeaders();
		headers.set("RECORD_COUNT",String.valueOf( returnMap.get("RECORD_COUNT")));
		return new ResponseEntity<List<Demand>>(allDemand,headers, httpStatus);
	}
}
