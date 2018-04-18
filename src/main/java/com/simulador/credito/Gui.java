package com.simulador.credito;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Vector;

public class Gui extends JFrame implements ActionListener, PropertyChangeListener {
    private Double P;
    private Integer n;
    private Double i;

    private final String[] nombresDeColumnas = {
            "Mes",
            "Saldo",
            "Amortización",
            "Interés",
            "Cuota"
    };
    private Vector<String[]> data;

    private JLabel lblObligacion;
    private NumberFormat currencyObligacion;
    private JFormattedTextField txtObligacion;

    private JLabel lblInteres;
    private NumberFormat percentInteres;
    private JFormattedTextField txtInteres;

    private JLabel lblMeses;
    private JTextField txtMeses;

    private JButton btnCalcular;
    private JTable tablaDeAmortizacion;

    public Gui () {
        setUpFormats();

        JPanel panel =  new JPanel(new MigLayout());

        lblObligacion = new JLabel("Obligación:");
        txtObligacion = new JFormattedTextField(currencyObligacion);
        txtObligacion.setValue(0);

        lblInteres = new JLabel("Interés:");
        txtInteres = new JFormattedTextField(percentInteres);
        txtInteres.setValue(0);

        lblMeses = new JLabel("Meses:");
        txtMeses = new JTextField();
        txtMeses.setText("0");

        btnCalcular = new JButton("Calcular");

        data = new Vector<String[]>();
        tablaDeAmortizacion = new JTable(data, new Vector<String>(Arrays.asList(nombresDeColumnas)));
        JScrollPane sp=new JScrollPane(tablaDeAmortizacion);
        btnCalcular.addActionListener(this);

        panel.add(lblObligacion, "wrap");
        panel.add(txtObligacion, "width 100%, wrap");
        panel.add(lblInteres, "wrap");
        panel.add(txtInteres, "width 100%, wrap");
        panel.add(lblMeses, "wrap");
        panel.add(txtMeses, "width 100%, wrap");
        panel.add(btnCalcular, "wrap");
        panel.add(sp, "width 100%, height 100%");

        add(panel);
        pack();

        setTitle("Simulador De Crédito");
        setSize(512, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void actionPerformed (ActionEvent evt) {
        P = Double.parseDouble(txtObligacion.getValue().toString());
        i = Double.parseDouble(txtInteres.getValue().toString());
        n = Integer.parseInt(txtMeses.getText());

        DecimalFormat df = new DecimalFormat("#.#");
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setMinimumFractionDigits(0);
        nf.setMaximumFractionDigits(0);

        Double A = P*((i*Math.pow((1+i), n))/(Math.pow((1+i), n)-1));

        DefaultTableModel model = (DefaultTableModel) tablaDeAmortizacion.getModel();
        model.setRowCount(0);
        model.addRow(new String[]{"0", nf.format(P), nf.format(0.0), nf.format(0.0), nf.format(0)});

        Double saldo = P;
        for(int it = 1; it <= n; ++it){
            Double interes = saldo*i;
            Double amortizacion = A - interes;
            saldo = saldo - amortizacion;
            model.addRow(new String[]{Integer.toString(it), nf.format(saldo), nf.format(amortizacion), nf.format(interes), nf.format(A)});
        }
    }

    public void propertyChange(PropertyChangeEvent e) {}

    private void setUpFormats() {
        currencyObligacion = NumberFormat.getCurrencyInstance();
        currencyObligacion.setMaximumFractionDigits(0);
        currencyObligacion.setMinimumFractionDigits(0);

        percentInteres = NumberFormat.getPercentInstance();
        percentInteres.setMinimumFractionDigits(2);
        percentInteres.setMaximumFractionDigits(2);
    }
}
