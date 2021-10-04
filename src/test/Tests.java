package test;


import java.util.Random;

import javax.swing.JFrame;

public class Tests {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ



		test1();

		JFrame frame=new JFrame("test");
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(400,400);
	     frame.setVisible(true);

	}





	public static int test1 () {


		//変数
		int 	number 				= 0; //id番号
		String 	Stnumber 			= ""; //文字列でのid番号
		Random 	repeat_rundom 		= new Random()					; //何回繰り返すかを指定するためのrandomインスタンス
		int 	repeat 				= repeat_rundom.nextInt(5) + 3	; //何回繰り返すかを指定

		for (int i = 0; i < repeat ; i ++) {

			String[] Snumber = {"1","2","3","4","5","6","7","8","9"};

			Random random = new Random();

			String snumber = Snumber[random.nextInt(9)];


			Stnumber += snumber;



		}

		number = Integer.parseInt(Stnumber);
		System.out.println(number);

		return number;




	}




}
