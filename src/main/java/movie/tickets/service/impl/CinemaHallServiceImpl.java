package movie.tickets.service.impl;

import java.util.List;
import movie.tickets.dao.CinemaHallDao;
import movie.tickets.lib.Inject;
import movie.tickets.lib.Service;
import movie.tickets.model.CinemaHall;
import movie.tickets.service.CinemaHallService;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
