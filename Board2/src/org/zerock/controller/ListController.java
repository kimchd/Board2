package org.zerock.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.persistence.BoardDAO;
import org.zerock.persistence.BoardVO;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/list")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardDAO dao;

	@Override
	public void init(ServletConfig config) throws ServletException {

		dao = (BoardDAO) config.getServletContext().getAttribute("BoardDAO");

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<BoardVO> list = null;

		try {
			list = dao.showList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("list", list);

		request.getRequestDispatcher("/WEB-INF/board2/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
