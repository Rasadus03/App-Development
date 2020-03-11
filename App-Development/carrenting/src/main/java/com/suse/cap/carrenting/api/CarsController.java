package com.suse.cap.carrenting.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.suse.cap.carrenting.data.Car;

@RestController
@RequestMapping(value = "/cars")
public class CarsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CarsController.class);
	private CrudRepository<Car, String> carDb;

	@Autowired
	public CarsController(CrudRepository<Car, String> carDb) {
		this.carDb = carDb;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Iterable<Car> cars() {
		LOGGER.info("Load all Cars!!!!");
		Iterable<Car> cars = carDb.findAll();
		LOGGER.info("All Cars! --" + cars);
		return cars;

	}

	@RequestMapping(value = "/addCar", method = RequestMethod.PUT)
	public Car add(@RequestBody @Valid Car car) {
		LOGGER.info("Saving a car! car details -- " + car);
		return carDb.save(car);
	}

	@RequestMapping(value = "/updateCar", method = RequestMethod.POST)
	public Car update(@RequestBody @Valid Car car) {
		LOGGER.info("Updating a car! car details -- " + car);
		Car existingCar = carDb.findById(car.getId()).orElse(null);
		existingCar.setRented(car.isRented());
		existingCar.setRentedPersonContactEmail(car.getRentedPersonContactEmail());
		existingCar.setRentedPersonFirstName(car.getRentedPersonFirstName());
		existingCar.setRentedPersonLastName(car.getRentedPersonLastName());
		existingCar.setRentedPersonContactNumber(car.getRentedPersonContactNumber());
		return carDb.save(existingCar);
	}

	@RequestMapping(value = "/getCar/{id}", method = RequestMethod.GET)
	public Car getById(@PathVariable String id) {
		LOGGER.info("Loading a car by id! id -- " + id);
		Car car = carDb.findById(id).orElse(null);
		LOGGER.info("loaded car = " + car);
		return car;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable String id) {
		LOGGER.info("Deleteing a car by id! id -- " + id);
		carDb.deleteById(id);
	}

}
