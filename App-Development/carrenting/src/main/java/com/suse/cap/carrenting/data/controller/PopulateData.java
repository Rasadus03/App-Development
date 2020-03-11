package com.suse.cap.carrenting.data.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.init.Jackson2ResourceReader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suse.cap.carrenting.api.CarsController;
import com.suse.cap.carrenting.data.Car;

public class PopulateData implements ApplicationListener<ApplicationReadyEvent> {
	@Autowired
	DatabaseController databaseController;
	private static final Logger LOGGER = LoggerFactory.getLogger(PopulateData.class);
	private final Jackson2ResourceReader resourceReader;
	private final Resource sourceData;
	private JSONArray jsonArray;

	public PopulateData() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		resourceReader = new Jackson2ResourceReader(mapper);
		sourceData = new ClassPathResource("AllCars.json");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(sourceData.getFile()));
			jsonArray = (JSONArray) obj;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		CrudRepository<Car, String> carsDatabase = BeanFactoryUtils
				.beanOfTypeIncludingAncestors(event.getApplicationContext(), CrudRepository.class);
		if (carsDatabase != null && carsDatabase.count() == 0) {
			LOGGER.info("Loading the in memory Database for the cars!");
			populate(carsDatabase);
		}

	}

	private void populate(CrudRepository carsDatabase) {

		Iterator iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObject = (JSONObject) iterator.next();
			Car car = new Car();
			byte[] photo = null;
			car.setDescription(jsonObject.get("description").toString());
			car.setTitle(jsonObject.get("title").toString());
			System.out.println("jsonObject.get(\"isRented\") " + jsonObject.get("isRented"));
			car.setRented(jsonObject.get("isRented").toString().equalsIgnoreCase("true") ? true : false);
			car.setPhotoAlt(jsonObject.get("photoAlt").toString());
			try {
				File photoFile = new ClassPathResource(jsonObject.get("photo").toString()).getFile();
				photo = new byte[(int) photoFile.length()];
				new FileInputStream(photoFile).read(photo);
			} catch (IOException e) {
				e.printStackTrace();
			}
			car.setPhoto(photo);
			carsDatabase.save(car);
		}
	}

}
