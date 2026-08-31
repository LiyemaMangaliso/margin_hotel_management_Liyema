package za.ac.cput.marginhotelmanagement.service;

import org.springframework.stereotype.Service;
import za.ac.cput.marginhotelmanagement.domain.Room;
import za.ac.cput.marginhotelmanagement.repository.RoomRepository;
import za.ac.cput.marginhotelmanagement.enums.RoomStatus;

import java.util.List;

@Service
public class RoomService implements IRoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room create(Room room) {
        // Business rule: Enforce the uniqueness constraint before saving to the DB
        if (roomRepository.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new IllegalArgumentException("A room with number " + room.getRoomNumber() + " already exists.");
        }
        return roomRepository.save(room);
    }

    @Override
    public Room read(Long roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room update(Room room) {
        if (room != null && room.getRoomId() != null && roomRepository.existsById(room.getRoomId())) {
            return roomRepository.save(room);
        }
        return null;
    }

    @Override
    public boolean delete(Room room) {
        if (room != null && room.getRoomId() != null && roomRepository.existsById(room.getRoomId())) {
            roomRepository.delete(room);
            return !roomRepository.existsById(room.getRoomId());
        }
        return false;
    }

    @Override
    public boolean deleteById(Long roomId) {
        if (roomId != null && roomRepository.existsById(roomId)) {
            roomRepository.deleteById(roomId);
            return !roomRepository.existsById(roomId);
        }
        return false;
    }

    @Override
    public List<Room> getRoomByStatus(RoomStatus status) {
        return roomRepository.findByRoomStatus(status);
    }
}
