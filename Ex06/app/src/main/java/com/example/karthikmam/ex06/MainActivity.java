package com.example.karthikmam.ex06;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    Element nodeElement;
    Item[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(("<class>\n" +
                            "   <student rollno=\"393\">\n" +
                            "      <firstname>dinkar</firstname>\n" +
                            "      <lastname>kad</lastname>\n" +
                            "      <nickname>dinkar</nickname>\n" +
                            "      <marks>85</marks>\n" +
                            "   </student>\n" +
                            "   <student rollno=\"493\">\n" +
                            "      <firstname>Vaneet</firstname>\n" +
                            "      <lastname>Gupta</lastname>\n" +
                            "      <nickname>vinni</nickname>\n" +
                            "      <marks>95</marks>\n" +
                            "   </student>\n" +
                            "   <student rollno=\"593\">\n" +
                            "      <firstname>jasvir</firstname>\n" +
                            "      <lastname>singn</lastname>\n" +
                            "      <nickname>jazz</nickname>\n" +
                            "      <marks>90</marks>\n" +
                            "   </student>\n" +
                            "</class>").getBytes()));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("student");
            items = new Item[nodeList.getLength()];
            for(int i=0; i < nodeList.getLength(); i++) {
                nodeElement = (Element) nodeList.item(i);

                Item item = new Item();
                item.from = nodeElement.getElementsByTagName("firstname").item(0).getTextContent();
                item.body = nodeElement.getElementsByTagName("lastname").item(0).getTextContent();
                items[i] = item;
            }

            ListView listView = (ListView) findViewById(R.id.lvItems);
            ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.item_list, items);
            listView.setAdapter(itemAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
