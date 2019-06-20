package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.Answer;
import iducs.springboot.board.domain.Board;
import iducs.springboot.board.entity.AnswerEntity;
import iducs.springboot.board.entity.BoardEntity;
import iducs.springboot.board.repository.AnswerRepository;


@Service("answerService")
public class AnswerServiceImpl implements AnswerService {

	@Autowired AnswerRepository repository;
	
	@Override
	public Answer getAnswerById(long id) {
		// TODO Auto-generated method stub
		AnswerEntity entity = repository.findById(id).get();
		Answer answer = entity.buildDomain();
		
		return answer;
	}

	@Override
	public List<Answer> getAnswers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAnswer(Answer answer) {
		// TODO Auto-generated method stub
		AnswerEntity entity = new AnswerEntity();
		entity.buildEntity(answer);
		repository.save(entity);
	}

	@Override
	public void updateAnswer(Answer answer) {
		// TODO Auto-generated method stub
		AnswerEntity entity = new AnswerEntity();
		entity.buildEntity(answer);
		repository.save(entity);
	}

	@Override
	public void deleteAnswer(Answer answer) {
		// TODO Auto-generated method stub
		AnswerEntity entity = new AnswerEntity();
		entity.buildEntity(answer);
		repository.delete(entity);
	}

}
