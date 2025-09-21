package Lib;
import java.io.*;
import java.util.*;

public class FileManager {
    private String fileName;
    
    public FileManager(String filename){
        this.fileName = filename;
    }
    public void saveScore(String playerName,int score){
        // save Score (new score > oldscore)
        Map<String,Integer> data = LoadPlayerData();
        int oldscore = data.getOrDefault(playerName, score); //ถ้ามีชื่อผู้เล่นในmapให้ใช้คะแนนที่เก็บไว้ ถ้าไม่มีให้ใช้score

        if(score > oldscore){
            data.put(playerName, score);
            saveAllData(data);//บันทึกข้อมูลลงไฟล์
        }
    }
    //โหลดคะแนนสูงสุดรวม(ของทุกคน)
    public int LoadHighScore(){
        Map<String,Integer> data = LoadPlayerData();
        int maxScore = 0;

        for (int score : data.values()) {
           if (score > maxScore) {
                maxScore = score;
           } 
        }
        return maxScore;
    }
    //บันทึกข้อมูลผู้เล่น(ของผู้เล่นเดียว)
    public void savePlayerData(String name , int score){
        Map<String,Integer> data = LoadPlayerData();
        data.put(name, score);
        saveAllData(data);
    }
    public Map<String,Integer> LoadPlayerData(){
        Map<String,Integer> data = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line; //เก็บข้อความแต่ละบรรทัด

            while ((line = br.readLine() )!= null) { //ทำงานเมื่อบรรทัดที่อ่านไม่เป็นnull
                String[] parts = line.split(",");//แยกข้อมูลแต่ละบรรทัดโดยใช้ ","เป็นตัวคั่น

                if (parts.length == 2) { //ตรวจสอบว่าในบรรทัดมี2ข้อมูลนั่นคือ ชื่อ และ คะแนน ของผู้เล่น
                    String name = parts[0].trim();//ดึงชื่อผู้เล่นและเอาเว้นวรรคหน้า-หลังออก
                    int score = Integer.parseInt(parts[1].trim()); // เอาเว้นวรรคออก และ แปลงค่าInteger ไปเป็น int
                    data.put(name,score); //เก็บข้อมูลลงในdata
                }
            }
        } catch (FileNotFoundException e) {
            // ถ้าไฟล์ยังไม่มี ให้คืน Map ว่าง ๆ(ยังไม่มีผู้เล่น)
        } catch(IOException e){
            e.printStackTrace(); //print error on console
        }
        return data;

    }
    public void saveAllData(Map<String,Integer>data){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for (Map.Entry<String,Integer> entry: data.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}