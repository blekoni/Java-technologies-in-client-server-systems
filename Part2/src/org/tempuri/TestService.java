package org.tempuri;

import java.rmi.RemoteException;
import java.util.Scanner;

import org.tempuri.CalculatorStub.Multiply;
import org.tempuri.CalculatorStub.MultiplyResponse;

public class TestService {

  public static void main(String[] args) throws RemoteException {
       Scanner in = new Scanner(System.in);
      CalculatorStub stub = new CalculatorStub("http://www.dneonline.com/calculator.asmx?WSDL");
      System.out.println("Print some number:");
        int a = in.nextInt();
      
      Multiply multiply = new Multiply();
      multiply.setIntA(a);
      multiply.setIntB(a);
      MultiplyResponse response= stub.multiply(multiply);
      int result = response.getMultiplyResult();
      System.out.println("Pow is " + result);
  }

}