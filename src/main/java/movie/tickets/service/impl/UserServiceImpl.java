package movie.tickets.service.impl;

import java.util.Optional;
import movie.tickets.dao.UserDao;
import movie.tickets.lib.Inject;
import movie.tickets.lib.Service;
import movie.tickets.model.User;
import movie.tickets.service.UserService;
import movie.tickets.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        byte[] salt = HashUtil.getSalt();
        user.setPassword(HashUtil.hashPassword(user.getPassword(), salt));
        user.setSalt(salt);
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
