package iducs.springboot.board.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import iducs.springboot.board.entity.BoardEntity;
import iducs.springboot.board.entity.UserEntity;


public interface BoardRepository 
	extends JpaRepository<BoardEntity, Long> {	
	//List<QuestionEntity> findAllOrderByCreateTime();
	
	List<BoardEntity> findAll(Sort sort); 
	Page<BoardEntity> findAll(Pageable pageable); 
}
