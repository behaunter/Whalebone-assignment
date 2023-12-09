package сom.whalebone.apiTests.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class GetResponseTeamsDto {
    public String name;
    public String location;
    public int founded;
    public int firstYearOfPlay;
    public GetResponseDivisionDto division;
    public String officialSiteUrl;
}

