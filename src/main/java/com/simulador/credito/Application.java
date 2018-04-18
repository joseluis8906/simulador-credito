package com.simulador.credito;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class Application {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Simulador de Crédito");

        Double P = 0.0;
        Integer n = 0;
        Double i = 0.0;

        System.out.println("Ingrese el valor de la obligación:");
        P = input.nextDouble();

        System.out.println("Ingrese el número de cuotas:");
        n = input.nextInt();

        System.out.println("Ingrese el interés efectivo mensual:");
        i = input.nextDouble();
        i = i / 100;

        ImprimirTablaAmortizacion(P, n, i);
    }

    public static void ImprimirTablaAmortizacion (
            Double P, Integer n, Double i) {

        Double A = P*((i*Math.pow((1+i), n))/(Math.pow((1+i), n)-1));

        System.out.println("+-------------------------------------------------------+");
        System.out.println("|                  TABLA DE AMORTIZACION                |");
        System.out.println("+-------------------------------------------------------+");
        System.out.println(String.format("|%-8s||%-10s||%12s||%7s||%10s|", "Mes", "Saldo", "Amortización", "interés", "Cuota"));

        DecimalFormat df = new DecimalFormat("#.#");

        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);

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
