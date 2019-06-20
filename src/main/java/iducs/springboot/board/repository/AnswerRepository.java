package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.AnswerEntity;
import iducs.springboot.board.entity.BoardEntity;

@Repository
public interface AnswerRepository 
	extends JpaRepository<AnswerEntity, Long> {
	
	List<AnswerEntity> findAll(Sort sort); 
	Page<AnswerEntity> findAll(Pageable pageable);
}
