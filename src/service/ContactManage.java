package service;

import model.Contact;

import java.util.List;

public class ContactManage {
    public static String FILE_NAME="contact.dat";
    public static String FILE_NAME_CSV="contact.csv";
    private List<Contact> contactList;
    public ContactManage(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public void showAll(){
        for (Contact contact:contactList) {
            System.out.println(contact);
        }
    }
    public void addNew(Contact contact){
        contactList.add(contact);
        System.out.println("Thêm thành công");
    }
    public Contact search(String numberSearching){
        for (Contact contact:contactList) {
            String numberPhone=contact.getNumberPhone();
            if (numberPhone.equals(numberSearching))return contact;
        }
        return null;
    }
    public void update(String numberSearching,Contact newContact){
        Contact contact=search(numberSearching);
        int index= contactList.indexOf(contact);
        contactList.set(index,newContact);
        System.out.println("Cập nhật Thành công");
    }
    public void remove(String numberSearching){
        Contact contact=search(numberSearching);
        if (contact==null) System.out.println("Số không tồn tại");
        else {
            contactList.remove(contact);
            System.out.println("Xóa thành công");
        }
    }

}
