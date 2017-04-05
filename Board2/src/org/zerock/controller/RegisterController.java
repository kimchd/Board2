package org.zerock.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.persistence.BoardDAO;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/write")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		dao = (BoardDAO) config.getServletContext().getAttribute("BoardDAO");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/board2/writer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		try {
			dao.insertList(title, content, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/list");
		

	}

}
