package com.evabui.labb3;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

// Inspiration ifrån https://www.tutorialspoint.com/android/android_xml_parsers.htm
public class ArtistParser {

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    public static List<String> parse(InputStream in) {
        List<String> result = new ArrayList<>();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc  = dBuilder.parse(in);
            Element element = doc.getDocumentElement();
            element.normalize();
            NodeList artists = doc.getElementsByTagName("artist");
            for(int i = 0; i < artists.getLength(); i++) {
                Node node = artists.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element artistElement = (Element) node;
                    result.add(getValue("name", artistElement));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
