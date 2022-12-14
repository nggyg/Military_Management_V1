package basic;

public class Weapon extends Item {
    @Override
    public void assignedTo(Soldier soldier) {
        soldier.setWeapon(this);
        this.usable=false;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalibre() {
        return calibre;
    }

    public void setCalibre(double calibre) {
        this.calibre = calibre;
    }

    @Override
    public int getID() {
        return weaponID;
    }

    public void setWeaponID(int weaponID) {
        this.weaponID = weaponID;
    }

    public Weapon(String name, double calibre, int weaponID) {
        this.name = name;
        this.calibre = calibre;
        this.weaponID = weaponID;
    }

    private String name;
    private double calibre;
    private int weaponID;

    public Weapon() {
        this.name = "TBD";
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
