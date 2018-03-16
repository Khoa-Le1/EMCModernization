package ca.ehealthsask.emc.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ca.ehealthsask.emc.demo.Entity.User;
import ca.ehealthsask.emc.demo.Entity.request.AddUserRequest;
import ca.ehealthsask.emc.demo.Repository.UserRepository;

import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Repository
@Transactional
@RestController
public class UserController {

    @Autowired
    @PersistenceContext
    private UserRepository userRepository;

    public User find(int id){
        return userRepository.findByPersonID(id);
    }

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int insert (User user){
        userRepository.findByPersonID(user.getPersonID());
        return user.getPersonID();
    }



    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    /*
    @RequestMapping("/")
    public String Home(HttpServletRequest request){
        request.setAttribute("USERS", userRepository.findAll());
        return "PERSONS";
    }

    @RequestMapping(method = RequestMethod.GET)
    public void addUser (@RequestBody AddUserRequest addUserRequest){
        User user = new User();
        user.setFirstName(addUserRequest.getName());
        user.setLastName(addUserRequest.getSurname());
        userRepository.save(user);
    }
    */


}