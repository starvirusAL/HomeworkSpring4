package app.models;

import app.enums.Currency;
import lombok.Data;

@Data
public class InputItems {

  String name;
  String email;
  Integer age;
  String phoneNumber;
  Double valueM;
  String address;
  String password;
  Currency inputCurrency;
  int idCustomer;
  int idAccount;
  int idAccount2;
  int idEmployer;
  int checkPage;


}
