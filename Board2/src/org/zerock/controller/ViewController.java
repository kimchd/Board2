package org.zerock.controller;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class ViewController
 */
@WebServlet("/view")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardDAO dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		dao = (BoardDAO) config.getServletContext().getAttribute("BoardDAO");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		System.out.println(bno);
		List<BoardVO> list = new ArrayList<>();

		try {
			list = dao.contentView(bno);
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/board2/viewcontent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		BoardVO vo = new BoardVO();
		
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setParent(Integer.parseInt(request.getParameter("parent")));
		vo.setGno(Integer.parseInt(request.getParameter("gno")));
		vo.setGord(Integer.parseInt(request.getParameter("gord")));
		vo.setWriter(request.getParameter("writer"));
		
		int page = Integer.parseInt(request.getParameter("pageNum"));

		try {
			dao.reply(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/list?pageNum="+page);
	}

}
