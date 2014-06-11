package todo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDAO;
import chapter10.TodoValueObject;
/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/todo/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//DAOの取得
		TodoDAO dao = new TodoDAO();

		try {
			dao.getConnection();
			//タスクのリストを一覧で取得し、リクエスト属性へ格納する
			List<TodoValueObject> list = dao.todoList();

			request.setAttribute("todoList",list);

		} catch (Exception e){
			throw new ServletException(e);
		} finally {
			//DAOの処理が完了したら接続を閉じる
			dao.closeConnection();
		}
		//検索一覧を表示する
		RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
		rd.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//POST送信時もGETと同じ処理をおこなう
		doGet(request,response);
	}
}
