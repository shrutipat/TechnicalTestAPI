package com.virgingames.bingo;

import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Archita Patel
 */


public class TestSuit extends TestBase {

    //verify that startTime is future timestamp
    @Test
    public void Test01() {

        int size = get().then().extract().path("bingoLobbyInfoResource.streams.size()");
        long[] timestamp = new long[size];
        for (int i = 0; i < size; i++) {
            timestamp[i] = given().when().get().then().extract().path("bingoLobbyInfoResource.streams[" + i + "].startTime");
        }

        // current timestamp
        long time = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            Assert.assertTrue(timestamp[i] > time); //check if timestamp on database is bigger than current time; If it is bigger than it's in future;
            System.out.println(timestamp[i] + " is being compared to " + time);
        }
    }


    //verify that all defaultGameFrequency has value '300000'
    //THIS TEST FAILS AS ONE OF THE 4 'defaultGameFrequency' VALUES IS 'NULL' AND OTHER 3 ARE '300000'
    @Test
    public void Test02() {

        int size = get().then().extract().path("bingoLobbyInfoResource.streams.size()");
        for (int i = 0; i < size; i++) {
            given().when().get().then().body("bingoLobbyInfoResource.streams[" + i + "].defaultGameFrequency", equalTo(300000));
        }
    }
}