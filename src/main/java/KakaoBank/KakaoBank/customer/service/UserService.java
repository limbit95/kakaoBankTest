package KakaoBank.KakaoBank.customer.service;

import KakaoBank.KakaoBank.customer.domain.User;
import KakaoBank.KakaoBank.customer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userrepository;

    @Autowired
    public UserService(UserRepository userrepository) {
        this.userrepository = userrepository;
    }

    public void save(User user){
        userrepository.save(user);
    }

    public User findByEmail(String email){
        return userrepository.findByEmail(email).orElse(null);
    }

//    public Optional<User> findById(Long id){
//        return userrepository.findById(id);
//    }


}
