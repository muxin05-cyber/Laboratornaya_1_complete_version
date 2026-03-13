import Missions_data.Mission;
import Parsers.JSON_parser;
import Parsers.Parser_of_mission;
import Parsers.TXT_parser;
import Parsers.XML_parser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Files_processing {
    Parser_of_mission parser_xml;
    Parser_of_mission parser_json;
    Parser_of_mission parser_txt;
    private Basic_window window;

    public Files_processing(Basic_window window) {
        parser_xml = new XML_parser();
        parser_json = new JSON_parser();
        parser_txt = new TXT_parser();
        this.window = window;
    }

    public Mission define_type_of_file(String filepath) {
        String type = get_type_based_on_filepath(filepath);
        Mission mission = null;
        if (type.equals("")) {
            window.show_error_message("Файл имеет некорректный формат");
            return null;
        }
        if (type.equals("xml")) {
            if (checking_xml(filepath)) {
                mission = parser_xml.extract_mission(filepath);
                if (mission == null) {
                    window.show_error_message("Файл имеет ошибки в структуре.\nОжидалась валидная структура для xml файла");
                }
            } else {
                window.show_error_message("Файл имеет неправильную структуру.\nОжидалась валидная структура для xml файла");
                return null;
            }
        } else if (type.equals("json")) {
            if (checking_json(filepath)) {
                mission = parser_json.extract_mission(filepath);
                if (mission == null) {
                    window.show_error_message("Файл имеет ошибки в структуре.\nОжидалась валидная структура для json файла");
                }
            } else {
                window.show_error_message("Файл имеет неправильную структуру.\nОжидалась валидная структура для json файла");
                return null;
            }
        } else if (type.equals("txt")) {
            if (checking_txt(filepath)) {
                mission = parser_txt.extract_mission(filepath);
                if (mission == null) {
                    window.show_error_message("Файл имеет ошибки в структуре.\nОжидалась валидная структура для txt файла");
                }
            } else {
                window.show_error_message("Файл имеет неправильную структуру.\nОжидалась валидная структура для txt файла");
                return null;
            }

        }
        return mission;
    }

    private String get_type_based_on_filepath(String filepath) {
        if (filepath == null || filepath.isEmpty()) {
            return "";
        }
        int last_dot = filepath.lastIndexOf('.');
        int last_sep = Math.max(filepath.lastIndexOf('/'), filepath.lastIndexOf('\\'));
        if (last_dot > last_sep && last_dot < filepath.length() - 1) {
            return filepath.substring(last_dot + 1);
        }
        return "";
    }

    private boolean checking_xml(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String first_line = reader.readLine();
            if (first_line == null) {
                return false;
            }
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                if (line.startsWith("<mission") || line.equals("<mission>")) {
                    return true;
                }
                return false;
            }
        } catch (IOException e) {
            return false;

        }
        return false;
    }

    public boolean checking_json(String filepath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(new File(filepath));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean checking_txt(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String firstTag = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                int colonIndex = line.indexOf(':');
                if (colonIndex != -1) {
                    firstTag = line.substring(0, colonIndex).trim();
                    break;
                }
            }

            if (firstTag == null) {
                return false;
            }

            if (!firstTag.equals("missionId")) {
                return false;
            }

            return true;

        } catch (IOException e) {
            return false;
        }
    }


}
