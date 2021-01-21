package storage;

import model.Contact;

import java.io.*;
import java.util.List;

public class ReadAndWrite {
    public static void creatNewFile(String fileName){
        File saveFile=new File(fileName);
        if (!saveFile.exists()){
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void writeFile(String fileName, Object object){
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            fileOutputStream.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void writeFileToText(String fileName, List<Contact> list){
        try {
            FileWriter fileWriter=new FileWriter(fileName);
            BufferedWriter writer=new BufferedWriter(fileWriter);
            writer.write("Số điên thoại"+","+"Tên"+","+"Email"+"\n");
            for (Contact contact:list) {
                writer.write(contact.getNumberPhone()+","+contact.getFullName()+","+contact.getEmail()+"\n");
            }
            writer.close();
        } catch (IOException e) {
        }

    }
    public static Object readFile(String fileName){
        try {
            FileInputStream fileInputStream=new FileInputStream(fileName);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            Object object=objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
            return object;
        }catch (Exception e){
            return null;
        }
    }
}
