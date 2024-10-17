package com.example.myshopjava.Service;

import com.example.myshopjava.Domain.Transaction;
import com.example.myshopjava.Domain.Product;
import com.example.myshopjava.Domain.TransactionType;
import com.example.myshopjava.Repository.TransactionRepository;
import com.example.myshopjava.Repository.ProductRepository;
import com.example.myshopjava.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
public class RestService {

    UserRepository userRepository;
    ProductRepository productRepository;
    TransactionRepository transactionRepository;

    public static final String URL = "http://localhost:8080/products";
    private final RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) throws Exception {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    public RestService(UserRepository userRepository, ProductRepository productRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction addTransaction(Transaction transaction) throws Exception {
        return execute(() -> restTemplate.postForObject(URL, transaction, Transaction.class));
    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    List<Product> getAllProducts() throws Exception {
        System.out.println("GET ALL PRODUCTS FROM REST SERVICE");
        return productRepository.getAll();
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Product product) throws Exception {
        System.out.println("Creating Product...");
        productRepository.add(product);

        // By adding a new product with certain quantity and price
        // We create automatically a new arrival in the shop

        // Selecting our new entry's ID

        Integer id = -1;
        for (Transaction t: transactionRepository.getAll())
            if (t.getId() > id)
                id = t.getId();
        id += 1;

        // Save the current time of the entry

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(currentDate);

        // Save the new arrival in the repository

        Transaction transaction = new Transaction(id, TransactionType.ARRIVAL, time, product.getId(), product.getQuantity());
        addTransaction(transaction);

        // End the add-product-proccess with an ok response

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/arrivals", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Transaction arrival) throws Exception
    {
        System.out.println("Creating arrival...");
        transactionRepository.add(arrival);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/arrivals", method = RequestMethod.GET)
    List<Transaction> getAllArrivals() throws Exception {
        System.out.println("GET ALL ARRIVALS FROM REST SERVICE");
        return transactionRepository.getByType(TransactionType.ARRIVAL);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userError(Exception e) {
        return e.getMessage();
    }
}
