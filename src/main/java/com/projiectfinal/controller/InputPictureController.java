package com.projiectfinal.controller;


import com.projiectfinal.model.Note;
import com.projiectfinal.service.ChooseService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InputPictureController", value = "/InputPictureController")
public class InputPictureController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subject = request.getParameter("subject");
        List<Note> notes = ChooseService.getNoteByName(subject);
        request.setAttribute("subject",subject);
        request.setAttribute("noteList",notes);
        request.getRequestDispatcher("NotePage.jsp").forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
