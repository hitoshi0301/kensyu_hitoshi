package todo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dao.TodoDAO;
import chapter10.TodoValueObject;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/todo/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
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

		//リクエストパラメータから選択したタスクidを取得する
		String paramId = request.getParameter("id");

		//Stringからintへ変換し、daoで処理をおこなう。更新対象のタスク1件しゅとくする
		TodoValueObject vo;
		try {
			dao.getConnection();
			//intへ変換*NumberFormatExceptionが発生する可能性あり。チェック対象
			int id = Integer.parseInt(paramId);

			//タスク詳細結果を取得
			vo =  dao.detail(id);
		}catch (Exception e) {
			throw new ServletException(e);
		}finally {
			//DAOの処理が完了したら接続を閉じる
			dao.closeConnection();
		}

		//タスク1件のvoをリクエスト属性へバインド
		request.setAttribute("vo", vo);

		//画面を返す
		//検索一覧をひょうじする
		RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//post送信時もGETと同じ処理をおこなう
		doGet(request,response);
	}

}
