package сom.whalebone.apiTests.dto;

import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class GetResponseRootDto {

    public ArrayList<GetResponseTeamsDto> teams;
}
