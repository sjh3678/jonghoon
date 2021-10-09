package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Member;
import web.service.face.MemberService;
import web.service.impl.MemberServiceImpl;

@WebServlet("/member/join")
public class MemberJoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberServiceImpl(); 

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/join [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/join [POST]");
		
		Member param = memberService.getJoinMember(req);
	
		memberService.join(param);
		
		resp.sendRedirect("/main");
	}
	
}
