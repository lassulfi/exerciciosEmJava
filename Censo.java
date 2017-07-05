/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loucademiajava.exercicios;

import java.util.Scanner;

/**
 *
 * @author luis.assulfi
 */
public class Censo {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        int idade, sexo, pessoas = 10, masculino = 0, feminino = 0;
        
        for (int i = 0; i < pessoas; i++){
            System.out.print("Informe a idade -> ");
            idade = scanner.nextInt();
            System.out.println();
            System.out.print("Informe o sexo (1 - Masc. e 2 - Femin. -> ");
            sexo = scanner.nextInt();
            System.out.println();
            //Controle do erro
            if(sexo != 1 && sexo != 2){
                System.out.print("Opcao de sexo invalida! Digite novamente! -> ");
                sexo = scanner.nextInt();
                System.out.println();
                i--;
                if (i < 0){
                    i = 0;
                }
            } 
            else if (sexo == 1 && idade >= 18){
                masculino++;
            } else if (sexo == 2 && idade >=18){
                feminino++;
            }
        } 
        
        System.out.println("O número de mulheres maiores de 18 anos é " 
                + feminino);
        System.out.println("O número de homens maiores de 18 anos é " 
                + masculino);
        
    }
    
}
