package pastelpink.project.Controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        }
        else
        {
            session.setAttribute("errorLog", "Tài khoản hoặc mật khẩu không chính xác!");
            return "redirect:/login";
        }
        return "";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Xóa tất cả các attribute của session
        session.invalidate();
        // Chuyển hướng đến trang đăng nhập hoặc trang chính của ứng dụng
        return "redirect:/login";
    }
}