package com.virgingames.bingo;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;

/**
 * Created by Archita Patel
 */
public class TestBase {

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI = "https://www.virgingames.com/bingo/GetBingoLobbyFeed.do";
        RestAssured.registerParser("text/plain", Parser.JSON);
    }
}
