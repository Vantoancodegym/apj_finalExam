package service;

import model.Contact;

import java.util.List;

public class ContactManage {
    private List<Contact> contactList;
    public ContactManage(List<Contact> contactList) {
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
    public Contact search(int numberSearching){
        for (Contact contact:contactList) {
            int numberPhone=contact.getNumberPhone();
            if (numberPhone==numberSearching)return contact;
        }
        return null;
    }
    public void update(int numberSearching,Contact newContact){
        Contact contact=search(numberSearching);
        int index= contactList.indexOf(contact);
        contactList.set(index,newContact);
        System.out.println("Cập nhật Thành công");
    }
    public void remove(int numberSearching){
        Contact contact=search(numberSearching);
        contactList.remove(contact);
        System.out.println("Xóa thành công");
    }

}
