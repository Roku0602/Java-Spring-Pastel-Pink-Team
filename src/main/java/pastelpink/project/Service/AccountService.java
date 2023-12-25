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
}
