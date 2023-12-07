package Web;

import domain.Followings;
import domain.Publishing;
import domain.dto.AppUserCreateDto;
import domain.dto.AppUserViewDto;
import services.FollowingsServiceImpl;
import services.PublishingServiceImpl;
import services.UserServiceImpl;
import services_api.FollowingsService;
import services_api.PublishingService;
import services_api.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "registrationServlet", urlPatterns = "/registrationServlet")
public class RegistrationServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private PublishingService publishingService = new PublishingServiceImpl();
    private FollowingsService followingsService = new FollowingsServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException {
        AppUserCreateDto appUserCreateDto = extractUserFromRequest(req);
        AppUserViewDto user = userService.registerUser(appUserCreateDto);
        req.getSession().setAttribute("user",user);
        HttpSession session = req.getSession(true);
        Collection<Publishing> publishings = publishingService.readPublishings();
        Collection<Followings> followings = followingsService.readFollowings();
        session.setAttribute("publishings", publishings);
        session.setAttribute("followings", followings);
        session.setAttribute("user", user);
        resp.sendRedirect("/Reg_Form/index");
    }
    private AppUserCreateDto extractUserFromRequest(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String nickname = req.getParameter("nickname");
        Long wallet = Long.valueOf(req.getParameter("wallet"));
        Boolean isBlocked = Boolean.valueOf(req.getParameter("isBlocked"));

        return new AppUserCreateDto(name, nickname, isBlocked, wallet, email, password);
    }
}