package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import iducs.springboot.board.entity.BoardEntity;
import iducs.springboot.board.entity.UserEntity;


public interface UserRepository 
	extends JpaRepository<UserEntity, Long> {	
	UserEntity findByUserId(String userId);
	List<UserEntity> findByNameOrderByIdAsc(String name);
	List<UserEntity> findByCompany(String company);

	Page<UserEntity> findAll(Pageable pageable); 

}
