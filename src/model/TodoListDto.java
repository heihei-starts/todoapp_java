package model;

public class TodoListDto {

	//フィールド
	private int	   user_ID		; //
	private String title		; //タイトル
	private String importance	; //重要性


	//ゲッター、セッター


	public String getTitle() {
		return title;
	}
	public int getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}








}




