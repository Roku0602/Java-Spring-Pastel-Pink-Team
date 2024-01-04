package pastelpink.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pastelpink.project.Entity.Room_detail;
import pastelpink.project.Repository.Room_detailRepository;
import pastelpink.project.Repository.RoomsRepository;

@Service
public class HomeService {

    @Autowired
    private Room_detailRepository room_detailRepository;

    @Autowired 
    private RoomsRepository roomsRepository;

    public List<Room_detail> getallroom()
    {
         List<Room_detail> lstroom = room_detailRepository.findAll();
         return lstroom; 
    }

    public Room_detail CheckCountNumPlayer(int idRoom)
    {
        try{
            Room_detail soluong = room_detailRepository.findAll().stream().filter(s -> s.getRoomid().getIdRoom() == idRoom).findFirst().orElseThrow(null);
            return soluong;
        }
        catch(Exception e)
        {
            return null;
        }
       
    }
    
    public void updateRoomPlayerCount(int idRoom)
    {
         Room_detail soluong = room_detailRepository.findAll().stream().filter(s -> s.getRoomid().getIdRoom() == idRoom).findFirst().get();
         if(soluong.getSoLuong() == 0 || soluong.getSoLuong() == null)
         {
            soluong.setSoLuong(0+1);
         }
         else if(soluong.getSoLuong() < 2 && soluong.getSoLuong() > 0)
         {
            soluong.setSoLuong(soluong.getSoLuong() + 1);
         }
         room_detailRepository.save(soluong);
    }
    
    public void outgame(int idRoom)
    {
         Room_detail soluong = room_detailRepository.findAll().stream().filter(s -> s.getRoomid().getIdRoom() == idRoom).findFirst().get();
         if(soluong.getSoLuong() > 0)
         {
            soluong.setSoLuong(soluong.getSoLuong() - 1);
         }
         room_detailRepository.save(soluong);
    }

    public void stopgame(int idRoom)
    {
        Room_detail soluong = room_detailRepository.findAll().stream().filter(s -> s.getRoomid().getIdRoom() == idRoom).findFirst().get();
        // roomsRepository.deleteById(idRoom);
        room_detailRepository.deleteById(soluong.getId());
        
    }
    public String getRoomMaster(int id)
    {
         Room_detail soluong = room_detailRepository.findAll().stream().filter(s -> s.getRoomid().getIdRoom() == id).findFirst().get();
         String nameMaster = soluong.getUserid().getEmail();
         return nameMaster;
    }
}
