package services;

import models.dto.AdminRoomDto;
import repositories.AdminRoomRepository;

import java.util.List;

public class AdminRoomService {

    private AdminRoomRepository roomRepository = new AdminRoomRepository();

    public List<AdminRoomDto> getAllRooms() {
        return roomRepository.getAllRooms();
    }

    public void addRoom(AdminRoomDto roomDto) {
        roomRepository.addRoom(roomDto.getRoomID(), roomDto.getRoomType(), roomDto.getCapacity(), roomDto.getFloor());
    }

    public void updateRoom(AdminRoomDto roomDto) {
        roomRepository.updateRoom(roomDto.getRoomID(), roomDto.getRoomType(), roomDto.getCapacity(), roomDto.getFloor());
    }

    public void deleteRoom(String roomID) {
        roomRepository.deleteRoom(roomID);
    }

//    public int getTotalRooms() {
//        return roomRepository.getTotalRooms();
//    }
//
//    public int getTotalRoomsByType(String roomType) {
//        return roomRepository.getTotalRoomsByType(roomType);
//    }
//
//    public List<AdminRoomDto> getRoomsByFloor(int floor) {
//        return roomRepository.getRoomsByFloor(floor);
//    }

    // You can add more methods here to handle other business logic related to rooms
}
