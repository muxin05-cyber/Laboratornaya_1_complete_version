package Parsers;

import Missions_data.Curse;
import Missions_data.Mission;
import Missions_data.Sorcerer;
import Missions_data.Technique;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TXT_parser extends Parser_of_mission {

    @Override
    public Mission extract_mission(String filename) {
        Mission mission = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            mission = new Mission();
            List<Sorcerer> sorcerers = new ArrayList<>();
            List<Technique> techniques = new ArrayList<>();
            Curse curse = new Curse();
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf(':');
                if (index != -1) {
                    String tag = line.substring(0, index);
                    if (tag.equals("missionId")) {
                        mission.setMissionId(line.substring(index + 1).trim());
                        continue;
                    }
                    if (tag.equals("date")) {
                        mission.setDate(line.substring(index + 1).trim());
                        continue;
                    }
                    if (tag.equals("outcome")) {
                        mission.setOutcome(line.substring(index + 1).trim());
                        continue;
                    }
                    if (tag.equals("damageCost")) {
                        mission.setDamageCost(Integer.parseInt(line.substring(index + 1).trim()));
                        continue;
                    }

                    if (tag.equals("location")) {
                        mission.setLocation(line.substring(index + 1).trim());
                        continue;
                    }

                    if (tag.equals("comment")) {
                        mission.setComment(line.substring(index + 1).trim());
                        continue;
                    }

                    if (tag.equals("note")) {
                        mission.setComment(line.substring(index + 1).trim());
                        continue;
                    }

                    if (tag.equals("curse.name")) {
                        curse.setName(line.substring(index + 1).trim());
                    }

                    if (tag.equals("curse.threatLevel")) {
                        curse.setThreatLevel(line.substring(index + 1).trim());
                    }


                    int start_point = line.indexOf('[');
                    int end_point = line.indexOf(']');

                    if (start_point != -1 && end_point != -1 && end_point > start_point) {
                        String inside = line.substring(start_point + 1, end_point);  // то, что внутри скобок
                        String before = line.substring(0, start_point);              // часть до скобок
                        String after = line.substring(end_point + 1, index);    // часть между ] и :
                        String outside = before + after;                              // полный - без скобок и индекса

                        String value = line.substring(index + 1).trim();

                        if (outside.equals("sorcerer.name")) {
                            Sorcerer sorcerer = new Sorcerer();
                            sorcerer.setName(value);
                            sorcerers.add(sorcerer);
                            continue;
                        }
                        if (outside.equals("sorcerer.rank")) {
                            if (sorcerers.size() > Integer.parseInt(inside)) {
                                sorcerers.get(Integer.parseInt(inside)).setRank(value);
                            }
                            continue;
                        }

                        if (outside.equals("technique.name")) {
                            Technique technique = new Technique();
                            technique.setName(value);
                            techniques.add(technique);
                            continue;
                        }
                        if (outside.equals("technique.type")) {
                            if (techniques.size() > Integer.parseInt(inside)) {
                                techniques.get(Integer.parseInt(inside)).setType(value);
                            }
                            continue;
                        }
                        if (outside.equals("technique.owner")) {
                            if (techniques.size() > Integer.parseInt(inside)) {
                                techniques.get(Integer.parseInt(inside)).setOwner(value);
                            }
                            continue;
                        }
                        if (outside.equals("technique.damage")) {
                            if (techniques.size() > Integer.parseInt(inside)) {
                                techniques.get(Integer.parseInt(inside)).setDamage(Integer.parseInt(value));
                            }
                        }
                    }

                }
            }
            mission.setCurse(curse);
            mission.setSorcerers(sorcerers);
            mission.setTechnique(techniques);

        } catch (IOException e) {
            return null;
        }
        return mission;
    }
}
