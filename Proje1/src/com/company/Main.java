package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Stream;

public class Main  {

    public static void main(String[] args) throws IOException {

        //Kodun bu kısmında Arayüz oluşturuyoruz.

        JFrame f = new JFrame("Ekran");
        JTextField textField = new JTextField();
        JPanel panel = new JPanel();
        JTextArea textArea = new JTextArea();
        JLabel label = new JLabel("");
        String salaries = "";
        BinaryTree tree = new BinaryTree(textArea,salaries);



        JButton b1 = new JButton("Calisan Goruntule");
        JButton b2 = new JButton("Calisan ekle");
        JButton b3 = new JButton("Calisan cikar");
        JButton b4 = new JButton("Calisan min maas");
        JButton b5 = new JButton("Calisan max maas");

        JButton sirala = new JButton("Sırala");

        //Binary Tree'mizi oluşturuyoruz.
        tree.root = tree.insert(tree.root,"Caner","int",15000);
        tree.insert(tree.root,"Caner","int1",20000);
        tree.insert(tree.root,"Caner","int2",10000);
        tree.insert(tree.root,"Caner","int3",7000);
        tree.insert(tree.root,"Caner","int4",2000);
        tree.insert(tree.root,"Caner","int5",30000);
        tree.insert(tree.root,"Caner","int6",25000);
        tree.insert(tree.root,"Caner","int7",13000);



        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        f.setBounds(400,150,1000,800);

        textArea.setBounds(400,50,300,200);
        sirala.setBounds(100,300,150,30);
        b1.setBounds(100,50,150,30);
        b2.setBounds(100,100,150,30);
        b3.setBounds(100,150,150,30);
        b4.setBounds(100,200,150,30);
        b5.setBounds(100,250,150,30);
        label.setBounds(100,430,200,30);


        sirala.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                textArea.setText("");
                Node temp=tree.root;
                Stack<Node> stack=new Stack<>();

                // inorder gezinme
                while (temp!=null||!stack.isEmpty()){
                    if(temp!=null){
                        stack.add(temp);
                        temp=temp.left;
                    }
                    else {
                        temp=stack.pop();
                        textArea.setText(textArea.getText()  +  temp.data+" " + "\n");

                        temp=temp.right;
                    }
                }



            }
        });

        //Bu butonumuz Çalışanları sıralıyor.

        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                textArea.setText("");
                File filer = new File("Calisanlar.txt");
                try {
                    BufferedReader br = new BufferedReader(new FileReader(filer));
                    String st;
                    // Condition holds true till
                    // there is character in a string
                    while ((st = br.readLine()) != null)

                        // Print the string
                        textArea.setText(textArea.getText()+st +"\n");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });

        //Bu butonumuz Çalışan ekleme arayüzüne gidiyor.

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // İkinci arayüzümüzü yani çalışan arayüzü ekleme ve silme arayüzümüzü oluşturuyoruz.

                f.dispose();
                JFrame f2 = new JFrame();
                JPanel panel1 = new JPanel();
                JButton btnekle = new JButton("Ekle");
                JButton btngeri = new JButton("Geri");
                f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                f2.setBounds(400,150,1000,800);

                JLabel name = new JLabel("AD : ");
                JLabel surname = new JLabel("SOYAD : ");
                JLabel salary = new JLabel("MAAŞ : ");
                JLabel info = new JLabel("");

                JTextField isim = new JTextField();
                JTextField soyisim = new JTextField();
                JTextField maas = new JTextField();



                btngeri.setBounds(20,20,70,30);




                name.setBounds(100,100,150,30);
                surname.setBounds(100,150,150,30);
                salary.setBounds(100,200,150,30);


                isim.setBounds(300,100,150,30);
                soyisim.setBounds(300,150,150,30);
                maas.setBounds(300,200,150,30);
                btnekle.setBounds(320,250,100,30);
                info.setBounds(320, 300,150,30);




                btngeri.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        // 'geri' butonuna basınca ilk ekranımıza dönme

                        f2.dispose();
                        f.setVisible(true);


                    }
                });

                //Çalışan ekleme butonu

                btnekle.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        FileWriter file = null;


                        try {
                            file = new FileWriter("Calisanlar.txt",true);
                            BufferedWriter w = new BufferedWriter(file);


                            w.write("Isim: "+ isim.getText() + " Soyisim: " + soyisim.getText() + " Maas: " + maas.getText());

                            w.newLine();

                            w.close();

                            info.setText("Maaş eklendi.");



                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        tree.insert(tree.root,isim.getText(),soyisim.getText(),Integer.parseInt(maas.getText()));


                    }
                });


                f2.add(name);
                f2.add(surname);
                f2.add(salary);

                f2.add(isim);
                f2.add(soyisim);
                f2.add(maas);
                f2.add(btnekle);
                f2.add(btngeri);
                f2.add(info);




                f2.add(panel1);





                f2.setVisible(true);


            }
        });

        //Silme arayüzümüze gidiyor.
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                JFrame f2 = new JFrame();
                JPanel panel1 = new JPanel();
                JButton btnsil = new JButton("Sil");
                JButton btngeri = new JButton("Geri");
                f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                f2.setBounds(400,150,1000,800);

                JLabel name = new JLabel("AD : ");
                JLabel surname = new JLabel("SOYAD : ");
                JLabel salary = new JLabel("MAAŞ : ");
                JLabel info = new JLabel("");

                JTextField isim = new JTextField();
                JTextField soyisim = new JTextField();
                JTextField maas = new JTextField();



                btngeri.setBounds(20,20,70,30);




                name.setBounds(100,100,150,30);
                surname.setBounds(100,150,150,30);
                salary.setBounds(100,200,150,30);


                isim.setBounds(300,100,150,30);
                soyisim.setBounds(300,150,150,30);
                maas.setBounds(300,200,150,30);
                btnsil.setBounds(320,250,100,30);
                info.setBounds(320, 300,150,30);
                btngeri.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //'geri' butonu

                        f2.dispose();
                        f.setVisible(true);


                    }
                });


                //Silme butonu

                btnsil.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                       tree.removeLineFromFile("Calisanlar.txt", "Isim: "+isim.getText()+" Soyisim: "+soyisim.getText()+" Maas: "+maas.getText());

                       /* */


                        info.setText("Maaş silindi.");

                    }
                });



                f2.add(name);
                f2.add(surname);
                f2.add(salary);

                f2.add(isim);
                f2.add(soyisim);
                f2.add(maas);
                f2.add(btnsil);
                f2.add(btngeri);
                f2.add(info);




                f2.add(panel1);





                f2.setVisible(true);
            }
        });

        // Minimum maaşı bulma
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("");
                textArea.setText("Minimum maaş : " + tree.min(tree.root));
            }
        });

        //Maximum maaşı bulma
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("");
                textArea.setText("Maximum maaş : " + tree.max(tree.root));
            }
        });



        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b5);
        f.add(label);
        f.add(sirala);
        f.add(textArea);


        f.add(panel);

        f.setVisible(true);



    }
}
