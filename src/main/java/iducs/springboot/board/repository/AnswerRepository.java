package iducs.springboot.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.AnswerEntity;

@Repository
public interface AnswerRepository 
	extends JpaRepository<AnswerEntity, Long> {

}
