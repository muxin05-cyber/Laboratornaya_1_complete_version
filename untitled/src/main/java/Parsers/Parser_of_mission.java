package Parsers;

import Missions_data.Mission;

public abstract class Parser_of_mission {

    public abstract Mission extract_mission(String filepath);

}
