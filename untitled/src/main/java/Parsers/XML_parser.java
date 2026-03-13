package Parsers;

import Missions_data.Mission;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;


public class XML_parser extends Parser_of_mission {
    JAXBContext context;
    Unmarshaller unmarshaller;
    public XML_parser(){
        try {
            context = JAXBContext.newInstance(Mission.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        try {
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Mission extract_mission(String filepath) {
        String xml = null;
        Mission mission = null;
        try {
            xml = Files.readString(Paths.get(filepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringReader reader = new StringReader(xml);
        try {
            mission = (Mission)unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            return null;
        }
        return mission;
    }
}
