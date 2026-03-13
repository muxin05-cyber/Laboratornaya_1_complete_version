package Missions_data;

import java.util.ArrayList;
import java.util.List;



public class Mission {
    private String missionId;
    private String date;
    private String location;
    private String outcome;
    private int damageCost;
    private Curse curse;
    private List<Sorcerer> sorcerers;
    private List<Technique> techniques;
    private String comment = "";


    public Mission() {}

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public String getMissionId() {
        return missionId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setDamageCost(int damageCost) {
        this.damageCost = damageCost;
    }

    public void setCurse(Curse curse) {
        this.curse = curse;
    }

    public int getDamageCost() {
        return damageCost;
    }

    public Curse getCurse() {
        return curse;
    }

    public void setSorcerers(List<Sorcerer> sorcerers) {
        this.sorcerers = sorcerers;
    }

    public void setTechnique(List<Technique> techniques) {
        this.techniques = techniques;
    }

    public List<Sorcerer> getSorcerers() {
        return sorcerers;
    }

    public List<Technique> getTechniques() {
        return techniques;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getView() {
        StringBuilder view = new StringBuilder();
        view.append("Общая информация:\n\n");
        view.append("missionId: ").append(getMissionId()).append("\n");
        view.append("Дата: ").append(getDate()).append("\n");
        view.append("Место: ").append(getLocation()).append("\n");
        view.append("Результат: ").append(getOutcome()).append("\n");
        view.append("Ущерб: ").append(getDamageCost()).append("\n\n");

        view.append("Проклятие:\n\n");
        view.append("Название: ").append(curse.getName()).append("\n");
        view.append("Сила проклятия: ").append(curse.getThreatLevel()).append("\n\n");

        if (getSorcerers() != null && !getSorcerers().isEmpty()) {
            view.append("Колдуны:\n\n");
            for (int i = 0; i < getSorcerers().size(); i++) {
                Sorcerer s = getSorcerers().get(i);
                view.append(i + 1).append("-ый Колдун:\n");
                view.append("Имя: ").append(s.getName()).append("\n");
                view.append("Ранг: ").append(s.getRank()).append("\n\n");
            }
        }

        if (getTechniques() != null && !getTechniques().isEmpty()) {
            view.append("Техники:\n\n");
            for (int i = 0; i < getTechniques().size(); i++) {
                Technique t = getTechniques().get(i);
                view.append(i + 1).append("-ая Техника:\n");
                view.append("Название: ").append(t.getName()).append("\n");
                view.append("Тип: ").append(t.getType()).append("\n");
                view.append("Владелец: ").append(t.getOwner()).append("\n");
                view.append("Ущерб: ").append(t.getDamage()).append("\n\n");
            }
        }
        if (!getComment().isEmpty()){
            view.append("Комментарий: ").append(getComment());
        }

        return view.toString();
    }




}
