package intenal;

import java.util.ArrayList;

import userData.Admin;
import userData.Owner;
import userData.Tenant;
import userData.TypeAccount;
import userData.User;

public class Processing {

    private ArrayList<User> array;

    public Processing() {
        array = new ArrayList<>();
    }

    public void createAccount(ArrayList<String> arrayList, TypeAccount type) {
        User u;
        if (type == TypeAccount.ADMINISTRATOR){
            u = new Admin(arrayList.get(0), arrayList.get(0), arrayList.get(0), arrayList.get(0), arrayList.get(0));
        }else if (type == TypeAccount.OWNER){
            u = new Owner(arrayList.get(0), arrayList.get(0), arrayList.get(0), arrayList.get(0), arrayList.get(0));
        }else{
            u = new Tenant(arrayList.get(0), arrayList.get(0), arrayList.get(0), arrayList.get(0), arrayList.get(0));
        }
        array.add(u);
    }

    public void connect(ArrayList<String> arrayList, TypeAccount type) {
        for(User u : array){
        }
    }

    public boolean testValidityAccount(ArrayList<String> informations, TypeAccount type) {
        return true;
    }
}
