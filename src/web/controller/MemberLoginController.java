package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Member;
import web.service.face.MemberService;
import web.service.impl.MemberServiceImpl;

@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/login [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/login [POST]");
		
		Member member = memberService.getLoginMember(req);
		
		boolean login = memberService.login(member);
		
		if(login) {
			
			member = memberService.info(member);
			
			//세션정보 저장하기
			HttpSession session = req.getSession();
			session.setAttribute("login", true);
			session.setAttribute("userid", member.getUserid());
			session.setAttribute("usernick", member.getUsernick());
		}
		
		resp.sendRedirect("/");
	}
}
