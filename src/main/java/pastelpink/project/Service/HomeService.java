package pastelpink.project.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pastelpink.project.Entity.Room_detail;
import pastelpink.project.Entity.Room_detailCompositeKey;
import pastelpink.project.Entity.User;
import pastelpink.project.Entity.Rooms;
import pastelpink.project.Repository.Room_detailRepository;
import pastelpink.project.Repository.RoomsRepository;
import pastelpink.project.Repository.UserRepository;

@Service
public class HomeService {

    @Autowired
    private Room_detailRepository room_detailRepository;

    @Autowired 
    private RoomsRepository roomsRepository;

    @Autowired
    private UserRepository userRepository;

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

    public int AddNewRoom(String user, String passwordOfRoom)
    {
        User u = userRepository.findAll().stream().filter(f->f.getEmail().toString().trim().equals(user.toString().trim())).findFirst().get();
        if(u != null)
        {
            Rooms newroom = new Rooms();
            String roomname = u.getTen()+"'s game";
            newroom.setTenPhong(roomname);
            if(passwordOfRoom != null)
            {
                newroom.setRoomPassword(passwordOfRoom);
            }
            else
            {
                newroom.setRoomPassword(null);
            }
            roomsRepository.save(newroom);
            Room_detail newdetail = new Room_detail();
            Room_detailCompositeKey newkey = new Room_detailCompositeKey();
            
            Rooms checkroomid = roomsRepository.getIdByRoomName(roomname);
            newkey.setId_room(checkroomid.getIdRoom());
            newkey.setId_user(u.getIdUser());
            newdetail.setId(newkey);
            newdetail.setRoomid(checkroomid);
            newdetail.setUserid(u);
            newdetail.setSoLuong(0);
            LocalDate now = LocalDate.now();
            newdetail.setNgayTao(now);
            room_detailRepository.save(newdetail);
            return checkroomid.getIdRoom();
        }
        else
        {
            return 0;
        }
    }

    public int CheckpassRoomIsRight(int id,String pass)
    {
        Rooms checkPassword = roomsRepository.findAll().stream().filter(c -> c.getIdRoom() == id).findFirst().get();
        if(checkPassword.getRoomPassword().toString().trim().equals(pass.toString().trim()))
        {
            return 1;
        } 
        else
        {
            return 0;
        }
    }
}
