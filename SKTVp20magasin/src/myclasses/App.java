package myclasses;

import entity.Customer;
import entity.Purchase;
import entity.Shoe;
import entity.Income;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import tools.SaverToBase;
import tools.SaverToFiles;


/**
 *
 * @author user
 */
public class App {
    public static boolean toFile = false;

    
    private Scanner scanner = new Scanner(System.in);
    private List<Shoe> shoes = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Purchase> purchases = new ArrayList<>();
    private List<Income> incomes = new ArrayList<>();
    Income income = new Income();
    private Keeping keeper;
    
    public App(){
        if(toFile){
            keeper = new SaverToFiles();
        }else{
            keeper = new SaverToBase();
        }
        shoes = keeper.loadShoes();
        customers = keeper.loadCustomers();
        purchases = keeper.loadPurchases();
        incomes = keeper.loadIncome();
    }
    
    public void run(){
        String repeat = "yes";
        do{
            System.out.println("Выберите номер задачи: ");
            System.out.println("0: Закрыть программу");
            System.out.println("1: Добавить покупателя");
            System.out.println("2: Добавить обувь");
            System.out.println("3: Список обуви");
            System.out.println("4: Список покупателей");
            System.out.println("5: Купить обувь");
            System.out.println("6: Доход магазина за все время работы");
            System.out.println("7: Добавить деньги покупателю");
            System.out.println("8: Изменить пользователя");
            System.out.println("9: Изменить товар");
            System.out.println("10: Доход магазина за определенный месяц");
            int task = getNumber();

            switch (task) {
                case 0:
                    repeat = "no";
                    break;
                    
                case 1:
                    addCustomer();
                    break;
                
                case 2:
                    addShoe();
                    break;
                    
                case 3:
                    listShoe();
                    break;
                    
                case 4:
                    listCustomer();
                    break;
                    
                case 5:
                    purchaseShoe();
                    break;
                   
                case 6:
                    Income();
                    break;
                    
                case 7:
                    addMoney();
                    break;
                    
                case 8:
                    changeCustomer();
                    break;
                    
                case 9:
                    changeShoe();
                    break;
                    
                case 10:
                    incomeMonth();
                    break;
                   
       }
    }while("yes".equals(repeat));
       System.out.println("До свидания!");
    }

    private int getNumber() {
        int number;
        do{
           String strNumber = scanner.nextLine();
           try {
               number = Integer.parseInt(strNumber);
               return number;
           } catch (NumberFormatException e) {
               System.out.println("Введено \""+strNumber+"\". Выбирайте номер ");
           }
       }while(true);
    }

    private void addCustomer() {
        System.out.println("Добавить покупателя: ");
        Customer customer = new Customer();
        System.out.print("Введите имя покупателя: ");
        customer.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию покупателя: ");
        customer.setLastname(scanner.nextLine());
        System.out.print("Введите телефон покупателя: ");
        customer.setPhone(scanner.nextLine());
        System.out.println("Введите количество денег у покупателя");
        customer.setMoney(scanner.nextDouble());scanner.nextLine();
        System.out.println("Покупатель инициирован: "+customers.toString());
        customers.add(customer);
        keeper.saveCustomers(customers);
    }

    private void addShoe() {
        System.out.println("Добавить обувь: ");
        Shoe shoe = new Shoe();
        System.out.println("Введите фирму обуви: ");
        shoe.setShoeFirm(scanner.nextLine());
        System.out.println("Введите размер обуви: ");
        shoe.setShoeSize(scanner.nextDouble());scanner.nextLine();
        System.out.println("Введите цену обуви: ");
        shoe.setShoePrice(scanner.nextDouble());scanner.nextLine();
        System.out.print("Введите количество пар обуви: ");
        shoe.setQuantity(scanner.nextInt());scanner.nextLine();
        shoe.setCount(shoe.getQuantity());
        System.out.println("Обувь инициирована: "+shoes.toString());
        shoes.add(shoe);
        keeper.saveShoes(shoes);
    }

    private void listShoe() {
        System.out.println("Список кроссовок: ");
        for (int i = 0; i < shoes.size(); i++) {
            if(shoes.get(i) != null && shoes.get(i).getCount() > 0){
                System.out.printf("%d. Фирма: %s. Размер: %s. Цена: %s%n.",
                            i+1,
                            shoes.get(i).getShoeFirm(),
                            shoes.get(i).getShoeSize(),
                            shoes.get(i).getShoePrice()
                            );
            }

        }
    }

    private void listCustomer() {
        System.out.println("Список покупателей: ");
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i) != null){
                System.out.printf("%d. %s %s. тел.: %s%n. кошелек: %s%n."
                       ,i+1
                       ,customers.get(i).getFirstname()
                       ,customers.get(i).getLastname()
                       ,customers.get(i).getPhone()
                       ,customers.get(i).getMoney()
                       );
            }
        }
    }

    private void purchaseShoe() {
        System.out.println("Список кроссовок: ");
        listShoe();
        
        System.out.println("Выберите номер обуви: ");
        int numberShoe = scanner.nextInt(); scanner.nextLine();
        
        System.out.println("Список покупателей: ");
        listCustomer();
        
        System.out.println("Выберите номер покупателя: ");
        int numberCustomer = scanner.nextInt(); scanner.nextLine();
        
        
        
        Purchase purchase = new Purchase();
        purchase.setShoe(shoes.get(numberShoe - 1));
        purchase.setCustomer(customers.get(numberCustomer - 1));
        Calendar c = new GregorianCalendar();
        purchase.setPurchaseShoe(c.getTime());
        if(purchase.getCustomer().getMoney()>=purchase.getShoe().getShoePrice()){
            System.out.printf("Обувь %s %s купил %s %s за %.2f евро %s%n",
                    purchase.getShoe().getShoeFirm(),
                    purchase.getShoe().getShoeSize(),
                    purchase.getCustomer().getFirstname(),
                    purchase.getCustomer().getLastname(),
                    purchase.getShoe().getShoePrice(),
                    purchase.getPurchaseShoe()
            );
            purchase.getCustomer().setMoney(purchase.getCustomer().getMoney()-purchase.getShoe().getShoePrice());
            income.setGeneralMoney(income.getGeneralMoney()+purchase.getShoe().getShoePrice());
            purchases.add(purchase);
            purchase.getShoe().setCount(purchase.getShoe().getCount() - 1);
            keeper.saveShoes(shoes);
        }
        else{
        System.out.println("Этот пользователь не может совершить покупку, так как у него не хватает денег!");
        keeper.savePurchases(purchases);
    }
    
    }   

    private void Income() {
        System.out.println("Доход магазина: ");
        System.out.printf("Выручка магазина составляет: %.2f%n",income.getGeneralMoney());
        keeper.saveIncome(incomes);
        
    }

    private void addMoney() {
        System.out.println("Добавить в кошелек деньги");
        listCustomer();
        System.out.println("Выберите покупателя: ");
        int choice= scanner.nextInt(); scanner.nextLine();
        System.out.println("Введите сколько денег вы хотите добавить этому покупателю: ");
        int add= scanner.nextInt(); scanner.nextLine();
        customers.get(choice-1).setMoney(customers.get(choice-1).getMoney()+add);
        keeper.saveCustomers(customers);
    }

    private void changeCustomer() {
        System.out.println("Выберите пользователя, которого хотитие изменить: ");
        int n=0;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i)!=null) {
                System.out.printf("%d. %s %s. тел.: %s%n. кошелек: %s%n."
                ,i+1
                ,customers.get(i).getFirstname()
                ,customers.get(i).getLastname()
                ,customers.get(i).getPhone()
                ,customers.get(i).getMoney()
                );
            }
            n++;
        }
        if (n<1) {
            System.out.println("Нет зарегистрированных пользователей");
            return;
        }
        System.out.print("Выберите номер пользователя: ");
        int numberUser= getNumber();
        String repeat="yes";
        do{
            System.out.println("0: Выход");
            System.out.println("1: Изменить имя пользователя");
            System.out.println("2: Изменить фамилию пользователя");
            System.out.println("3: Изменить номер пользователя");
            System.out.println("Выберите номер параметра, который хотите изменить: ");
            int num=getNumber();
            switch(num){
                case 0:
                    repeat="no";
                    break;
                case 1:
                    System.out.print("Введите новое имя пользователя: ");
                    String newFirstname=scanner.nextLine();
                    customers.get(numberUser-1).setFirstname(newFirstname);
                    keeper.saveCustomers(customers);
                    break;
                case 2:
                    System.out.print("Введите новую фамилию пользователя: ");
                    String newLastname=scanner.nextLine();
                    customers.get(numberUser-1).setLastname(newLastname);
                    keeper.saveCustomers(customers);
                    break;
                case 3:
                    System.out.print("Введите новый номер пользователя: ");
                    String newPhone=scanner.nextLine();
                    customers.get(numberUser-1).setPhone(newPhone);
                    keeper.saveCustomers(customers);
                    break;
            }
         }while("yes".equals(repeat));
    
    }

    private void changeShoe() {
        System.out.println("Выберите обувь которую хотите изменить: ");
        listShoe();
        System.out.print("Выберите номер обуви: ");
        int numberModel= getNumber();
        String repeat="yes";
        do{
            System.out.println("0: Выход");
            System.out.println("1: Изменить фирму обуви");
            System.out.println("2: Изменить размер обуви");
            System.out.println("3: Изменить цену обуви");
            System.out.println("4: Изменить количество экземпляров");
            System.out.println("Выберите номер параметра, который хотите изменить: ");
            int num=getNumber();
            switch(num){
                case 0:
                    repeat="no";
                    break;
                case 1:
                    System.out.print("Введите новую фирму обуви: ");
                    String newShoeFirm = scanner.nextLine();
                    shoes.get(numberModel-1).setShoeFirm(newShoeFirm);
                    keeper.saveShoes(shoes);
                    break;
                case 2:
                    System.out.print("Введите новый размер обуви: ");
                    double newShoeSize = getNumber();
                    shoes.get(numberModel-1).setShoeSize(newShoeSize);
                    keeper.saveShoes(shoes);
                    break;
                case 3:
                    System.out.print("Введите новую цену модели: ");
                    double newShoePrice = getNumber();
                    shoes.get(numberModel-1).setShoePrice(newShoePrice);
                    keeper.saveShoes(shoes);
                    break;
                case 4:
                    System.out.print("Введите новое количество экземпляров: ");
                    int newQuantity = getNumber();
                    shoes.get(numberModel-1).setQuantity(newQuantity);
                    keeper.saveShoes(shoes);
                    break;
            }
         }while("yes".equals(repeat));
    }

    private void incomeMonth() {
        System.out.println("Доход за месяц");
        double income = 0;
        System.out.println("Введите год, за который надо вывести доход: ");
        int years = getNumber();
        System.out.println("Введите месяц (1-12), за который надо вывести доход: ");
        int chosenMonths = getNumber()-1;
        for (int i = 0; i < purchases.size(); i++) {
            Date date = purchases.get(i).getPurchaseShoe();
            boolean toSum = summator(date, chosenMonths, years);
            if (purchases.get(i)!=null & toSum) {
                income+=purchases.get(i).getShoe().getShoePrice();
            }
        }
        System.out.println("Доход магазина: ");
        System.out.printf("Выручка магазина составляет: %.2f%n",income);
        }

    private boolean summator(Date date, int chosenMonths, int years) {
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        if (month == chosenMonths) {
            return true;
        }else{
            return false;
        }
    }
    
}