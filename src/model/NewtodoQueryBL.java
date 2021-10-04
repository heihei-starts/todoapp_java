package model;

import java.util.ArrayList;
import java.util.List;

public class NewtodoQueryBL {


	//todoDaoのtodoタスクインサート

	public boolean isInsert (int idNumber,String task, String importance) {

		boolean isinsert = true;//returnする

		TodoListDao dao = new TodoListDao();

		isinsert = dao.doInsert(idNumber, task, importance);

		return isinsert;
	}

	//todoリストをクエリする。
	public List<TodoListDto> isSelect (int idNumber) {
		//戻り値として返す、listを生成
		List<TodoListDto> list = new ArrayList<>();

		TodoListDao dao = new TodoListDao();

		list = dao.doselect(idNumber);

		return list;
	}

}
