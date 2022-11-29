package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Посмотреть работает или нужно обновление и сохранение разложить
    @Override
    public void saveUser(User user) {
        if (!user.getPassword().equals(getUser(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    @Override
    public User getUser(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }
}

////    @PersistenceContext
////    private EntityManager em;
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Autowired
//    RoleRepository repository;
//
//    private final PasswordEncoder passwordEncoder;
////    @Autowired
////    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public User loadUserByUsername (String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUserName(username);
//        if (user == null) {throw new UsernameNotFoundException("User not found");
//        }
//        return user;
//    }
//
//    @Transactional
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//
//    public boolean saveUser(User user) {
//        User userFromDB = userRepository.findByUserName(user.getUsername());
//
//        if (userFromDB != null) {
//            return false;
//        }
//        user.setRole(Collections.singleton(new Role(1L, "ROLE_USER")));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return true;
//    }
//
//
//    public User getUser(int id) {
//        return userRepository.findById(id).orElse(new User());
//    }
//
//
//    public boolean deleteUser(int id) {
//
//        if (userRepository.findById(id).isPresent()) {
//            userRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//
////    public List<User> usergetList(Long idMin) {
////        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
////                .setParameter("paramId", idMin).getResultList();
////    }
