package model;

public class TodoListBL {

	public boolean todoTaskTableCreate (InputInfoDto dto) {


		boolean createSuccess = true;	//クリエイトできたか確認


		TodoListDao dao = new TodoListDao();

		createSuccess = dao.createUsertask(dto);

		return createSuccess;



	}

	public boolean todotaskTable_id_insert (InputInfoDto dto) {
		boolean insertSuccess = true;

		TodoListDao dao = new TodoListDao();

		insertSuccess = dao.insert_user_id(dto);

		return insertSuccess;

	}

}
