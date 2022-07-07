package com.projiectfinal.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projiectfinal.dao.DBUtil;
import com.projiectfinal.model.areaChart;
import com.projiectfinal.model.mapChart;
import com.projiectfinal.model.pieChart;
import com.projiectfinal.model.toneChart;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChartService {


    public static List<mapChart> getMapData() throws IOException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;


        List<mapChart> list = null;
        // 1.1获取redis客户端
        Jedis jedis = new Jedis();
        // jedis.del("partners");
        boolean exist = false;
        boolean jedisErr = false;
        try {
            exist = jedis.exists("mapData");
        } catch (Exception e) {
            e.printStackTrace();
            jedisErr = true;
        }

        if (!exist) {
            System.out.println("从数据库中查询mapData");
            try {
                con = DBUtil.getCon();

                String sql = "SELECT user_area area,COUNT(user_area) value from t_user GROUP BY user_area";

                pstmt = con.prepareStatement(sql);
                rs = pstmt.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    mapChart mapChart = new mapChart();
                    mapChart.setName(rs.getString("area"));
                    mapChart.setValue(rs.getInt("value"));
                    list.add(mapChart);
                }

                if (!jedisErr) {
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonValue = mapper.writeValueAsString(list);
                    jedis.set("mapData", jsonValue);
                    jedis.expire("mapData",3600);
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
            System.out.println("从redis中查询mapData");
            String json = jedis.get("mapData");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, new TypeReference<List<mapChart>>() {
            });
        }
    }


    public static List<pieChart> getPieData() throws IOException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;


        List<pieChart> list = null;
        // 1.1获取redis客户端
        Jedis jedis = new Jedis();
        // jedis.del("partners");
        boolean exist = false;
        boolean jedisErr = false;
        try {
            exist = jedis.exists("pieData");
        } catch (Exception e) {
            e.printStackTrace();
            jedisErr = true;
        }

        if (!exist) {
            System.out.println("从数据库中查询pieData");
            try {
                con = DBUtil.getCon();

                String sql = "SELECT user_sex sex,COUNT(user_sex) value from t_user GROUP BY user_sex";

                pstmt = con.prepareStatement(sql);
                rs = pstmt.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    pieChart pie = new pieChart();
                    if (rs.getInt("sex") == 0) {
                        pie.setName("女");
                        pie.setValue(rs.getInt("value"));
                    } else {
                        pie.setName("男");
                        pie.setValue(rs.getInt("value"));
                    }
                    list.add(pie);
                }
                if (!jedisErr) {
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonValue = mapper.writeValueAsString(list);
                    jedis.set("pieData", jsonValue);
                    jedis.expire("pieData",3600);
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
            System.out.println("从redis中查询pieData");
            String json = jedis.get("pieData");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, new TypeReference<List<pieChart>>() {
            });
        }
    }






    /*public static List<pieChart> getPieData() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBUtil.getCon();

            String sql = "SELECT user_sex sex,COUNT(user_sex) value from t_user GROUP BY user_sex";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<pieChart> list = new ArrayList<>();
            while (rs.next()) {
                pieChart pie = new pieChart();
                if (rs.getInt("sex") == 0) {
                    pie.setName("女");
                    pie.setValue(rs.getInt("value"));
                } else {
                    pie.setName("男");
                    pie.setValue(rs.getInt("value"));
                }
                list.add(pie);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(rs, pstmt, con);
        }
    }*/

    public static List<toneChart> getNoteUpdateCount() throws IOException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;


        List<toneChart> list = null;
        // 1.1获取redis客户端
        Jedis jedis = new Jedis();
        // jedis.del("partners");
        boolean exist = false;
        boolean jedisErr = false;
        try {
            exist = jedis.exists("toneData");
        } catch (Exception e) {
            e.printStackTrace();
            jedisErr = true;
        }

        if (!exist) {
            System.out.println("从数据库中查询toneData");
            try {
                con = DBUtil.getCon();

                String sql = "SELECT user_id name,COUNT(user_id) value from t_note  GROUP BY user_id";
                pstmt = con.prepareStatement(sql);
                rs = pstmt.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    toneChart toneChart = new toneChart();
                    toneChart.setName(HomeService.getUserName(rs.getInt("name")));
                    toneChart.setValue(rs.getInt("value"));
                    list.add(toneChart);
                }
                if (!jedisErr) {
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonValue = mapper.writeValueAsString(list);
                    jedis.set("toneData", jsonValue);
                    jedis.expire("toneData",6);
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
            System.out.println("从redis中查询toneData");
            String json = jedis.get("toneData");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, new TypeReference<List<toneChart>>() {
            });
        }
    }

    /*public static List<toneChart> getNoteUpdateCount() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBUtil.getCon();

            String sql = "SELECT user_id name,COUNT(user_id) value from t_note  GROUP BY user_id";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<toneChart> list = new ArrayList<>();
            while (rs.next()) {
                toneChart toneChart = new toneChart();
                toneChart.setName(HomeService.getUserName(rs.getInt("name")));
                toneChart.setValue(rs.getInt("value"));
                list.add(toneChart);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(rs, pstmt, con);
        }
    }*/

    public static List<areaChart> getAreaData() throws IOException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;


        List<areaChart> list = null;
        // 1.1获取redis客户端
        Jedis jedis = new Jedis();
        // jedis.del("partners");
        boolean exist = false;
        boolean jedisErr = false;
        try {
            exist = jedis.exists("areaData");
        } catch (Exception e) {
            e.printStackTrace();
            jedisErr = true;
        }

        if (!exist) {
            System.out.println("从数据库中查询areaData");
            try {
                con = DBUtil.getCon();

                String sql = "SELECT subject_id name,COUNT(subject_id) value from t_note GROUP BY subject_id";

                pstmt = con.prepareStatement(sql);
                rs = pstmt.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    areaChart areaChart = new areaChart();
                    if (rs.getInt("name") == 1) {
                        areaChart.setName("Code");
                        areaChart.setValue(rs.getInt("value"));
                    } else if (rs.getInt("name") == 2) {
                        areaChart.setName("Math");
                        areaChart.setValue(rs.getInt("value"));
                    } else if (rs.getInt("name") == 3) {
                        areaChart.setName("Paint");
                        areaChart.setValue(rs.getInt("value"));
                    }
                    list.add(areaChart);
                }
                if (!jedisErr) {
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonValue = mapper.writeValueAsString(list);
                    jedis.set("areaData", jsonValue);
                    jedis.expire("areaData",6);
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
            System.out.println("从redis中查询areaData");
            String json = jedis.get("areaData");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, new TypeReference<List<areaChart>>() {
            });
        }
    }
}

/*    public static List<areaChart> getAreaData() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBUtil.getCon();

            String sql = "SELECT subject_id name,COUNT(subject_id) value from t_note GROUP BY subject_id";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<areaChart> list = new ArrayList<>();
            while (rs.next()) {
                areaChart areaChart = new areaChart();
                if (rs.getInt("name") == 1) {
                    areaChart.setName("Code");
                    areaChart.setValue(rs.getInt("value"));
                } else if (rs.getInt("name") == 2) {
                    areaChart.setName("Math");
                    areaChart.setValue(rs.getInt("value"));
                } else if (rs.getInt("name") == 3) {
                    areaChart.setName("Paint");
                    areaChart.setValue(rs.getInt("value"));
                }
                list.add(areaChart);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(rs, pstmt, con);
        }
    }
}*/
