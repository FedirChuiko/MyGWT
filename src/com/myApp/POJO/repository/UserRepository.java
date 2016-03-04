package com.myApp.POJO.repository;

import com.myApp.POJO.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Fedir on 03.03.2016.
 */
public interface UserRepository extends JpaRepository <User,String>{
}
