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

        fCompare();
        fResult();
    }
    static int j; //счетчик
    static int [][] numIp = new int[2][4];
    
    
    //Проверка ввода
    static int fCheck(String temp){
        //System.out.println("функция fCheck Проверка ввода");
        //массив уровней сетей
        int[] ip = new int[4]; 
               
            //Считывание ввода, разделение сетей на уровни
            //с помощью поиска и исключения разделителя "."
        Pattern p = Pattern.compile("[.]"); //Определение разделителя
        Scanner scan = new Scanner(temp).useDelimiter(p);
        
        //Проверка правильности ввода
        try{
            if (scan.hasNextInt()){ // Проверка ввода данных типа int
                for(int i=0;i < ip.length;i++){
                    ip[i] = scan.nextInt();
                    // Проверка попадания в допустимый (0-255) дапазон
                    if (ip[i]>255 ||ip[i]<0 ){
                        System.out.println("Введен недопустимый IP адрес");
                        return 1;
                    }
                    else{
                        //System.out.println("уровень №"+ i+ "" + j + " " + ip[i]);
                        numIp[j][i] = ip[i];
                    }
                }
            }
            // Если введены данные не типа int
            else{ 
                System.out.println("Введены недопустимые символы"); 
                return 1;
            }    
        }
        catch(Exception e){
            System.out.println("Ошибка ввода!");
            return 1;           
        }    
    return 0;    
    }  
    //сравнение адресов на == > < замена местами при необходимости
    static void fCompare(){
  
            for(int i=0; i<4;){
                if (numIp[0][i]==numIp[1][i]){
                    i++;
                }
                else if (numIp[0][i]>numIp[1][i]){
                    break;
                }
                else if (numIp[0][i]<numIp[1][i]){ 
                    int [] numIpTemp = new int[4];
                    for(int j=0;j<4;j++){
                        numIpTemp[j] = numIp[1][j];
                        numIp[1][j] = numIp[0][j];
                        numIp[0][j] = numIpTemp[j];
                        i=4;
                    }
                }
               else 
                    System.out.print("ошибка");
            }      
    }
    
    //Вычесление IP адресов 
    // sn1.sn2.sn3.sn4
    static void fResult(){
        System.out.println("\n Списк допустимых адресов:");
        int sn0,sn1,sn2,sn3 ; // подсети subnet
        int max0,max1,max2,max3; // максимальный размер подсети
        int min0,min1,min2,min3; // минимальный размер подсети
        int i;
        int[]ip = new int[4];
        // подсеть первого уровня (0)
        for(sn0=numIp[1][0];sn0<=numIp[0][0];sn0++){
                
            if (sn0>numIp[1][0]){
                min1=0;max1 = 255;
            }
            
            else{
                min1 = numIp[1][1];max1 = 255;
            }  
            if(sn0==numIp[0][0]){
                max1 = numIp[0][1];
            } 
            // подсеть второго уровня (1)    
            for(sn1=min1;sn1<=max1;sn1++){
                
                if (sn1>numIp[1][1]||sn0>numIp[1][0]){
                     min2=0;max2 = 255;
                }
                else{
                    min2 = numIp[1][2];max2 = 255;
                }                    
                if(sn1==numIp[0][1]&&sn0==numIp[0][0]){
                    max2 = numIp[0][2];
                } 
                // подсеть третьего уровня (2) 
                for(sn2=min2;sn2<=max2;sn2++){
                    
                    if (sn2>numIp[1][2]||sn1>numIp[1][1]||sn0>numIp[1][0]){
                        min3=0;max3 = 255;
                    }
                    else{
                        min3 = numIp[1][3]+1;max3 = 255;
                    }                    

                    if(sn2==numIp[0][2]&&sn1==numIp[0][1]&&sn0==numIp[0][0]){
                        max3 = numIp[0][3]-1;
                    }
                    // подсеть четвертого уровня (3)                
                    for(sn3=min3;sn3<=max3;sn3++){
                        System.out.println(sn0+"."+sn1+"."+sn2+"."+sn3);
                    }
                        
                }
            }
        }
    }   
}
