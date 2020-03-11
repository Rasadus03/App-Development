package com.suse.cap.carrenting.data.controller;

import org.springframework.data.repository.CrudRepository;

import com.suse.cap.carrenting.data.Car;

public interface  DatabaseController extends CrudRepository<Car, String> {
	
}
