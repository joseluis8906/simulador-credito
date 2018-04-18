package com.simulador.credito;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class Console {
    private Double P;
    private Integer n;
    private Double i;
    private String salir;
    Scanner input;

    public Console(){
        input = new Scanner(System.in);
    }

    public void capturaDeDatos (){
        salir = "n";

        while(!salir.equals("s") && !salir.equals("S")){

            System.out.println("Simulador de Crédito");

            System.out.println("Ingrese el valor de la obligación:");
            P = input.nextDouble();

            System.out.println("Ingrese el interés efectivo mensual:");
            i = input.nextDouble();
            i = i / 100;

            System.out.println("Ingrese el número de cuotas:");
            n = input.nextInt();

            imprimirTablaAmortizacion();

            System.out.println("Salir s/n: ");
            salir = input.next();
        }

        System.exit(0);

    }

    public void imprimirTablaAmortizacion(){

        Double A = P*((i*Math.pow((1+i), n))/(Math.pow((1+i), n)-1));

        System.out.println("+-------------------------------------------------------+");
        System.out.println("|                  TABLA DE AMORTIZACION                |");
        System.out.println("+-------------------------------------------------------+");
        System.out.println(String.format("|%-8s||%-10s||%12s||%7s||%10s|", "Mes", "Saldo", "Amortización", "interés", "Cuota"));

        DecimalFormat df = new DecimalFormat("#.#");

        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setMinimumFractionDigits(0);
        nf.setMaximumFractionDigits(0);

        System.out.println("---------------------------------------------------------");
        System.out.println(String.format("|%-8d||%-10s||%12s||%7s||%10s|", 0, nf.format(P), nf.format(0.0), nf.format(0.0), nf.format(0)));

        Double saldo = P;
        for(int it = 1; it <= n; ++it){
            System.out.println("---------------------------------------------------------");
            Double interes = saldo*i;
            Double amortizacion = A - interes;
            saldo = saldo - amortizacion;
            System.out.println(String.format("|%-8d||%-10s||%12s||%7s||%10s|", it, nf.format(saldo), nf.format(amortizacion), nf.format(interes), nf.format(A)));
        }

        System.out.println("+-------------------------------------------------------+");

    }
}
