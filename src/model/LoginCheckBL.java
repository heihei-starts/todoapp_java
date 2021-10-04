package model;

public class LoginCheckBL {


	public InputInfoDto check (String logname, String logpass) {

		//dtoとdaoを生成
		InputInfoDto dto = new InputInfoDto();
		InputInfoDao dao = new InputInfoDao();

		dto = dao.doSelect(logname, logpass);

		return dto;

	}



}
