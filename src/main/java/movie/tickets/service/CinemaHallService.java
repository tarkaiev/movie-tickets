package movie.tickets.service;

import java.util.List;
import movie.tickets.model.CinemaHall;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);
    
    List<CinemaHall> getAll();
}
