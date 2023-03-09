package com.frankmoley.lil.learningspring.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.frankmoley.lil.learningspring.data.Room;
import com.frankmoley.lil.learningspring.data.RoomRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;


@RequestMapping("room")
@RestController(value = "rooms")
@ApiOperation("Products API")
public class RoomController 
{
	private static final Logger logger = LogManager.getLogger(RoomController.class);
	
	@Autowired
	private RoomRepository roomRepository;
	
	
	@GetMapping(value = "allRooms")
	@ApiOperation(value = "Get a product by id", notes = "Returns a product as per the id")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Successfully retrieved"),
	  @ApiResponse(code = 404, message = "Not found - The product was not found")
	})
	@ResponseBody
	public List<Room> getAllRooms(){
		List<Room> ls = new ArrayList<Room>();
		logger.info("getting all rooms");
		roomRepository.findAll().forEach(ls::add);
		return ls;
	}
	
	@PostMapping(value = "newRoom", consumes = {"application/json"})
	@ApiOperation(value = "Create a room", notes = "Returns a product as per the id")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Successfully room inserted"),
	})
	public long newRoom(@RequestBody Room room) {
		logger.info("creating new rooms");
		roomRepository.save(room);
		return room.getId();
	}
	
	@PutMapping(value = "updRoom")
	@ApiOperation(value = "Update a room", notes = "Returns a product as per the id")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Successfully room updated"),
	})
	public long updRoom(@RequestBody Room room) 
	{
		logger.info("updating rooms");
		Room r = roomRepository.findById(room.getId()).get();
		roomRepository.save(r);
		return room.getId();
	}
	
	@GetMapping(value = "getByRoomNumber/{roomNumber}")
	@ApiOperation(value = "Get by number", notes = "Returns a product as per the id")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Successfully room updated"),
	})
	public ResponseEntity<List<Room>> getRoomByRoomNumber(@PathVariable String roomNumber) {
		logger.info("getting by room number");
		List<Room> r = roomRepository.findByRoomNumber(roomNumber);
		return new ResponseEntity<List<Room>>( r, HttpStatus.OK);
	}
}