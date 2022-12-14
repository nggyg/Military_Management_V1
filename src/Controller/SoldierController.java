package Controller;
import java.sql.*;

import Repository.Repository;
import basic.Rang;
import basic.Soldier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SoldierController {
    private Repository<Soldier> generalSoldierList;

    public SoldierController() {
        this.generalSoldierList=new Repository<Soldier>(new ArrayList<Soldier>());
    }

    public SoldierController(Repository<Soldier> generalSoldierList){
        this.generalSoldierList = generalSoldierList;
    }

    public Soldier findById(int id) {
        for (Soldier soldier : generalSoldierList.getContent()) {
            if (soldier.getId() == id) {
                return soldier;
            }
        }
        return null;
    }

    public ArrayList<Soldier> resultSoldiers(){
        ArrayList<Soldier> soldiers = new ArrayList<Soldier>();
        String url ="jdbc:sqlserver://DESKTOP-GLDFCK4;databaseName=MillitaryManagement;user=test;password=123;encrypt=true;trustServerCertificate=true";

        try{
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            int id=1;
            String idnull = " ";
            while(idnull!="NULL") {
                ResultSet resultSet = statement.executeQuery("select * from Soldier where id=' "+id+" '");
                Soldier soldier1 = null;
                while (resultSet.next()) {
                    soldier1 = new Soldier(resultSet.getString("name"), resultSet.getInt("id"), Rang.valueOf(resultSet.getString("rang")));
                    idnull = resultSet.getString(("id"));
                }
                soldiers.add(soldier1);
                id++;
            }
        }catch(Exception e){
            System.out.println("Failed");
            System.out.println(e.getMessage());
        }
        return soldiers;
    }

    public void addSoldier(Soldier newSoldier) {
        newSoldier.setRang(Rang.Private);
        generalSoldierList.add(newSoldier);
        String url ="jdbc:sqlserver://DESKTOP-GLDFCK4;databaseName=MillitaryManagement;user=test;password=123;encrypt=true;trustServerCertificate=true";
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into Soldier (name, rang) values (' "+newSoldier.getName()+
                    " ', ' "+newSoldier.getRang()+"')");
        } catch (Exception e) {
            System.out.println("Failed");
            System.out.println(e.getMessage());
        }
    }

    public boolean deleteSoldier(int id) {
        String url ="jdbc:sqlserver://DESKTOP-GLDFCK4;databaseName=MillitaryManagement;user=test;password=123;encrypt=true;trustServerCertificate=true";
//        ArrayList<Soldier> soldiers = resultSoldiers();
        for (Soldier soldier : generalSoldierList.getContent()) {
            if (id == soldier.getId()) {
                generalSoldierList.remove(soldier);
                return true;
            }
        }
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from Soldier where id = '"+id+"'");
        } catch (Exception e) {
            System.out.println("Failed");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateSoldier(int id, String newName, Rang newRang) {
        String url ="jdbc:sqlserver://DESKTOP-GLDFCK4;databaseName=MillitaryManagement;user=test;password=123;encrypt=true;trustServerCertificate=true";
        for (Soldier soldier : generalSoldierList.getContent()) {
            if (id == soldier.getId()) {
                soldier.setName(newName);
                soldier.setRang(newRang);
                return true;
            }
        }
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            statement.executeUpdate("update Soldier set name= '"+newName+"' , set rang='"+newRang+"'");
        } catch (Exception e) {
            System.out.println("Failed");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void displaySoldiers() {
        this.generalSoldierList.display();
    }
    //select from database

}
