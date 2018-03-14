package com.citywalker.networktest;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Administrator on 2018/3/14.
 */

public class ContentHandler extends DefaultHandler {
    private static final String TAG = "ContentHandler";
    private String nodeName;
    private StringBuilder to;
    private StringBuilder from;
    private StringBuilder heading;
    private StringBuilder body;

    @Override
    public void startDocument() throws SAXException {
        to = new StringBuilder();
        from = new StringBuilder();
        heading = new StringBuilder();
        body = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeName = localName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("note".equals(localName)) {
            Log.d(TAG, "to is " + to.toString().trim());
            Log.d(TAG, "from is " + from.toString().trim());
            Log.d(TAG, "heading is " + heading.toString().trim());
            Log.d(TAG, "body is " + body.toString().trim());

            to.setLength(0);
            from.setLength(0);
            heading.setLength(0);
            body.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ("to".equals(nodeName)) {
            to.append(ch, start, length);
        } else if ("from".equals(nodeName)) {
            from.append(ch, start, length);
        } else if ("heading".equals(nodeName)) {
            heading.append(ch, start, length);
        } else if ("body".equals(nodeName)) {
            body.append(ch, start, length);
        }
    }

}
