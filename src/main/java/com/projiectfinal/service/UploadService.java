package com.projiectfinal.service;

import com.projiectfinal.dao.DBUtil;
import com.projiectfinal.model.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class UploadService {
    public static int uploadNote(Note uploadNote) {
        PreparedStatement pstmt = null;
        Connection con = null;
        int noteId = 0;
        String sql = "insert into t_note values(null,?,?,?,?,?,?,?)";
        Date d = new Date();
        java.sql.Date d1 = new java.sql.Date(d.getTime());
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.CHINA);
        String str = sf.format(d);
        Random r = new Random();
        int i = r.nextInt(100);
        String s = "";
        if (i < 10) {
            s = "00" + i;
        } else if (i >= 10 && i <= 99) {
            s = "0" + i;
        }
        String noteCode = str + s;
        uploadNote.setNoteCode(noteCode);
        try {
            con = DBUtil.getCon();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, uploadNote.getIsFree());
            pstmt.setInt(2, uploadNote.getSubjectId());
            pstmt.setString(3, uploadNote.getPicPath());
            pstmt.setInt(4, uploadNote.getUserId());
            pstmt.setString(5, uploadNote.getNoteName());
            pstmt.setDate(6, d1);
            pstmt.setString(7, uploadNote.getNoteCode());
            pstmt.executeUpdate();
            pstmt = con.prepareStatement("select LAST_INSERT_ID()");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                noteId = Integer.parseInt(rs.getString(1));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pstmt, con);
        }

        return noteId;
    }
}
