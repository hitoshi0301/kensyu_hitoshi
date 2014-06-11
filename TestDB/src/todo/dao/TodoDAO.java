package todo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import chapter10.TodoValueObject;

public class TodoDAO extends CommonMySQLDAO{

	public List<TodoValueObject> todoList() throws Exception {
		List<TodoValueObject> returnList = new ArrayList<TodoValueObject>();


		String sql = "SELECT id,title,task,limitdate,lastupdate,userid,label,td.status,filename FROM todo_list td LEFT JOIN status_list stts ON stts.status = td.status";

		//プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);

		//SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		//検索結果の行数分フェッチを行い、取得結果をValueObjectへ格納する
		while (rs.next()) {
			TodoValueObject vo = new TodoValueObject();

			//クエリー結果をvoへ格納（あらかじめクエリー結果とvoの変数名は一致させている）
			vo.setId(rs.getInt("id"));
			vo.setTitle(rs.getString("title"));
			vo.setTask(rs.getString("task"));
			vo.setLimitdate(rs.getTimestamp("limitdate"));
			vo.setLastupdate(rs.getTimestamp("lastupdate"));
			vo.setUserid(rs.getString("userid"));
			vo.setLabel(rs.getString("label"));
			vo.setFilename(rs.getString("filename"));

			returnList.add(vo);
		}
		return returnList;
	}
	public TodoValueObject detail(int id) throws Exception{
		TodoValueObject vo = new TodoValueObject();


		String sql = "SELECT id,title,task,limitdate,lastupdate,userid,label,filename,td.status FROM todo_list td LEFT JOIN status_list stts ON stts.status = td.status where id = ?";

		//プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);

		//SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		//検索結果の行数分フェッチを行い、取得結果をValueObjectへ格納する
		while (rs.next()) {


			//クエリー結果をvoへ格納（あらかじめクエリー結果とvoの変数名は一致させている）
			vo.setId(rs.getInt("id"));
			vo.setTitle(rs.getString("title"));
			vo.setTask(rs.getString("task"));
			vo.setLimitdate(rs.getTimestamp("limitdate"));
			vo.setLastupdate(rs.getTimestamp("lastupdate"));
			vo.setUserid(rs.getString("userid"));
			vo.setLabel(rs.getString("label"));
			vo.setStatus(rs.getInt("status"));
			vo.setFilename(rs.getString("filename"));



		}
		return vo;
	}


	public int registerInsert(TodoValueObject vo) throws Exception{
		String sql = "INSERT INTO todo_list(title,task,limitdate,lastupdate,userid,status) VALUES (?,?,?,now(),?,0)";

		int result = 0;
		//プリペアステートメントを取得し、実行SQLを渡す
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vo.getTitle());
			statement.setString(2, vo.getTask());
			statement.setString(3, vo.getInputLimit());
			statement.setString(4, vo.getUserid());

			result = statement.executeUpdate();

			//コミットをおこなう
			connection.commit();
		} catch (Exception  e){
			//ロールバックを行い、スローした例外はDAOから脱出する
			connection.rollback();
			throw e;
		}
		return result;
	}

	public int registerUpdate(TodoValueObject vo) throws Exception {
		String sql = "UPDATE todo_list SET title = ?, task = ?, limitdate = ?, userid = ?,status = ? WHERE id = ?";

		//プリペアステートメントを取得し、実行SQLを渡す。
		int result = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vo.getTitle());
			statement.setString(2, vo.getTask());
			statement.setString(3, vo.getInputLimit());
			statement.setString(4, vo.getUserid());
			statement.setInt(5, vo.getStatus());
			statement.setInt(6, vo.getId());

			result = statement.executeUpdate();

			//コミットを行う
			connection.commit();
		} catch (Exception e){
			connection.rollback();
			throw e;
		}
		return result;
	}

	public int delete(int id) throws Exception {
		String sql = "DELETE FROM todo_list where id = ?";

		//sqlを実行してその結果を取得する。
		int result = 0;
		try {
			//プリペアステートメントを取得し、実行sqlを渡す
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			result = statement.executeUpdate();

			//コミットを行う
			connection.commit();
		}catch (Exception e) {
			connection.rollback();
			throw e;
		}
		return result;
	}

	public int updateUploadInfo(TodoValueObject vo) throws Exception {
		String sql = "UPDATE todo_list SET filename = ? WHERE id = ?";
		int result = 0;
		//プリペアステートメントを取得し、実行sqlを渡す
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vo.getFilename());
			statement.setInt(2, vo.getId());

			//登録を行う
			result = statement.executeUpdate();

			//コミットを行う
			connection.commit();
		}catch (Exception e) {
			connection.rollback();
			throw e;
		}
		return result;
	}
}

