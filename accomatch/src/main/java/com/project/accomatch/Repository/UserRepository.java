package com.project.accomatch.Repository;

import com.project.accomatch.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {


    @Query(value = "INSERT INTO users(email, `name`, password, age, gender, mobile, address, is_admin, is_leaseholder, created_at, updated_at) " +
            "VALUES (:#{#user.email}, :#{#user.name}, :#{#user.password}, :#{#user.age}, :#{#user.gender}, :#{#user.mobile}, :#{#user.address}, :#{#user.is_admin}, :#{#user.is_leaseholder}, :#{#user.created_at}, :#{#user.updated_at})", nativeQuery = true)
    void createUser(@Param("user") UserModel user);


}
