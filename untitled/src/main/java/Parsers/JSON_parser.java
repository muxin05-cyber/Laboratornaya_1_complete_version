package Parsers;

import Missions_data.Mission;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSON_parser extends Parser_of_mission {

    @Override
    public Mission extract_mission(String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        Mission mission = null;
        try {
            mission = mapper.readValue(new File(filepath), Mission.class);
        } catch (IOException e) {
            return null;
        }
        return mission;
    }
}