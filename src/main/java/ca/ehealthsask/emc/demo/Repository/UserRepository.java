package ca.ehealthsask.emc.demo.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ca.ehealthsask.emc.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

@Query (value = "SELECT * FROM PERSONS WHERE PERSONID =?0001", nativeQuery = true)
User findByPersonID(int personID);

}