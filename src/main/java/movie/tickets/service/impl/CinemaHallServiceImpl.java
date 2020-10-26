package movie.tickets.service.impl;

import java.util.List;
import movie.tickets.dao.CinemaHallDao;
import movie.tickets.model.CinemaHall;
import movie.tickets.service.CinemaHallService;
import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private final CinemaHallDao cinemaHallDao;

    public CinemaHallServiceImpl(CinemaHallDao cinemaHallDao) {
        this.cinemaHallDao = cinemaHallDao;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }

    @Override
    public CinemaHall get(Long cinemaHallId) {
        return cinemaHallDao.get(cinemaHallId);
    }
}
