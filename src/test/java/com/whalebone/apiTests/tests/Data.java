package com.whalebone.apiTests.tests;

import java.util.List;

public class Data {

    public static final String WHALEBONE_URL = "https://qa-assignment.dev1.whalebone.io/api/teams";
    public static final String NHL_URL = "https://api-web.nhle.com/v1/roster/MTL/20232024";
    public static final int expectedTeamsSize = 32;
    public static final int expectedMetropolitanTeamsSize = 8;
    public static final String expectedOldestTeam = "Montreal Canadiens";

    public static final List<String> expectedTeamNames = List.of(
            "Carolina Hurricanes", "Columbus Blue Jackets", "New Jersey Devils", "New York Islanders", "New York Rangers", "Philadelphia Flyers", "Pittsburgh Penguins", "Washington Capitals"
    );
}
