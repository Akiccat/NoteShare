package com.projiectfinal.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projiectfinal.dao.DBUtil;
import com.projiectfinal.model.Note;
import com.projiectfinal.model.areaChart;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChooseService {


    public static List<Note> getNoteByName(String name) throws IOException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;


        List<Note> list = null;
        // 1.1获取redis客户端
        Jedis jedis = new Jedis();
        boolean exist = false;
        boolean jedisErr = false;
        try {
            exist = jedis.exists("getNoteByName");
        } catch (Exception e) {
            e.printStackTrace();
            jedisErr = true;
        }

        if (!exist) {
            System.out.println("从数据库中查询getNoteByName");
            try {
                con = DBUtil.getCon();

                String sql = "SELECT * FROM t_note WHERE subject_id in (SELECT subject_id FROM t_subject WHERE name = ?)";

                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, name);
                rs = pstmt.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    Note note = new Note();
                    note.setNoteId(rs.getInt("note_id"));
                    note.setIsFree(rs.getInt("is_Free"));
                    note.setSubjectId(rs.getInt("subject_id"));
                    note.setPicPath(rs.getString("pic_Path"));
                    note.setUserId(rs.getInt("user_id"));
                    note.setNoteName(rs.getString("note_name"));
                    list.add(note);
                }
                if (!jedisErr) {
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonValue = mapper.writeValueAsString(list);
                    jedis.set("getNoteByName", jsonValue);
                    jedis.expire("getNoteByName",1);
                }
                return list;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (!jedisErr) {
                    jedis.close();
                }
                DBUtil.close(rs, pstmt, con);
            }
        } else {
            System.out.println("从redis中查询getNoteByName");
            String json = jedis.get("getNoteByName");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, new TypeReference<List<Note>>() {
            });
        }
    }



   /* public static List<Note> getNoteByName(String name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBUtil.getCon();

            String sql = "SELECT * FROM t_note WHERE subject_id in (SELECT subject_id FROM t_subject WHERE name = ?)";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            List<Note> list = new ArrayList<>();
            while (rs.next()) {
                Note note = new Note();
                note.setNoteId(rs.getInt("note_id"));
                note.setIsFree(rs.getInt("is_Free"));
                note.setSubjectId(rs.getInt("subject_id"));
                note.setPicPath(rs.getString("pic_Path"));
                note.setUserId(rs.getInt("user_id"));
                note.setNoteName(rs.getString("note_name"));
                list.add(note);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(rs, pstmt, con);
        }
    }*/





    public static List<Note> getNoteByNoteName(String name) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBUtil.getCon();
            String sql = "select * from t_note where note_name LIKE \"%\"?\"%\"";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            List<Note> list = new ArrayList<>();
            while (rs.next()) {
                Note note = new Note();
                note.setNoteId(rs.getInt("note_id"));
                note.setIsFree(rs.getInt("is_Free"));
                note.setSubjectId(rs.getInt("subject_id"));
                note.setPicPath(rs.getString("pic_Path"));
                note.setUserId(rs.getInt("user_id"));
                note.setNoteName(rs.getString("note_name"));
                list.add(note);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(rs, pstmt, con);
        }
    }

    public static void delNote(String noteCode) {
        PreparedStatement pstmt = null;
        Connection con = null;
        String sql1 = "delete from t_note where upload_code=?";
        try {
            con = DBUtil.getCon();
            pstmt = con.prepareStatement(sql1);
            pstmt.setString(1, noteCode);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pstmt, con);
        }
    }
}
