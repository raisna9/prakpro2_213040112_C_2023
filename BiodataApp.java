/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudiKasus;

/**
 *
 * @author Raisa Isna
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BiodataApp extends JFrame {
    private int selectedRowIndex = -1;
    private MyTableModel2 tableModel2;
    private JTextField textFieldNama;
    private JTextField textFieldNomorHP;
    private JRadioButton radioButton1;
    private JTextArea textAreaAlamat;
    private JButton EditButton;
    
    public BiodataApp(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(BiodataApp.this, 
                        "Anda yakin ingin keluar dari aplikasi?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    System.exit(0); // Keluar dari aplikasi
                }
            }
        });
        
        JLabel headerLabel = new JLabel("Form Biodata", SwingConstants.CENTER); 
        headerLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        headerLabel.setBounds(15, 10, 350, 20); 

        JLabel labelNama = new JLabel("Nama:"); 
        labelNama.setBounds(15, 40, 100, 10); 

        JTextField textFieldNama = new JTextField(); 
        textFieldNama.setBounds(15, 60, 320, 30); 
        
        JLabel labelNomorHP = new JLabel("Nomor HP:");
        labelNomorHP.setBounds(15, 95, 100, 10);
        
        JTextField textFieldNomorHP = new JTextField();
        textFieldNomorHP.setBounds(15, 110, 320, 30);
        
        JLabel labelRadio = new JLabel("Jenis Kelamin:");
        labelRadio.setBounds(15, 145, 350, 10);
        
        JRadioButton radioButton1 = new JRadioButton("Laki-Laki", true);
        radioButton1.setBounds(15, 165, 350, 30);
        
        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 195, 350, 30);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1); 
        bg.add(radioButton2);
        
        JLabel labelAlamat = new JLabel("Alamat:"); // Perbaikan nama variabel
        labelAlamat.setBounds(10, 230, 50, 20); // Perbaikan nama variabel
        
        JTextArea textAreaAlamat = new JTextArea(); // Perbaikan nama variabel
        textAreaAlamat.setBounds(10, 253, 320, 110); // Perbaikan nama variabel

        JButton button = new JButton("Simpan"); 
        button.setBounds(15, 375, 100, 40);
        
        JButton Simpantxtbutton = new JButton("Simpantxt"); 
        Simpantxtbutton.setBounds(120, 375, 100, 40); 
        
        JButton Hapusbutton = new JButton("Hapus"); 
        Hapusbutton.setBounds(225, 375, 100, 40);
        
        JButton Editbutton = new JButton("Edit"); 
        Editbutton.setBounds(330, 375, 100, 40);
        
        JTable table = new JTable(); 
        JScrollPane scrollableTable2 = new JScrollPane(table); 
        scrollableTable2.setBounds(15, 435, 450, 200); 

        MyTableModel2 tableModel2 = new MyTableModel2();
        table.setModel(tableModel2); 
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jenisKelamin = "";
                String nama = textFieldNama.getText();
                String nomorHP = textFieldNomorHP.getText();
                String alamat = textAreaAlamat.getText(); // Perbaikan nama variabel
                
                if (radioButton1.isSelected()) {
                    jenisKelamin = radioButton1.getText();
                }
                if (radioButton2.isSelected()) {
                    jenisKelamin = radioButton2.getText();
                }
               
                if (nama == null || nama.isEmpty() || alamat == null || alamat.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Harap isi semua inputan yang kosong.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                } 
                
                int konfirmasi = JOptionPane.showConfirmDialog(null,
                        "Apakah Anda yakin ingin menyimpan data?", "Konfirmasi Simpan", JOptionPane.YES_NO_OPTION);
               
                if (konfirmasi == JOptionPane.YES_OPTION) {
                    tableModel2.add(new ArrayList<>(Arrays.asList(nama, nomorHP, jenisKelamin, alamat)));
                } 
            }
        });
        
        Hapusbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int dialogResult = JOptionPane.showConfirmDialog(BiodataApp.this, 
                            "Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        tableModel2.removeRow(selectedRow);
                        clearInputFields();
                    }
                } else {
                    JOptionPane.showMessageDialog(BiodataApp.this, "Pilih baris yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
            Editbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                  ArrayList<String> rowData = tableModel2.getData().get(selectedRow);
                   textFieldNama.setText(rowData.get(0));
                   textFieldNomorHP.setText(rowData.get(1));
                    if (rowData.get(2).equals("Laki-Laki")) {
                        radioButton1.setSelected(true);
                        radioButton2.setSelected(false);
                    } else {
                        radioButton1.setSelected(false);
                        radioButton2.setSelected(true);
                    }
                    textAreaAlamat.setText(rowData.get(3));
                    tableModel2.remove(selectedRow);
                    JOptionPane.showMessageDialog(BiodataApp.this, "Edit data!");
                } else {
                    JOptionPane.showMessageDialog(BiodataApp.this, "Pilih baris yang akan diedit!");
                }
            }
        });
            
            // ActionListener untuk tombol Simpan Ke File
Simpantxtbutton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan Data ke File");
        
        // Filter untuk hanya menampilkan file dengan ekstensi .txt
        FileNameExtensionFilter filter = new FileNameExtensionFilter("File Teks (.txt)", "txt");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(BiodataApp.this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (PrintWriter writer = new PrintWriter(fileToSave)) {
                for (int i = 0; i < tableModel2.getRowCount(); i++) {
                    String nama = tableModel2.getValueAt(i, 0).toString();
                    String NomorHP = tableModel2.getValueAt(i, 1).toString();
                    String jenisKelamin = tableModel2.getValueAt(i, 2).toString();
                    String alamat = tableModel2.getValueAt(i, 3).toString();

                    // Tulis data ke file
                    writer.println("Nama: " + nama);
                    writer.println("NomorHP: " + NomorHP);
                    writer.println("Jenis Kelamin: " + jenisKelamin);
                    writer.println("Alamat: " + alamat);
                    writer.println();
                }
                writer.close();
                JOptionPane.showMessageDialog(BiodataApp.this, "Data telah disimpan ke file.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(BiodataApp.this, "Gagal menyimpan data ke file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});

        
        this.add(headerLabel);
        this.add(button);
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelNomorHP);
        this.add(textFieldNomorHP);
        this.add(labelRadio);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(scrollableTable2);
        this.add(labelAlamat);
        this.add(textAreaAlamat);
        this.add(Hapusbutton);
        this.add(Editbutton);
        this.add(Simpantxtbutton);

        this.setSize(400, 550);
        this.setLayout(null);
    }
    

    // Metode untuk membersihkan input fields
    private void clearInputFields() {
    textFieldNama.setText("");
    textFieldNomorHP.setText("");
    radioButton1.setSelected(true);
    textAreaAlamat.setText("");
}

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BiodataApp b4 = new BiodataApp();
                b4.setVisible(true);
            }
        });
        
    }
    
}
