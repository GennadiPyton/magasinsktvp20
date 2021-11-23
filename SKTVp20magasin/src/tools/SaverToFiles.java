package tools;

import entity.Customer;
import entity.Income;
import entity.Purchase;
import entity.Shoe;
import interfaces.Keeping;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class SaverToFiles implements Keeping{

    @Override
    public void saveShoes(List<Shoe> shoes) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("shoes");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(shoes);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет файла shoes", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }

    @Override
    public List<Shoe> loadShoes() {
        List<Shoe> shoes = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("shoes");
            ois = new ObjectInputStream(fis);
            shoes = (List<Shoe>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "shoes еше не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка чтения", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        }
        return shoes;
    }

    @Override
    public void saveCustomers(List<Customer> customers) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("customers");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(customers);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет файла customers", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }

    @Override
    public List<Customer> loadCustomers() {
        List<Customer> readers = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("customers");
            ois = new ObjectInputStream(fis);
            readers = (List<Customer>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "customers еше не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка чтения", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        }
        return readers;
    }

    @Override
    public void savePurchases(List<Purchase> purchases) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("purchases");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(purchases);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет файла purchases", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка ввода", ex);
        }
    }

    @Override
    public List<Purchase> loadPurchases() {
        List<Purchase> purchases = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("purchases");
            ois = new ObjectInputStream(fis);
            purchases = (List<Purchase>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "purchases еше не создан", ex);
        } catch (IOException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Ошибка чтения", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaverToFiles.class.getName()).log(Level.SEVERE, "Нет такого файла", ex);
        }
        return purchases;
    }

    @Override
    public void saveIncome(List<Income> incomes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Income> loadIncome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}