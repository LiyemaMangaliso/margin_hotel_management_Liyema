package za.ac.cput.marginhotelmanagement.service;

import za.ac.cput.marginhotelmanagement.domain.Room;
import za.ac.cput.marginhotelmanagement.enums.RoomStatus;
import java.util.List;

public interface IRoomService extends IService<Room, Long> {

    // Custom query method for room statuses
    List<Room> getRoomByStatus(RoomStatus status);

    // Explicitly add deleteById to make it visible to RoomController
    boolean deleteById(Long roomId);
}
