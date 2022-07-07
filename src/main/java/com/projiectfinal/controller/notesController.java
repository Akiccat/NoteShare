package com.projiectfinal.controller;

import com.projiectfinal.model.Note;
import com.projiectfinal.model.NoteDetail;
import com.projiectfinal.model.PayDetail;
import com.projiectfinal.model.User;
import com.projiectfinal.service.ChooseService;
import com.projiectfinal.service.HomeService;
import com.projiectfinal.service.PayService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "notesController", value = "*.action")
public class notesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath();
        request.setCharacterEncoding("utf-8");
        /*int noteId = Integer.parseInt(request.getParameter("note_id"));*/
        if (url.equals("/notesDetail.action")) {//笔记详情页面
            int noteId = Integer.parseInt(request.getParameter("note_id"));
            String subject = request.getParameter("subject");
            NoteDetail noteDetail = HomeService.getNoteDetail(noteId);
            List<PayDetail> paydetail = PayService.getWord(noteDetail.getNoteCode());
            request.setAttribute("payDetail",paydetail);
            request.getSession().setAttribute("subject",subject);
            request.setAttribute("noteDetail", noteDetail);
            request.getRequestDispatcher("NoteDetail.jsp").forward(request, response);
        } else if (url.equals("/notePay.action")) {
            int noteId = Integer.parseInt(request.getParameter("note_id"));
            User logu = (User) request.getSession().getAttribute("_LOGIN_USER_");
            int dstUserid = Integer.parseInt(request.getParameter("dstUserid"));
            int srcUserid = logu.getUserId();
            String word = request.getParameter("word");
            String noteCode = request.getParameter("noteCode");
            int price = Integer.parseInt(request.getParameter("price"));
            PayDetail payDetail = new PayDetail();
            payDetail.setNoteCode(noteCode);
            payDetail.setNoteId(noteId);
            payDetail.setDstUserid(dstUserid);
            payDetail.setPrice(price);
            payDetail.setWord(word);
            payDetail.setSrcUserid(srcUserid);
            PayDetail payDetail1 = PayService.uploadPay(payDetail);

            String dstUser = HomeService.getUserName(dstUserid);
            String srcUser = HomeService.getUserName(srcUserid);
            request.setAttribute("dstUser",dstUser);
            request.setAttribute("srcUser",srcUser);
            request.setAttribute("payDetail",payDetail1);
            request.getRequestDispatcher("/pay.jsp").forward(request,response);
        }else if (url.equals("/payConfirm.action")){
            String payCode = request.getParameter("payCode");
            int i = PayService.UpdatePayState(payCode);
            if(i!=0){
                request.setAttribute("msg","支付成功");
                request.getRequestDispatcher("/pay.jsp").forward(request,response);
            }
        } else if (url.equals("/searchNote.action")) {
            String noteName = request.getParameter("noteName");
            List<Note> notes = ChooseService.getNoteByNoteName(noteName);
            request.setAttribute("noteList",notes);
            request.setAttribute("subject","Search");
            request.getRequestDispatcher("NotePage.jsp").forward(request,response);
        } else if (url.equals("/delNote.action")) {
            String noteName = request.getParameter("noteCode");
            ChooseService.delNote(noteName);
            request.getRequestDispatcher("/Choose.jsp").forward(request,response);
        }
    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
