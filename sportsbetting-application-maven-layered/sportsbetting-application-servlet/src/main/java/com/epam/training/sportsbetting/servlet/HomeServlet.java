package com.epam.training.sportsbetting.servlet;

import static org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnServletContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.training.sportsbetting.business.dao.PlayerDao;
import com.epam.training.sportsbetting.domain.Player;

public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PlayerDao playerDao;

    @Autowired
    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        processInjectionBasedOnServletContext(this, getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {   
        try {
            persist(req);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Player player = playerDao.getPlayerByEmail("root");
        req.setAttribute("player", player);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
    
    private void persist(HttpServletRequest req) throws ParseException {
        Player player = playerDao.getPlayerByEmail("root");
        setPlayerFields(req, player);
        playerDao.savePlayerChanges(player);
    }

    private void setPlayerFields(HttpServletRequest req, Player player) throws ParseException {
        player.setName(req.getParameter("name"));
        player.setAccountNumber(Integer.parseInt(req.getParameter("account")));
        Integer balance = Integer.parseInt(req.getParameter("balance"));
        player.setBalance(BigDecimal.valueOf(balance));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(req.getParameter("date"));
        LocalDate birth = new java.sql.Date( date.getTime() ).toLocalDate();
        player.setBirth(LocalDateTime.of(birth, LocalTime.of(12, 12)));
    }

}
