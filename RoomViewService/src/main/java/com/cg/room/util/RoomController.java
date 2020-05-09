package com.cg.room.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.room.model.Hotel;
import com.cg.room.dao.RoomRepositoryProxy;
import com.cg.room.model.Room;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {
	
	@Autowired
	private RoomRepositoryProxy proxy;
	
	@GetMapping("/rooms")
	public List<Room> findAll(){
		return proxy.findAllRooms();
	}
	
	@GetMapping("/rooms/hotelName/{name}")
	public List<Room> findRoomsByHotelName(@PathVariable String name) {
		Hotel hotel = proxy.findAll().stream().filter(h -> h.getHotelName().equals(name)).findAny().get();
		return hotel.getRooms();
	}
	
	@GetMapping("/rooms/hotelName/{name}/roomType/{type}")
	public List<Room> findRoomsByType(@PathVariable String name, @PathVariable String type){
		Hotel hotel = proxy.findAll().stream().filter(h -> h.getHotelName().equals(name)).findAny().get();
		return hotel.getRooms().stream().filter(l -> l.getRoomType().equals(type)).collect(Collectors.toList());
	}
}
