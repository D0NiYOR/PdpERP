package uz.pdp.pdperp.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.pdperp.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query(value = "from users  u where not u.delete")
    Page<UserEntity> getAll(Pageable pageable);
    Optional<UserEntity> findUserEntitiesByEmail(String email);
    boolean existsUserEntitiesByEmail(String email);
}
