package tools;

import entity.Shoe;
import entity.Purchase;
import entity.Customer;
import entity.Income;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class SaverToBase implements Keeping{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SKTVp20MagasinPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public void saveShoes(List<Shoe> shoes) {
        tx.begin();
            for (int i = 0; i < shoes.size(); i++) {
                if(shoes.get(i).getId() == null){
                    em.persist(shoes.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Shoe> loadShoes() {
        List<Shoe> shoes = null;
        try {
            shoes = em.createQuery("SELECT shoe FROM Shoe shoe")
                .getResultList();
        } catch (Exception e) {
            shoes = new ArrayList<>();
        }
        return shoes;
    }

    @Override
    public void saveCustomers(List<Customer> customers) {
        tx.begin();
            for (int i = 0; i < customers.size(); i++) {
                if(customers.get(i).getId() == null){
                    em.persist(customers.get(i));
                }
            }
        tx.commit();
    }
    @Override
    public List<Customer> loadCustomers() {
        List<Customer> customers = null;
        try {
            customers = em.createQuery("SELECT customer FROM Customer customer")
                .getResultList();
        } catch (Exception e) {
            customers = new ArrayList<>();
        }
        return customers;
    }

    @Override
    public void savePurchases(List<Purchase> purchases) {
        tx.begin();
            for (int i = 0; i < purchases.size(); i++) {
                if(purchases.get(i).getId() == null){
                    em.persist(purchases.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Purchase> loadPurchases() {
        List<Purchase> purchases = null;
        try {
            purchases = em.createQuery("SELECT income FROM Income income")
                .getResultList();
        } catch (Exception e) {
            purchases = new ArrayList<>();
        }
        return purchases;
    }

    @Override
    public void saveIncome(List<Income> incomes) {
        tx.begin();
            for (int i = 0; i < incomes.size(); i++) {
                if(incomes.get(i).getId() == null){
                    em.persist(incomes.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Income> loadIncome() {
        List<Income> incomes = null;
        try {
            incomes = em.createQuery("SELECT income FROM Income income")
                .getResultList();
        } catch (Exception e) {
            incomes = new ArrayList<>();
        }
        return incomes;
    }


}