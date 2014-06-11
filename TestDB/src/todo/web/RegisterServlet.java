package todo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDAO;
import todo.util.SimpleMailSender;
import chapter10.TodoValueObject;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/todo/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//リクエストパラメータを受け取り、更新voに格納する準備をする
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String task = request.getParameter("task");
		String inputLimit = request.getParameter("inputLimit");
		String userid = request.getParameter("userid");
		int status = Integer.parseInt(request.getParameter("status"));

		//voへ格納する。登録されるValueObjectの期限(limit)はvoではinputLimitになる
		TodoValueObject vo = new TodoValueObject();
		vo.setId(id);
		vo.setTitle(title);
		vo.setTask(task);
		vo.setInputLimit(inputLimit);
		vo.setUserid(userid);
		vo.setStatus(status);

		//DAOの取得
		TodoDAO dao = new TodoDAO();

		try {
			dao.getConnection();
			//登録処理をおこなう
			dao.registerInsert(vo);
			setMessage(request, "タスクの新規登録処理が完了しました。");
		}catch (Exception e) {
			throw new ServletException(e);
		}finally {
			//DAOの処理が完了したら接続を閉じる
			dao.closeConnection();
		}

		//一覧画面へフォワードする
		RequestDispatcher rd = request.getRequestDispatcher("/todo/search");
		rd.forward(request,response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//リクエストパラメータを受け取り、更新voに格納する準備をする
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String task = request.getParameter("task");
		String inputLimit = request.getParameter("inputLimit");
		String userid = request.getParameter("userid");
		int status = Integer.parseInt(request.getParameter("status"));

		//voへ格納する。登録されるValueObjectの期限(limit)はvoではinputLimitになる
		TodoValueObject vo = new TodoValueObject();
		vo.setId(id);
		vo.setTitle(title);
		vo.setTask(task);
		vo.setInputLimit(inputLimit);
		vo.setUserid(userid);
		vo.setStatus(status);

		//DAOの取得
		TodoDAO dao = new TodoDAO();
		String message ="";

		try {
			dao.getConnection();
			//更新または登録処理を行う
			//idが０のときは新規登録、id>=1のときは更新
			if(id == 0) {
				dao.registerInsert(vo);
				message = "タスク[ " + id + " ]の新規登録処理が完了しました。";
				setMessage(request,message);
			}else {
				dao.registerUpdate(vo);
				message = "タスク[ " + id + " ]の更新処理が完了しました。";
				setMessage(request,message);
			}
		}catch (Exception e) {
			throw new ServletException(e);
		}finally {
			//DAOの処理が完了したら接続を閉じる
			dao.closeConnection();
		}

		String toAddr = "databasetest1991@yahoo.co.jp";
		String fromAddr = "databasetest1991@yahoo.co.jp";
		String personName = "斉藤仁";
		String subject = "TODO管理アプリケーションからの報告です";
		//完了時にメールを送信する
		SimpleMailSender mail = new SimpleMailSender();
		try {
			mail.sendMessage(toAddr,fromAddr,personName,subject,message);
		}catch (Exception e){
			e.printStackTrace();
		}

		//完了画面を表示させる
		RequestDispatcher rd = request.getRequestDispatcher("/todo/search");
		rd.forward(request, response);


		//一覧画面へフォーワードする
		//RequestDispatcher rd = request.getRequestDispatcher("/todo/search");
		//rd.forward(request, response);
	}
	//jspで表示するメッセージを設定する
	protected void setMessage(HttpServletRequest request, String message) {
		request.setAttribute("message", message);
	}
}
