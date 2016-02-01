package business.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import data.daos.CourtDao;
import data.daos.ReserveDao;
import data.daos.UserDao;
import business.entities.Court;
import business.entities.Reserve;
import business.wrapper.Availability;

@Controller
public class ReserveController {

    private ReserveDao reserveDao;

    private CourtDao courtDao;
    
    private UserDao userDao;

    @Autowired
    public void setReserveDao(ReserveDao reserveDao) {
        this.reserveDao = reserveDao;
    }

    @Autowired
    public void setCourtDao(CourtDao courtDao) {
        this.courtDao = courtDao;
    }
    
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Availability showAvailability(Calendar calendarDay) {
        Calendar endDay = (Calendar) calendarDay.clone();
        endDay.add(Calendar.DAY_OF_MONTH, 1);
        List<Court> courtList = courtDao.findAll();
        Map<Integer, List<Integer>> allTimesAvailable = new HashMap<>();

        int initialHour = 9;
        if (Calendar.getInstance().get(Calendar.YEAR) == calendarDay.get(Calendar.YEAR)
                && Calendar.getInstance().get(Calendar.DAY_OF_YEAR) == calendarDay.get(Calendar.DAY_OF_YEAR)) {
            initialHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        }
        for (Court court : courtList) {
            List<Integer> hourList = new ArrayList<>();
            for (int hour = initialHour; hour <= 23; hour++) {
                hourList.add(hour);
            }
            allTimesAvailable.put(court.getId(), hourList);
        }
        List<Reserve> reserveList = reserveDao.findByDateBetween(calendarDay, endDay);
        for (Reserve reserve : reserveList) {
            allTimesAvailable.get(reserve.getCourt().getId()).remove(new Integer(reserve.getDate().get(Calendar.HOUR_OF_DAY)));
        }
        return new Availability(calendarDay, allTimesAvailable);
    }

    public boolean reserveCourt(Reserve reserve,String username) {
        if (reserveDao.findByCourtAndDate(reserve.getCourt(), reserve.getDate()) != null) {
            return false;
        }
        reserve.setUser(userDao.findByUsernameOrEmail(username));
        reserveDao.save(reserve);
        return true;
    }

}