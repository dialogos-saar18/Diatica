/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saar.coli.jython_dialogos_plugin;

import com.clt.diamant.*;
import com.clt.diamant.graph.Graph;
import com.clt.diamant.graph.Node;
import com.clt.diamant.graph.nodes.AbstractOutputNode;
import com.clt.diamant.graph.nodes.AbstractOutputNode.DefaultPromptType;
import com.clt.speech.SpeechException;
import com.clt.speech.tts.VoiceName;
import com.clt.diamant.graph.nodes.NodeExecutionException;
import com.clt.diamant.gui.NodePropertiesDialog;
import com.clt.script.exp.Value;
import com.clt.script.exp.values.StringValue;
import com.clt.xml.XMLReader;
import com.clt.xml.XMLWriter;
import org.xml.sax.SAXException;
import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/**
 * @author peter,borisenkov
 */
public class HabiticaNode extends Node {

    public static final String API_USER = "api-user";
    public static final String API_KEY = "api-key";
    public static final String RESULT_VAR = "resultVar";
    public static final String Eingang_VAR = "eingangsVar";

    public HabiticaNode(){
      this.addEdge();
      this.addEdge();
      this.setProperty(API_USER,"");
      this.setProperty(Eingang_VAR,"");
      this.setProperty(RESULT_VAR,"");
    }
    public Slot getSlot(String name) {
        List<Slot> slots = this.getGraph().getAllVariables(Graph.LOCAL);
        for (Slot slot : slots) {
            if (name.equals(slot.getName()))
                return slot;
        }
        throw new NodeExecutionException(this, "unable to find variable with name " + name);
    }

    @Override
    public Node execute(WozInterface wozInterface, InputCenter inputCenter, ExecutionLogger executionLogger){

        List<Slot> slotlist = this.getGraph().getAllVariables(Graph.LOCAL);
        String api-user = slotlist.get((slotlist.indexOf("api-user")));
        String api-key = slotlist.get((slotlist.indexOf("api-key")));

        HttpURLConnectionExample diaticaCo = new HttpURLConnectionExample();
        String result = "";
        try{
          //result = diaticaCo.sendPost(); //für POST
          result = diaticaCo.sendGet(); //für GET
          String varName = this.getProperty(RESULT_VAR).toString();
          Slot var = getSlot(varName);
          System.out.println(result);
          var.setValue(new StringValue(result));

          return getEdge(0).getTarget();
        } catch(Exception e) {
          System.out.println("Fehler: Bitte überprüfe deine Internetverbindung!");
          return getEdge(1).getTarget();
        }
    }

    @Override
    public JComponent createEditorComponent(Map<String, Object> properties) {
        JPanel p = new JPanel();
        JPanel horiz = new JPanel();
        horiz.add(new JLabel("api-user"));
        horiz.add(NodePropertiesDialog.createTextField(properties, API_USER));
        p.add(horiz);
        horiz = new JPanel();
        horiz.add(new JLabel("eingangsVar"));
        horiz.add(NodePropertiesDialog.createComboBox(properties, Eingang_VAR,
                this.getGraph().getallVariables(Graph.LOCAL)));
        p.add(horiz);
        horiz = new JPanel();
        horiz.add(new JLabel("return:"));
        horiz.add(NodePropertiesDialog.createComboBox(properties, RESULT_VAR,
                this.getGraph().getAllVariables(Graph.LOCAL)));
        p.add(horiz);
        return p;
    }

    @Override
    public void writeAttributes(XMLWriter out, IdMap uid_map) {
        super.writeAttributes(out, uid_map);
        Graph.printAtt(out, API_USER, this.getProperty(API_USER).toString());
        Graph.printAtt(out, API_KEY, this.getProperty(API_KEY).toString());
        Graph.printAtt(out, RESULT_VAR, this.getProperty(RESULT_VAR).toString());
        Graph.printAtt(out, Eingang_VAR, this.getProperty(Eingang_VAR).toString());
    }

    @Override
    public void readAttribute(XMLReader r, String name, String value, IdMap uid_map) throws SAXException {
        if (name.equals(API_USER) ||name.equals(RESULT_VAR))|| name.equals(Eingang_VAR)) {
            this.setProperty(name, value);
        } else {
            super.readAttribute(r, name, value, uid_map);
        }
    }


    @Override
    public void writeVoiceXML(XMLWriter xmlWriter, IdMap idMap) {
    }
}
