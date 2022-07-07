package com.projiectfinal.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projiectfinal.dao.DBUtil;
import com.projiectfinal.model.Note;
import com.projiectfinal.model.NoteDetail;
import com.projiectfinal.model.PayDetail;
import com.projiectfinal.model.User;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

public class PayService {
        public static PayDetail uploadPay(PayDetail PD) {
            PreparedStatement pstmt = null;
            Connection con = null;

            String sql = "insert into t_pay values(?,?,?,?,?,?,?,?,?)";
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
            String payCode = str + s;
            PD.setPayCode(payCode);
            PayDetail payDetail = new PayDetail();
            try {
                con = DBUtil.getCon();
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, PD.getDstUserid());
                pstmt.setInt(2, PD.getSrcUserid());
                pstmt.setString(3, PD.getPayCode());
                pstmt.setString(4, PD.getWord());
                pstmt.setString(5, PD.getNoteCode());
                pstmt.setInt(6, PD.getNoteId());
                pstmt.setDate(7, d1);
                pstmt.setInt(8, PD.getPrice());
                pstmt.setInt(9, 2);
                pstmt.executeUpdate();
                payDetail = getPay(payCode, d1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(null, pstmt, con);
            }

            return payDetail;
        }



    public static PayDetail getPay(String payCode,java.sql.Date date) throws IOException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;


        PayDetail payDetail = null;
        // 1.1获取redis客户端
        Jedis jedis = new Jedis();
        boolean exist = false;
        boolean jedisErr = false;
        try {
            exist = jedis.exists("getPay");
        } catch (Exception e) {
            e.printStackTrace();
            jedisErr = true;
        }

        if (!exist) {
            System.out.println("从数据库中查询getPay");
            try {
                con = DBUtil.getCon();

                String sql2 = "select * from t_pay where pay_code = ?";

                pstmt = con.prepareStatement(sql2);
                pstmt.setString(1, payCode);
                rs = pstmt.executeQuery();
                rs.next();
                payDetail = new PayDetail();
                payDetail.setDstUserid(rs.getInt("dst_userid"));
                payDetail.setSrcUserid(rs.getInt("src_userid"));
                payDetail.setWord(rs.getString("word"));
                payDetail.setNoteCode(rs.getString("note_code"));
                payDetail.setNoteId(rs.getInt("note_id"));
                payDetail.setPayCode(payCode);
                payDetail.setPrice(rs.getInt("price"));
                payDetail.setDate(date);

                if (!jedisErr) {
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonValue = mapper.writeValueAsString(payDetail);
                    /*jedis.set("getPay", jsonValue);*/
                    /*jedis.expire("getNoteDetail",3600);*/
                }
                return payDetail;

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
            System.out.println("从redis中查询getPay");
            String json = jedis.get("getPay");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, new TypeReference<PayDetail>() {
            });
        }
    }



   /* public static PayDetail getPay(String payCode,java.sql.Date date) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBUtil.getCon();

            String sql2 = "select * from t_pay where pay_code = ?";

            pstmt = con.prepareStatement(sql2);
            pstmt.setString(1, payCode);
            rs = pstmt.executeQuery();
            rs.next();
            PayDetail payDetail = new PayDetail();
            payDetail.setDstUserid(rs.getInt("dst_userid"));
            payDetail.setSrcUserid(rs.getInt("src_userid"));
            payDetail.setWord(rs.getString("word"));
            payDetail.setNoteCode(rs.getString("note_code"));
            payDetail.setNoteId(rs.getInt("note_id"));
            payDetail.setPayCode(payCode);
            payDetail.setPrice(rs.getInt("price"));
            payDetail.setDate(date);
            return payDetail;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(rs, pstmt, con);
        }
    }*/

    public static int UpdatePayState(String payCode) {
        PreparedStatement pstmt = null;
        Connection con = null;

        try {
            con = DBUtil.getCon();

            String sql = "update t_pay set state = 1 where pay_code = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, payCode);
            int i = pstmt.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            DBUtil.close(null, pstmt, con);
        }
    }

    public static List<PayDetail> getWord(String noteCode) throws IOException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;


        List<PayDetail> list = null;
        // 1.1获取redis客户端
        Jedis jedis = new Jedis();
        boolean exist = false;
        boolean jedisErr = false;
        try {
            exist = jedis.exists("getWord");
        } catch (Exception e) {
            e.printStackTrace();
            jedisErr = true;
        }

        if (!exist) {
            System.out.println("从数据库中查询getWord");
            try {
                con = DBUtil.getCon();

                String sql2 = "select * from t_pay where note_code = ? and state = 1";

                pstmt = con.prepareStatement(sql2);
                pstmt.setString(1, noteCode);
                rs = pstmt.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    PayDetail payDetail = new PayDetail();
                    payDetail.setDstUserid(rs.getInt("dst_userid"));
                    payDetail.setSrcUserid(rs.getInt("src_userid"));
                    payDetail.setWord(rs.getString("word"));
                    payDetail.setNoteCode(rs.getString("note_code"));
                    payDetail.setNoteId(rs.getInt("note_id"));
                    payDetail.setPayCode(rs.getString("pay_code"));
                    payDetail.setPrice(rs.getInt("price"));
                    payDetail.setDate(rs.getDate("pay_date"));
                    payDetail.setSrcUsername(getScrUser(rs.getInt("src_userid")));
                    list.add(payDetail);
                }
                if (!jedisErr) {
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonValue = mapper.writeValueAsString(list);
                    /*jedis.set("getWord", jsonValue);
                    jedis.expire("getWord",1);*/
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
            System.out.println("从redis中查询getWord");
            String json = jedis.get("getWord");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, new TypeReference<List<PayDetail>>() {
            });
        }
    }


    /*public static List<PayDetail> getWord(String noteCode) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBUtil.getCon();

            String sql2 = "select * from t_pay where note_code = ? and state = 1";

            pstmt = con.prepareStatement(sql2);
            pstmt.setString(1, noteCode);
            rs = pstmt.executeQuery();
            List<PayDetail> list = new ArrayList<>();
            while (rs.next()) {
                PayDetail payDetail = new PayDetail();
                payDetail.setDstUserid(rs.getInt("dst_userid"));
                payDetail.setSrcUserid(rs.getInt("src_userid"));
                payDetail.setWord(rs.getString("word"));
                payDetail.setNoteCode(rs.getString("note_code"));
                payDetail.setNoteId(rs.getInt("note_id"));
                payDetail.setPayCode(rs.getString("pay_code"));
                payDetail.setPrice(rs.getInt("price"));
                payDetail.setDate(rs.getDate("pay_date"));
                payDetail.setSrcUsername(getScrUser(rs.getInt("src_userid")));
                list.add(payDetail);
            }
                return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(rs, pstmt, con);
        }
    }*/

    public static String getScrUser(int srcUserId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBUtil.getCon();

            String sql2 = "select * from t_user where user_id = ?";

            pstmt = con.prepareStatement(sql2);
            pstmt.setInt(1, srcUserId);
            rs = pstmt.executeQuery();
            rs.next();
            String name = rs.getString("user_name");
            return name;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(rs, pstmt, con);
        }
    }
}
