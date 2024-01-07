package pastelpink.project.Service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import pastelpink.project.Repository.UserRepository;
import pastelpink.project.Entity.User;

@Service
public class AccountService {
    @Autowired
    private UserRepository userRepository;

    public int Login(String username, String pass)
    {
        String md5Hash = DigestUtils.md5Hex(pass.trim());
        User checkLoginUser = userRepository.getUserByEmail(username, md5Hash);
        if(checkLoginUser == null)
        {
            return 0;
        }
        else
        {
            return 1;
        }
        
    }

    public String getUser(String email)
    {
        User u = userRepository.findAll().stream().filter(q-> q.getEmail().toString().trim().equals(email.toString().trim())).findFirst().get();
        String name = u.getTen();

        return name;
    }

    public int AccountIsAvailable(String email)
    {
        User u;
        try
        {
            u = userRepository.findAll().stream().filter(q-> q.getEmail().toString().trim().equals(email.toString().trim())).findFirst().get();  
        }
        catch(Exception e)
        {
            u = null;
        }

        if(u != null)
        {
                return 1;
        }
        else
        {
            return 0;
        }
    }

    public int SignUpAccount(String email, String name, String pass)
    {
        User u = userRepository.findAll().stream().filter(q-> q.getEmail().toString().trim().equals(email.toString().trim())).findFirst().orElse(null);
        if(u != null)
        {
            return 0;
        }
        else
        {
            User newUser = new User();
            String md5Hash = DigestUtils.md5Hex(pass.trim());
            newUser.setEmail(email);
            newUser.setIdGoogle(null);
            newUser.setPassword(md5Hash);
            userRepository.save(newUser);
            return 1;
        }
    }
}
