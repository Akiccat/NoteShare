package com.projiectfinal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projiectfinal.model.areaChart;
import com.projiectfinal.model.mapChart;
import com.projiectfinal.model.pieChart;
import com.projiectfinal.model.toneChart;
import com.projiectfinal.service.ChartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "mapController", value = {"/mapChart.action","/pieChart.action","/toneChart.action","/areaChart.action"})
public class mapController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (url.equals("/mapChart.action")) {
            List<mapChart> Data = ChartService.getMapData();
            ObjectMapper mapper = new ObjectMapper();
            /*mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);*/
            String mapData = mapper.writeValueAsString(Data);
            /*System.out.println(mapData);*/
            out.print(mapData);
            out.flush();
            out.close();
        }
        if (url.equals("/pieChart.action")) {
            List<pieChart> Data = ChartService.getPieData();
            ObjectMapper mapper = new ObjectMapper();
            /*mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);*/
            String pieData = mapper.writeValueAsString(Data);
            /*System.out.println(pieData);*/
            out.print(pieData);
            out.flush();
            out.close();
        }
        if (url.equals("/toneChart.action")) {
            List<toneChart> Data = ChartService.getNoteUpdateCount();
            ObjectMapper mapper = new ObjectMapper();
           /* mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);*/
            String toneData = mapper.writeValueAsString(Data);
            /*System.out.println(toneData);*/
            out.print(toneData);
            out.flush();
            out.close();
        }
        if (url.equals("/areaChart.action")) {
            List<areaChart> Data = ChartService.getAreaData();
            ObjectMapper mapper = new ObjectMapper();
            /* mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);*/
            String areaData = mapper.writeValueAsString(Data);
            /*System.out.println(areaData);*/
            out.print(areaData);
            out.flush();
            out.close();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
