package view;

import model.Contact;
import service.ContactManage;
import storage.ReadAndWrite;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String REGEX_PHONE_NUMBER = "^09[0-9]{8}";
    public static final String REGEX_EMAIL = "[A-Za-z0-9]{6,20}@[a-z]{5}\\.com";
    public static final String REGEX_BIRTHDAY = "[0-9]{2}-[0-9]{2}-[0-9]{4}";
    public static ContactManage mange;
    public static Scanner scanner=new Scanner(System.in);

    public static void main(String[] args) {
        ReadAndWrite.creatNewFile(ContactManage.FILE_NAME);
        Object obj=ReadAndWrite.readFile(ContactManage.FILE_NAME);
        List<Contact> list;
        list=(obj==null)?new ArrayList<>():((List<Contact>)obj);
        mange=new ContactManage(list);
        int choice;
        do {
            System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ----");
            System.out.println("Chọn chức năng theo số (để tiếp tục)");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("7. ghi vào file");
            System.out.println("8. Thoát");
            System.out.println("Chọn chức năng:");
            choice=scanner.nextInt();
            switch (choice){
                case 1:
                    mange.showAll();
                    break;
                case 2:
                    addNew();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    remove();
                    break;
                case 5:
                    searching();
                    break;
                case 6:
                   readFile();
                   break;
                case 7:
                    writeFile();
                    break;
                case 8:
                    System.exit(0);
            }
        }while (choice!=8);
    }
    public static void addNew(){
        String number=getNumber();
        Contact searchingcontact=mange.search(number);
        if (searchingcontact==null) {
            Scanner input = new Scanner(System.in);
            System.out.println("Nhập nhóm danh bạ");
            String group = input.nextLine();
            System.out.println("Nhập họ tên");
            String name = input.nextLine();
            Scanner input1 = new Scanner(System.in);
            System.out.println("Nhập giới tính");
            String gender = input1.nextLine();
            System.out.println("Nhập địa chỉ");
            String address = input1.nextLine();
            String birthday = getBirthday();
            String email = getEmail();
            Contact contact = new Contact(number, group, name, gender, address, birthday, email);
            mange.addNew(contact);
        }else System.out.println("số điện thoại đã tồn tại không thể thêm mới");
    }
    public static String getNumber(){
        Scanner input=new Scanner(System.in);
            System.out.println("Nhập số điện thoại");
            String number=input.nextLine();
            if (!number.matches(REGEX_PHONE_NUMBER)){
                do {
                    System.out.println("Mời nhập lại");
                    number=scanner.nextLine();
                }while (!number.matches(REGEX_PHONE_NUMBER));
            }
        return number;
    }
    public static String getEmail(){
        System.out.println("Nhập email");
        String email=scanner.nextLine();
        if (!email.matches(REGEX_EMAIL)){
            do {
                System.out.println("Mời nhập lại");
                email=scanner.nextLine();
            }while (!email.matches(REGEX_EMAIL));
        }
        return email;
    }
    public static String getBirthday(){
        System.out.println("Nhập ngày sinh dd-mm-yyyy");
        String birthday=scanner.nextLine();
        if (!birthday.matches(REGEX_BIRTHDAY)){
            do {
                System.out.println("Mời nhập lại");
                birthday=scanner.nextLine();
            }while (!birthday.matches(REGEX_BIRTHDAY));
        }
        return birthday;
    }
    public static void update(){
        String number=getNumber();
        Contact contact= mange.search(number);
        if (contact==null) System.out.println("Không tồn tại số Điện thoại");
        else {
            Scanner input=new Scanner(System.in);
            System.out.println("Nhập nhóm danh bạ");
            String group=input.nextLine();
            System.out.println("Nhập họ tên");
            String name=input.nextLine();
            Scanner input1=new Scanner(System.in);
            System.out.println("Nhập giới tính");
            String gender=input1.nextLine();
            System.out.println("Nhập địa chỉ");
            String address=input1.nextLine();
            String birthday=getBirthday();
            String email=getEmail();
            Contact newContact=new Contact(number,group,name,gender,address,birthday,email);
            mange.update(number,newContact);
        }
    }
    public static void remove(){
        String number=getNumber();
        mange.remove(number);
    }
    public static void searching(){
        String number=getNumber();
        Contact contact=mange.search(number);
        if (contact==null) System.out.println("Không tìm thấy");
        else System.out.println(contact);
    }
    public static void readFile(){
        Scanner input= new Scanner(System.in);
        System.out.println("Đọc file cập nhật dữ liệu từ file, chọn Y để chắc chắn");
        String yes=input.nextLine();
        if (yes.equals("Y")){
            Object newObj=ReadAndWrite.readFile(ContactManage.FILE_NAME);
            List<Contact> newList;
            newList=(newObj==null)?new ArrayList<>():((List<Contact>)newObj);
            mange.setContactList(newList);
            System.out.println("Đọc thành công");
        }else System.out.println("Cancle");
    }
    public static void writeFile(){
        Scanner input= new Scanner(System.in);
        System.out.println("Ghi file cập nhật dữ liệu vào file, chọn Y để chắc chắn");
        String yes=input.nextLine();
        if (yes.equals("Y")){
            ReadAndWrite.writeFile(ContactManage.FILE_NAME,mange.getContactList());
            ReadAndWrite.writeFileToText(ContactManage.FILE_NAME_CSV,mange.getContactList());
            System.out.println("Ghi thành công");
        }else System.out.println("Cancle");
    }

}
