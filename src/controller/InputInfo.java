package controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.InputInfoBL;

/**
 * Servlet implementation class InputInfo
 */
@WebServlet("/InputInfo")
public class InputInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");//文字コードの設定

		request.setCharacterEncoding("UTF-8");

//

		//登録されてるかチェック




		//されていない場合。
		//register.jspからrequest情報を取り出し、daoに持っていく。

		//リクエストパラメーターを取り出す。
		String registerName = request.getParameter("name");
		String registerPass = request.getParameter("password");
		//id情報を取得
		int registerId	= make_ID_Number();



		//InputInfoBLを経由して、DAOを取得
		InputInfoBL logic = new InputInfoBL();

		//↓---------------------------------------------------------↓//
		boolean isSuccess = logic.call_InputinfoDao(registerId, registerName, registerPass);


		//
		if (isSuccess) {
			//ログイン画面に飛ぶ
			response.sendRedirect("htmls/RegisterSuccses.html");
		} else {
			//インサートできなかった場合

			response.sendRedirect("Register");


		}







	}


	//ID番号生成メソッド(String型で、repeat桁の数字を生成した後、int型に型変更してreturn)
	public int make_ID_Number () {

		//変数
		int 	number 				= 0								; //id番号
		String 	Stnumber			= ""							; //文字列でのid番号
		Random 	repeat_rundom 		= new Random()					; //何回繰り返すかを指定するためのrandomインスタンス
		int 	repeat 				= repeat_rundom.nextInt(5) + 3	; //何回繰り返すかを指定

		for (int i = 0; i < repeat; i ++) {

			String[] Snumber = {"1","2","3","4","5","6","7","8","9"};

			Random random = new Random();
			//Snumber配列に格納してる数字をrandomに取り出す。
			String snumber = Snumber[random.nextInt(9)];

			//snumberの値を付け加えていく（Strin型）
			Stnumber += snumber;


		}
		//String型から、int型に変換
		number = Integer.parseInt(Stnumber);

		return number;
	}

	//名前とパスワードのバリデーションチェック用メソッドあとで作る。

}
