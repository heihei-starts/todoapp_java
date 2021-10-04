package model;

public class DeleteBL {

	//TodoListDaoから、isDeleteを呼び出す。
	public boolean callDelete (int idNumber, String todoTask) {
		boolean succese = true;

		TodoListDao dao = new TodoListDao();
		succese = dao.isDelete(idNumber, todoTask);

		return succese;
	}

}
