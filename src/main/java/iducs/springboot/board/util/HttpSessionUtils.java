package iducs.springboot.board.util;

import iducs.springboot.board.domain.User;

public class HttpSessionUtils {
	public static boolean inNotLogined(User user) {
		//session에 user객체가 존재하면 로그인이 된 상태
		//존재하면 False, null 이면 True
		if(user == null)
			return true;
		return false;
	}

}
