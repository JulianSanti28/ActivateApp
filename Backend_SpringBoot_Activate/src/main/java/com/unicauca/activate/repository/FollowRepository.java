
package com.unicauca.activate.repository;

import com.unicauca.activate.model.Follow;
import com.unicauca.activate.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Paula
 */
@Transactional(readOnly = true)
public interface FollowRepository extends JpaRepository<Follow,Long>{
    
    Optional<Follow> findByFromAndTo(User from , User to);
}
