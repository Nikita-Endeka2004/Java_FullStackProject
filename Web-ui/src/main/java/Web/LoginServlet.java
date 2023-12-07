package Web;

import domain.Followings;
import domain.Publishing;
import services.FollowingsServiceImpl;
import services.PublishingServiceImpl;
import services.UserServiceImpl;
import services_api.FollowingsService;
import services_api.PublishingService;
import services_api.UserService;
import domain.dto.AppUserViewDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private PublishingService publishingService = new PublishingServiceImpl();
    private FollowingsService followingsService = new FollowingsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Reg_Form/index");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        AppUserViewDto user = userService.login(email, password);
        HttpSession session = req.getSession(true);
        Collection<Publishing> publishings = publishingService.readPublishings();
        Collection<Followings> followings = followingsService.readFollowings();
        session.setAttribute("publishings", publishings);
        session.setAttribute("followings", followings);
        session.setAttribute("user", user);
        resp.sendRedirect("/Reg_Form/index");
    }
}