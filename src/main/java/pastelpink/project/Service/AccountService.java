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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String md5Hash = DigestUtils.md5Hex(pass.trim());
        User checkLoginUser = userRepository.findAll().stream().filter(q->q.getEmail().toString().equals(username.trim()) && q.getPassword().toString().equals(md5Hash)).findFirst().orElse(null);
        if(checkLoginUser == null)
        {
            return 0;
        }
        else
        {
            return 1;
        }
        
    }
}
