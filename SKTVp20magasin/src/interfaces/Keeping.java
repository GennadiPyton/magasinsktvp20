package interfaces;

import entity.Customer;
import entity.Purchase;
import entity.Shoe;
import entity.Income;
import java.util.List;

/**
 *
 * @author user
 */
public interface Keeping {
    public void saveShoes(List<Shoe> shoes);
    public List<Shoe> loadShoes();
    public void saveCustomers(List<Customer> customers);
    public List<Customer> loadCustomers();
    public void savePurchases(List<Purchase> purchases);
    public List<Purchase> loadPurchases();
    public void saveIncome(List<Income> incomes);
    public List<Income> loadIncome();
    

}