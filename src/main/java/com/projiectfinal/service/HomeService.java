package com.projiectfinal.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projiectfinal.dao.DBUtil;
import com.projiectfinal.model.Note;
import com.projiectfinal.model.NoteDetail;
import com.projiectfinal.model.User;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeService {
    public static User getCheckUser(String username, String password) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBUtil.getCon();

            String sql = "select * from t_user where user_name=? and user_pass=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            rs.next();
            User user = new User();
            user.setUserName(rs.getString("user_name"));
            user.setUserId(rs.getInt("user_id"));
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(rs, pstmt, con);
        }
    }

    //新用户注册
    public static int regUser(Map<String, String[]> map) {
        PreparedStatement pstmt = null;
        Connection con = null;

        try {
            con = DBUtil.getCon();

            String sql = "insert into t_user (user_name,user_pass,user_age,user_sex,user_email,user_area) values (?,?,?,?,?,?)";


            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, map.get("userName")[0]);
            pstmt.setString(2, map.get("userPass")[0]);

            if (map.get("userAge")[0] != "") {
                pstmt.setInt(3, Integer.parseInt(map.get("userAge")[0]));
            } else {
                pstmt.setString(3, null);
            }

            pstmt.setInt(4, Integer.parseInt(map.get("userSex")[0]));
            pstmt.setString(5, map.get("userEmail")[0]);
            pstmt.setString(6, map.get("userArea")[0]);

            int i = pstmt.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            DBUtil.close(null, pstmt, con);
        }

    }





    public static NoteDetail getNoteDetail(int noteId) throws IOException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;


        NoteDetail noteDetail = null;
        // 1.1获取redis客户端
        Jedis jedis = new Jedis();
        boolean exist = false;
        boolean jedisErr = false;
        try {
            exist = jedis.exists("getNoteDetail");
        } catch (Exception e) {
            e.printStackTrace();
            jedisErr = true;
        }

        if (!exist) {
            System.out.println("从数据库中查询getNoteDetail");
            try {
                con = DBUtil.getCon();

                String sql_getALL = "select * from t_note where note_id = ?";
                String sql_getUserName = "select * from t_user where user_id = (select user_id from t_note where note_id = ?)";

                pstmt = con.prepareStatement(sql_getALL);
                pstmt.setInt(1, noteId);
                rs = pstmt.executeQuery();
                rs.next();
                noteDetail = new NoteDetail();
                noteDetail.setNoteCode(rs.getString("upload_code"));
                noteDetail.setUploadDate(rs.getDate("upload_date"));
                noteDetail.setNoteName(rs.getString("note_name"));
                noteDetail.setPicPath(rs.getString("pic_Path"));
                noteDetail.setNoteId(noteId);



                pstmt = con.prepareStatement(sql_getUserName);
                pstmt.setInt(1, noteId);
                rs = pstmt.executeQuery();
                rs.next();
                noteDetail.setUploadUser(rs.getString("user_name"));
                noteDetail.setDstUserId(rs.getInt("user_id"));
                noteDetail.setUserEmail(rs.getString("user_email"));

                if (!jedisErr) {
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonValue = mapper.writeValueAsString(noteDetail);
                    jedis.set("getNoteDetail", jsonValue);
                    jedis.expire("getNoteDetail",1);
                }
                return noteDetail;

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
            System.out.println("从redis中查询getNoteDetail");
            String json = jedis.get("getNoteDetail");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, new TypeReference<NoteDetail>() {
            });
        }
    }




   /* public static NoteDetail getNoteDetail(int noteId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        NoteDetail noteDetail = new NoteDetail();
        try {
            con = DBUtil.getCon();

            String sql_getALL = "select * from t_note where note_id = ?";
            String sql_getUserName = "select * from t_user where user_id = (select user_id from t_note where note_id = ?)";

            pstmt = con.prepareStatement(sql_getALL);
            pstmt.setInt(1, noteId);
            rs = pstmt.executeQuery();
            rs.next();
            noteDetail.setNoteCode(rs.getString("upload_code"));
            noteDetail.setUploadDate(rs.getDate("upload_date"));
            noteDetail.setNoteName(rs.getString("note_name"));
            noteDetail.setPicPath(rs.getString("pic_Path"));
            noteDetail.setNoteId(noteId);



            pstmt = con.prepareStatement(sql_getUserName);
            pstmt.setInt(1, noteId);
            rs = pstmt.executeQuery();
            rs.next();
            noteDetail.setUploadUser(rs.getString("user_name"));
            noteDetail.setDstUserId(rs.getInt("user_id"));
            noteDetail.setUserEmail(rs.getString("user_email"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(rs, pstmt, con);
        }
        return noteDetail;
    }*/






    public static String getUserName(int userId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String username;
        try {
            con = DBUtil.getCon();

            String sql_getUserName = "select user_name from t_user where user_id = ?";
            pstmt = con.prepareStatement(sql_getUserName);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            rs.next();
            username = rs.getString("user_name");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(rs, pstmt, con);
        }
        return username;
    }


}
