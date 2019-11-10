package com.globomatics.bike.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globomatics.bike.models.Bike;
import com.globomatics.bike.repositories.BikeRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/v1/bikes")
public class BikesController {
	
	@Autowired
	private BikeRepository bikeRepository;

	@ApiOperation(value = "Must return a list of bikes for the user, so the user can buy one or more")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return the bikes list"),
			@ApiResponse(code = 403, message = "User not have permission to visualize"),
			@ApiResponse(code = 500, message = "Generic Exception Generated"),
	})
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	List<Bike> list(){
		return bikeRepository.findAll();
	}

	@ApiOperation(value = "Provides a way to the user to register their bike information")
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody Bike bike) {
		bikeRepository.save(bike);
	}

	@ApiOperation(value = "Must return information of a bike by the bike code")
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Bike get(@PathVariable("id") Long id) {
		return bikeRepository.findById(id).get();
	}
}
