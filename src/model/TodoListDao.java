package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoListDao {


	//JDBCドライバの相対パス
	String Driver_name = "com.mysql.cj.jdbc.Driver";

	//接続さきのデータベース
	String JDBC_URL    = "jdbc:mysql://localhost/todolist?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false";

	//接続先のユーザー
	String USER_ID     = "hei";


	//接続先のパスワード
	String USER_PASS   = "heiheihei";


	public boolean createUsertask (InputInfoDto dto) {


		try {//JDBCドライバのロード
			Class.forName(Driver_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}

		//sqlとの接続に必要な変数
		Connection 			con = null	;
		PreparedStatement 	ps 	= null	; //sqlへのデータの送信

		//returnとして返す変数
		boolean isCreate = true;

		//sqlとの接続
		try {

			//JDBCドライバとドライバマネージャーの接続の確認
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);



			//sql文の作成
			StringBuffer buf = new StringBuffer();

			buf.append(" CREATE TABLE todolist.TODO_FOR");
			buf.append("?");
			buf.append("		(								");
			buf.append("	USER_ID 	INT	,					");
			buf.append("	TEXT 		text,					");
			buf.append("	IMPORTANCE 	text			)		");


			//sqlぶんをセット（送信の準備）
			ps = con.prepareStatement(buf.toString());

			ps.setInt(1, dto.getId());

			//sql文の実行
			ps.executeLargeUpdate();

			System.out.println("こちらのDBも作動してますよ。");

		} catch (SQLException e) {
			e.printStackTrace();

			isCreate = false;
		} finally {

			//sql文が正しく実行できた場合、トランザクションの終了と共にコミットさせる。


			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return isCreate;



	}




	public boolean insert_user_id (InputInfoDto dto) {

		//JDBCドライバの接続
		drive();
		Connection 			con = null	;
		PreparedStatement 	ps 	= null	; //sqlへのデータの送信

		//returnとして返す変数
		boolean isInsert = true;

		try {
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//トランザクションの開始(オートこみっとのoff)
			con.setAutoCommit(false);

			StringBuffer buf = new StringBuffer();

			//insert文入力
			//IDだけ挿入
			buf.append(" INSERT INTO todolist.TODO_FOR");
			buf.append("?");
			buf.append(	" ( USER_ID )	");
			buf.append(" 	VALUES	( ? )		;				");

			//sqlの送信
			ps = con.prepareStatement(buf.toString());
			ps.setInt(1, dto.getId());
			ps.setInt(2, dto.getId());
			//sql文の実行
			ps.executeLargeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			isInsert = false;
		} finally {

			if (isInsert) {
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return isInsert;

	}


	//タスクと重要度をdbに格納
	public boolean doInsert (int idNumber,String todoTask, String importance) {
		//JDBCドライバをロード
		drive();

		//sqlとの接続よう変数
		Connection con 			= null	; //sqlと結びつける
		PreparedStatement ps 	= null	; //sql分を送信
		boolean isInsert		= true	; //insert成功したかどうか確認

		//sqlとの接続やらなんやらスタート
		try {
			//sqlとの接続
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//トランザクションの開始
			con.setAutoCommit(false);

			StringBuffer buf = new StringBuffer();
			//sql分の入力
			buf.append(" INSERT INTO todolist.TODO_FOR");
			buf.append("?");
			buf.append("	(TEXT, IMPORTANCE)			");
			buf.append("	VALUE (						");
			buf.append("    ?			,				");
			buf.append("	?					) ;		");

			ps = con.prepareStatement(buf.toString());
			//?に値を入れていく
			ps.setInt(1		, idNumber		);
			ps.setString(2	, todoTask		);
			ps.setString(3	, importance	);
			//sql文の実行
			ps.executeLargeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			isInsert = false;
		} finally {
			//トランザクションの解除
			if (isInsert) {
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {

				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			//sqlの接続解除

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}


		}

		return isInsert;
	}

	//リストを取り出し、
	public List<TodoListDto> doselect (int idNumber) {

		//JDBC
		drive();

		//DBとの接続に必要な変数
		Connection 			con = null	;
		PreparedStatement 	ps 	= null	;
		ResultSet 			rs 	= null	;

		List<TodoListDto> selectList = new ArrayList<>(); //戻り値に使う。

		try {
			//sqlと接続
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			//SQL分を入力する。
			buf.append(" SELECT	");
			buf.append("	TEXT,				");
			buf.append("	IMPORTANCE			");
			buf.append(" FROM	");
			buf.append("	todolist.TODO_FOR");
			buf.append("?");

			//セット
			ps = con.prepareStatement(buf.toString());
			//？部分の値をここでいれる。
			ps.setInt(1, idNumber);
			//発車,結果を受ける
			rs = ps.executeQuery();

			//rsで結果を受ける
			while (rs.next()) {
				if (rs.getString("TEXT") == null) {
					continue;
				}
				//取得情報を格納するdtoを作成。後に、TodoListDto型のlistに入れ込む
				TodoListDto dto = new TodoListDto();
				dto.setTitle(rs.getString("TEXT")				);
				dto.setImportance(rs.getString("IMPORTANCE")	);
				//リストに格納
				selectList.add(dto);
			}






		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			//sqlの接続の解除
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}



		}

		return selectList;

	}

	public boolean isDelete (int idNumber, String todoTask) {

		//JDBCドライバのロード
		drive();

		//sqlとの接続に必要な変数
		Connection 			con 		= null	;
		PreparedStatement 	ps 			= null	;


		boolean 			isDelete 	= true	; //戻り値として返す値

		try {
			//sqlとの接続
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//sql文の作成
			StringBuffer buf = new StringBuffer();
			buf.append(" DELETE 				");
			buf.append("	FROM				");
			buf.append("	todolist.TODO_FOR");
			buf.append("?");
			buf.append(" WHERE					");
			buf.append("	TEXT = ?		;	");
			
			

			//sql文の送信準備
			ps = con.prepareStatement(buf.toString());

			ps.setInt	(1		, idNumber);
			ps.setString(2		, todoTask);
			//sql文の発信
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			isDelete = false;
		} finally {

			//sql文の接続解除
			//psから
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//con
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}


		}

		return isDelete;
	}





	//JDBCドライバとの接続メソッド
	public void drive () {
		try {
			Class.forName(Driver_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//IDを情報をここまで引っ張ってくる。







}
