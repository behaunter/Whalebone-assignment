package com.whalebone.apiTests.tests;

import io.restassured.path.json.JsonPath;
import сom.whalebone.apiTests.dto.GetResponseRootDto;
import сom.whalebone.apiTests.dto.GetResponseTeamsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class BaseMethods {

    public GetResponseTeamsDto findOldestTeam(GetResponseRootDto response) {
        GetResponseTeamsDto oldestTeam = null;
        int oldestYear = Integer.MAX_VALUE;

        for (GetResponseTeamsDto team : response.getTeams()) {
            if (team.getFounded() < oldestYear) {
                oldestYear = team.getFounded();
                oldestTeam = team;
            }
        }

        return oldestTeam;
    }


    public boolean containsTeamsWithSameCity(GetResponseTeamsDto[] teamsArray) {
        for (int i = 0; i < teamsArray.length; i++) {
            for (int j = i + 1; j < teamsArray.length; j++) {
                if (teamsArray[i].getLocation().equals(teamsArray[j].getLocation())) {
                    System.out.println("Teams with the same city: " +
                            teamsArray[i].getName() + ", " + teamsArray[j].getName());
                    return true;
                }
            }
        }
        return false;
    }

    public static GetResponseRootDto getResponseTeams() {
        Specifications.installSpecification(Specifications.requestSpec(Data.WHALEBONE_URL), Specifications.responseSpec(200));

        GetResponseRootDto response = given()
                .when()
                .get("")
                .then()
                .log().all()
                .extract().response().jsonPath().getObject("", GetResponseRootDto.class);
        return response;
    }

    public static List<GetResponseTeamsDto> filterTeamsByDivision(List<GetResponseTeamsDto> teams, String divisionName) {
        return teams.stream()
                .filter(team -> team.getDivision().getName().equals(divisionName))
                .collect(Collectors.toList());


    }


    public static List<String> extractTeamNames(List<GetResponseTeamsDto> teams) {
        List<String> teamNames = new ArrayList<>();
        for (GetResponseTeamsDto team : teams) {
            teamNames.add(team.getName());
        }
        return teamNames;
    }

    public static List<String> extractBirthCountries(JsonPath jsonPath, String category) {
        List<String> birthCountries = new ArrayList<>();

        int playersCount = jsonPath.getList(category).size();
        for (int i = 0; i < playersCount; i++) {
            String birthCountry = jsonPath.getString(category + "[" + i + "].birthCountry");
            birthCountries.add(birthCountry);
        }

        return birthCountries;
    }

    public boolean areCanadianPlayersMoreThanUsaPlayers(List<String> list) {
        int canadianPlayersCount = 0;
        int usPlayersCount = 0;
        boolean answer = false;

        for (String birthCountry : list) {
            if ("CAN".equals(birthCountry)) {
                canadianPlayersCount++;
            } else if ("USA".equals(birthCountry)) {
                usPlayersCount++;
            }
        }

        if (canadianPlayersCount > usPlayersCount) {
            answer = true;
        } else {
            answer = false;
        }

        return answer;
    }


}
