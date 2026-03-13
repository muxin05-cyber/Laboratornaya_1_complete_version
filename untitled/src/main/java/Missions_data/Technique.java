package Missions_data;


public class Technique {
    private String name;
    private String type;
    private String owner;
    private int damage;

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDamage(){
        return damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }


    public void setType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getOwner() {
        return owner;
    }
}
