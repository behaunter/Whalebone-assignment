package com.whalebone.apiTests.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import сom.whalebone.apiTests.dto.GetResponseRootDto;
import сom.whalebone.apiTests.dto.GetResponseTeamsDto;

import java.util.ArrayList;
import java.util.List;


import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ApiTests extends BaseMethods {

//- verify the response returned expected count of teams (32 in total)
    @Test
    public void checkCountOfTeams() {
        GetResponseRootDto response = getResponseTeams();
        int actualTeamsSize = response.getTeams().size();
        assertEquals(actualTeamsSize, Data.expectedTeamsSize);

    }
//- verify the oldest team is Montreal Canadiens
    @Test
    public void checkTheOldestTeam() {
        GetResponseRootDto response = getResponseTeams();
        String actualOldestTeam = findOldestTeam(response).getName();
        assertEquals(actualOldestTeam, Data.expectedOldestTeam);

    }
//- verify there's a city with more than 1 team and verify names of those teams
    @Test
    public void checkCityWithMoreThan1Team() {
        GetResponseRootDto response = getResponseTeams();
        GetResponseTeamsDto[] teamsArray = response.getTeams().toArray(new GetResponseTeamsDto[0]);
        containsTeamsWithSameCity(teamsArray);

    }
//- verify there are 8 teams in the Metropolitan division and verify them by their names
    @Test
    public void check8TeamsAreInMetropolitanDivision() {
        GetResponseRootDto response = getResponseTeams();
        
        List<GetResponseTeamsDto> metropolitanTeams = filterTeamsByDivision(response.getTeams(),"Metropolitan");
        assertEquals(Data.expectedMetropolitanTeamsSize,metropolitanTeams.size());

        List<String> actualTeamNames = extractTeamNames(metropolitanTeams);
        assertEquals(Data.expectedTeamNames, actualTeamNames);
    }
//- open web browser and scrape roster of the oldest team and verify there's more Canadian players than players from USA
    @Test
    public void checkCanadianPlayersMoreThanUsaPlayers(){
        Specifications.installSpecification(Specifications.requestSpec(Data.NHL_URL), Specifications.responseSpec(200));

        Response response = given()
                .when()
                .get("")
                .then()
                .log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<String> allBirthCountries = new ArrayList<>();
        allBirthCountries.addAll(extractBirthCountries(jsonPath,"forwards"));
        allBirthCountries.addAll(extractBirthCountries(jsonPath,"defensemen"));
        allBirthCountries.addAll(extractBirthCountries(jsonPath,"goalies"));


        assertTrue(areCanadianPlayersMoreThanUsaPlayers(allBirthCountries));
    }


    }





