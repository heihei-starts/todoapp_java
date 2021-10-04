package model;

public class InputInfoBL {




	public boolean call_InputinfoDao (int registerId, String registerName, String registerPass) {


		InputInfoDto dto = new InputInfoDto();
		//ユーザー情報を格納
		dto.setId(registerId);
		dto.setName(registerName);
		dto.setPass(registerPass);

		InputInfoDao dao = new InputInfoDao();
		//登録情報DBに挿入します。
		boolean isSuccess = dao.doInsert(dto);

		//ユーザそれぞれのDBを作成する。
		if (isSuccess) {
			boolean isCreate_userOwn_DB = true;

			TodoListBL bl 		= new TodoListBL();
			//ユーザーごとのテーブル作成
			isCreate_userOwn_DB = bl.todoTaskTableCreate(dto);
			//ユーザーごとのテーブル作成成功すれば、id情報のみを格納
			if (isCreate_userOwn_DB) {
				boolean isInsert_userOwn_DB = true;
				isInsert_userOwn_DB = bl.todotaskTable_id_insert(dto);
				if (!isInsert_userOwn_DB) {
					System.out.println("ID情報格納失敗");
				}
			} else {
				//失敗した場合
				System.out.println("テーブル作成失敗");
			}
		} else {


			System.out.println("ユーザそれぞれのDB作成に失敗しました。。");
		}

		return  isSuccess;
	}


}
