package test;

import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import server.*;


class TestJunit {


    @Test
    void packageTest() {
    	String expectedResult = "{ length: 25.0, width: 24.0, depth: 7.3, cat: S }";
    	double len = 25;
    	double wid = 24;
    	double hei = 7.3;
    	String cat = "S";

        Parcel parcel = new server.Parcel();
        parcel.cat = cat;
        parcel.length = len;
        parcel.depth = hei;
        parcel.width = wid;

        assertEquals( expectedResult, parcel.toString());
    }

    @Test
    void dbValidTest() {
    	DBConn conn = new DBConn();

    	String expectedResult = "L";
    	String res = conn.getSize(71.0f);

    	assertEquals( expectedResult, res);
    }

    @Test
    void postSizeTest() {
    	String expectedResult = "{\"length\":25.0,\"width\":24.0,\"depth\":7.3,\"cat\":\"XL\"}";
    	REST meRes = new REST();

    	String res = meRes.size("{ length: 25.0, width: 24.0, depth: 7.3, cat: ' ' }").getEntity().toString();

    	assertEquals(expectedResult, res);
    }


    /*@Test
    void gurtSizeValidTest() {
    	double expectedResult = 87.0f;

    	double len = 25;
    	double wid = 24;
    	double hei = 7;

    	Parcel parcel = new api.Package();
        parcel.length = len;
        parcel.height = hei;
        parcel.width = wid;

        MessageResource meRes = new MessageResource();

        double res = meRes.getGurtSize(parcel);

    	assertEquals(expectedResult, res);
    }*/


    /*@Test
    void gurtSizeInvalidTest() {
    	double expectedResult = 87.0f;

    	double len = -25;
    	double wid = 24;
    	double hei = 7;

    	Parcel parcel = new server.Package();
        parcel.length = len;
        parcel.height = hei;
        parcel.width = wid;

        REST meRes = new REST();

        double res = meRes.size(parcel);

    	assertEquals(expectedResult, res);
    }*/


    @Test
    void dbInvalidTest() {
    	/*
    	 assertThrows(SQLException.class,
                ()->{
                	MysqlCon mysql = new MysqlCon();
                	mysql.getSize(-33.0);
                });
                */

    	DBConn mysql = new DBConn();

    	String expectedResult = "";
    	String res = mysql.getSize(-33.7f);

    	assertEquals( expectedResult, res);
    }

}
