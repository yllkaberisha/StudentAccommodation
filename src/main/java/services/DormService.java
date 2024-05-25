package services;

import repositories.DormRepository;

public class DormService {

    private DormRepository dormRepository;

    public DormService() {
        dormRepository = new DormRepository();
    }

    public long getTotalCapacityForMaleRooms() {
        return dormRepository.getTotalCapacityForRoomType("M");
    }

    public long getTotalCapacityForFemaleRooms() {
        return dormRepository.getTotalCapacityForRoomType("F");
    }

    public long getTotalCapacity() {
        return getTotalCapacityForMaleRooms() + getTotalCapacityForFemaleRooms();
    }
}