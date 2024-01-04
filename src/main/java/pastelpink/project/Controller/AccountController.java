package pastelpink.project.controller;


import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pastelpink.project.Service.AccountService;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
     @GetMapping("/login")
    public String loginfrm() {
        return "Account/login.html";
    }

    // @GetMapping("/GoogleLogin")
    //  public String LoginGoogle() {
    //     return "Account/loginGoogle"; // Trả về tên của view
    // }

    @PostMapping("/login")
    public String loginfunc(Model model,@RequestParam(name = "username") String tk,
            @RequestParam(name = "password") String mk, HttpSession session) {
                System.out.println("tk:"+tk+"mk"+mk);
                 String md5Hash = DigestUtils.md5Hex(mk.trim());
                 System.out.println("end/ "+md5Hash);
        int login = accountService.Login(tk, mk);
        if(login == 1)
        {
            if(session.getAttribute("errorLog") != null)
            {
                session.removeAttribute("errorLog");
            }
            session.setAttribute("user", tk);
            String name = accountService.getUser(tk);
            session.setAttribute("playername",name );
           
        }
        else
        {
            session.setAttribute("errorLog", "Tài khoản hoặc mật khẩu không chính xác!");
            return "redirect:/login";
        }
        System.out.println(session.getAttribute("user"));
         return "redirect:/";
    }

    //  @GetMapping("/LoginGoogleSuccess")
    //  public String AfterLogin(OAuth2AuthenticationToken authentication, HttpSession session, Model model)
    //  {
    //     OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
    //     int ketquakiemtra = accountService.AccountIsAvailable(oauth2User.getAttribute("email"));
    //     if(ketquakiemtra == 1)
    //     {
    //         //Đã tồn tại tài khoản
    //         session.setAttribute("user", oauth2User.getAttribute("email"));
    //         // String name = accountService.getUser(tk);
    //         session.setAttribute("playername",oauth2User.getAttribute("name"));
    //     }
    //     else
    //     {
    //         //Chưa tồn tại tài khoản
    //     }
        
    //     return "redirect:/";
    //  }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Xóa tất cả các attribute của session
        session.invalidate();
        // Chuyển hướng đến trang đăng nhập hoặc trang chính của ứng dụng
        return "redirect:/login";
    }

    
}
