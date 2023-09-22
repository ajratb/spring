package com.example;

import com.example.Person.Gender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

import static com.example.Person.Gender.*;

@Slf4j
@SpringBootApplication
public class SbJacksonApplication implements CommandLineRunner {

	@Autowired
	ObjectMapper jsonMapper;

	public static void main(String[] args) {
		SpringApplication.run(SbJacksonApplication.class, args);
	}

	@Override
	public void run(String... args) throws JsonProcessingException {

		Person feodor = new Person("Feodor", "Mihailovich", MALE, 38);
		String toJson = jsonMapper.writeValueAsString(feodor);
		log.info(toJson);
		String fromJson = "{\"firstName\":\"Svetlana\",\"lastName\":\"Egorovna\",\"gender\":\"Female\",\"age\":24}";
		Person person = jsonMapper.readValue(fromJson, Person.class);
		log.info(person.toString());

		// jackson csv:
		// use only for simple-lists
		List<DataItem> items = new ArrayList<>();
		items.add(new DataItem(1, "firstItem"));
		items.add(new DataItem(2, "secondItem"));
		// get csv from json
		String listToJson = jsonMapper.writeValueAsString(items);
		JsonNode jsonTree = jsonMapper.readTree(listToJson);

		CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
		JsonNode firstObject = jsonTree.elements().next();
		firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
		CsvSchema csvJsonTreeSchema = csvSchemaBuilder.build().withHeader();
		CsvMapper csvMapper = new CsvMapper();
		String listToCsv = csvMapper.writerFor(JsonNode.class)
				.with(csvJsonTreeSchema)
				.writeValueAsString(jsonTree);
		log.info("TestData json: {}", listToCsv);

	}
}
