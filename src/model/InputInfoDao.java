package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InputInfoDao {




	//JDBCドライバの相対パス
	String Driver_name = "com.mysql.cj.jdbc.Driver";


	//接続さきのデータベース
	String JDBC_URL    = "jdbc:mysql://localhost/todolist?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false";


	//接続先のユーザー
	String USER_ID     = "hei";

	//接続先のパスワード
	String USER_PASS   = "heiheihei";




	public boolean doInsert (InputInfoDto dto) {


		//JDBCドライバとの接続
		try {
			Class.forName(Driver_name);
			System.out.println("ドライバのロードに成功してます。");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ドライバのロードに失敗してます。");
		}

		//mysqlの接続に必要な変数
		Connection con = null; //mysqlとの接続

		PreparedStatement ps = null; //sqlへのデータの送信


		boolean istrue = true; //登録情報をデータベースに格納できたか確認


		try {

			//JDBCドライバとドライバマネージャーの接続の確認

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			System.out.println("sqlとの接続成功");

			//トランザクションの開始(オートこみっとのoff)
			con.setAutoCommit(false);

			//Insert文の作成
			StringBuffer buf = new StringBuffer();

			buf.append(" INSERT INTO 												");
			buf.append("    todolist.USER_INFO  (USER_ID, NAME, PASSWORD	)		");
			buf.append("    VALUES (												");
			buf.append(" 			? 				,								");
			buf.append("			?				,								");
			buf.append("			?				)		;						");

			//sql情報をpsに格納して、送信
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setInt	(1, dto.getId()		); 	//id情報を格納
			ps.setString(2, dto.getName()	);	//名前を格納
			ps.setString(3, dto.getPass	()	);	//パスワードを格納

			//sql文の実行
			ps.executeUpdate();




		} catch (SQLException e) {
			e.printStackTrace();

			//sql実行の失敗
			istrue = false;
		} finally {
			//sql文が正しく実行できた場合、トランザクションの終了と共にコミットさせる。
			if (istrue) {
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			} else {//sql文の実行が失敗した場合、ロールバック（前の状態に戻す）
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			//ps,conの接続を解除。小さい方から解除
			//psの解除
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

			//conの解除
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return istrue;











	}



	public InputInfoDto doSelect (String logname, String logpass) {

		//JDBCドライバをロード

		try {
			Class.forName(Driver_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//sqlとの接続に必要な変数
		Connection 			con = null	;
		PreparedStatement 	ps 	= null	;
		ResultSet			rs 	= null	;

		//return文で返す
		InputInfoDto dto = new InputInfoDto();

		try {
			//sqlと接続
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();

			//sql文の接続
			buf.append(" SELECT					");
			buf.append("	USER_ID,			");
			buf.append("	NAME,				");
			buf.append("	PASSWORD			");
			buf.append(" FROM					");
			buf.append("	todolist.USER_INFO	");
			buf.append(" WHERE					");
			buf.append("	NAME = ? AND		");
			buf.append("	PASSWORD = ?	;	");

			ps = con.prepareStatement(buf.toString());

			//?に差込
			ps.setString(1,logname);
			ps.setString(2, logpass);

			//sqlの実行
			rs = ps.executeQuery();

			if (rs.next()) {
				dto.setId	(rs.getInt("USER_ID")		);
				dto.setName	(rs.getString("NAME")		);
				dto.setPass	(rs.getString("PASSWORD")	);
			}




		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			//sqlの接続解除
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

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}


		}

		return dto;



	}
















}
