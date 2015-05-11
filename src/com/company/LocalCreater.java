package com.company;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Ilya on 11.05.2015.
 */
public class LocalCreater extends JFrame {

    JTextArea hostLang = new JTextArea();
    JTextArea translate = new JTextArea();
    private JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    JMenuBar menuBar = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenu settings = new JMenu("Settings");
    private JMenuItem create = new JMenuItem("Create");
    private JMenuItem settingsItem = new JMenuItem("Settings");
    private Settings sett = new Settings();
    private JTextField pathField = new JTextField();
    private JButton ok = new JButton("OK");
    private JButton close = new JButton("Cancel");
    private String path = "";
    public LocalCreater()
    {
        super("Creater");
        file.add(create);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JSONObject object = new JSONObject();
                String key = "";
                String value = "";
                try {
                    FileWriter host = new FileWriter("tmph.t");
                    FileWriter tr = new FileWriter("tmptr.t");
                    BufferedReader readerh = new BufferedReader(new FileReader("tmph.t"));
                    BufferedReader readertr = new BufferedReader(new FileReader("tmptr.t"));
                    host.write(hostLang.getText());
                    tr.write(translate.getText());
                    host.flush();
                    host.close();
                    tr.flush();
                    tr.close();

                    key = readerh.readLine();
                    value = readertr.readLine();
                    while (key != null) {
                        object.put(key, value);
                        key = readerh.readLine();
                        value = readertr.readLine();

                    }
                    readerh.close();
                    readertr.close();
                    FileWriter jsonwriter = new FileWriter("ru.json");
                    jsonwriter.write(object.toJSONString());
                    jsonwriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = pathField.getText();
                sett.setVisible(false);
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pathField.setText("");
                sett.setVisible(false);
            }
        });
        settingsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sett.pack();
                sett.setLocationRelativeTo(null);
                sett.setLayout(null);
                sett.setSize(new Dimension(400, 120));
                sett.setResizable(false);
                sett.setVisible(true);
                sett.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                pathField.setBounds(0, 0, 1000, 30);
                ok.setBounds(5, 45, 70, 30);
                close.setBounds(100, 45, 70, 30);
                sett.add(ok);
                sett.add(close);
                sett.add(pathField);
            }
        });


        settings.add(settingsItem);
        menuBar.add(file);
        menuBar.add(settings);
        add(menuBar);
        setJMenuBar(menuBar);
        hostLang.setBounds(0, 0, 1000, 1000);
        hostLang.setBounds(0, 0, 1000, 1000);
        jsp.setLeftComponent(hostLang);
        jsp.setRightComponent(translate);
        add(jsp);

    }


}
