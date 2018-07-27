/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ip_address;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author дом
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Программа поиска допустимых IP адресов\n\n"
            + "Введите  диапазон IP адресов.");

        //Переменные для хранения ввода
        String[] inIp = new String[2];
        
        // Считывание ввода с клавиатуры
        for(int i=0;i<inIp.length;){
            System.out.print("Введите адрес: ");
            Scanner scnIn = new Scanner(System.in);
            String temp = scnIn.nextLine();
            inIp[i] = temp;//присваивание значения переменной для хранения ввода 
            j = i; // присваивание значения статическому счетчику
            if (fCheck(temp) == 1){
                System.out.print("Повторите ввoд адреса: ");
            }
            else {
                i++;
            }           
        }  
        
        for(int i=0; i<2;i++){
            for(int n=0; n<4;n++){
              System.out.print(numIp[i][n]+ " ");  
            }
            System.out.print("\n");
        }
    }
    static int j; //счетчик
    static int [][] numIp = new int[2][4];
    
    //Проверка ввода
    static int fCheck(String temp){
        //массив уровней сетей
        int[] ip = new int[4]; 
               
            //Считывание ввода, разделение сетей на уровни
            //с помощью поиска и исключения разделителя "."
        Pattern p = Pattern.compile("[.]"); //Определение разделителя
        Scanner scan = new Scanner(temp).useDelimiter(p);
        
        try{
            if (scan.hasNextInt()){
                for(int i=0;i < ip.length;i++){
                    ip[i] = scan.nextInt();

                    if (ip[i]>255 ||ip[i]<0 ){
                        System.out.println("Введен недопустимый IP адрес");
                        return 1;
                    }
                    else{
                        System.out.println("уровень №"+ i+ "" + j + " " + ip[i]);
                        numIp[j][i] = ip[i];
                    }
                }
            }
            else{
                System.out.println("Введены недопустимые символы");                
            }    
        }
        catch(Exception e){
            System.out.println("Ошибка ввода!");
            return 1;           
        }    
    return 0;    
    }  
    static void fCompare(){
        int tr=0;
        
        // если [0]>[1] искать дельту
        // если [0]<[1] поменять местами и искать дельту
        // если [0]=[1] -> сравнивать следующий уровень
        

            for(int i=0; i<numIp.length/2; ){
                if(numIp[0][i]>numIp[1][i])  
                    fResult(0);
                else if (numIp[0][i]<numIp[1][i]) 
                    fResult(1);
                else i++;
            }
           
            
    }
    
    
    
    static void fResult(int a){
        int[] delta = new int[4]; //вычисление разницы в адресах
        
        if (a==0) {
            for(int i=0; i<numIp.length/2; i++){
                delta[i] = numIp[0][i]-numIp[1][i]; 
                System.out.print("дельта "+delta[i]+" ");
            }  
        }
        
    }
    
    
}
