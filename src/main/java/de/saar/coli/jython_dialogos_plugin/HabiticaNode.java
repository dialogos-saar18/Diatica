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
import java.util.*;
/**
 * @author peter,borisenkov
 */
public class HabiticaNode extends Node {

    public static final String RESULT_VAR = "resultVar";
    public static final String Eingang_VAR = "eingangsVar";

    public HabiticaNode(){
      addEdge();
      addEdge();
      setProperty(Eingang_VAR,"");
      setProperty(RESULT_VAR,"");
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
        Slot apiuser = slotlist.get(0);
        Slot apikey = slotlist.get(3);
        String xapiuser = (apiuser.getValue()).getReadableValue().toString();
        String xapikey = (apikey.getValue()).getReadableValue().toString();
        String eingabe = getSlot(this.getProperty(Eingang_VAR).toString()).getValue().getReadableValue().toString();
        //System.out.println(xapiuser);
        HttpURLConnectionExample diaticaCo = new HttpURLConnectionExample();
        String result = "";
        System.out.println(eingabe);

        if (eingabe.equals("hp")){
          try{
<<<<<<< HEAD
            //result = diaticaCo.sendPost(xapiuser, xapikey); //für POST
            result = diaticaCo.sendGet(xapiuser, xapikey); //für GET
            System.out.println("Ich bin in hp.");
=======

            result = diaticaCo.sendGet(xapiuser, xapikey,"https://habitica.com/api/v3/user?userFields=stats.hp"); //für GET

>>>>>>> 2446ced753ca24bfdde585ef488fbfd5bfecd6ca
            String varName = this.getProperty(RESULT_VAR).toString();
            Slot var = getSlot(varName);
            var.setValue(new StringValue(result));

            return getEdge(0).getTarget();
          } catch(Exception e) {
            System.out.println("Fehler: Bitte überprüfe deine Internetverbindung!");
            return getEdge(1).getTarget();
          }
<<<<<<< HEAD
        }
        else if(eingabe.equals("\"test\"")){
            try{
                System.out.println("Ich bin in Test.");
                result = diaticaCo.sendGet(xapiuser, xapikey); //für GET

                String varName = this.getProperty(RESULT_VAR).toString();
                Slot var = getSlot(varName);
                var.setValue(new StringValue(result));
    
                return getEdge(0).getTarget();
              } catch(Exception e) {
                System.out.println("Fehler: Bitte überprüfe deine Internetverbindung!");
                return getEdge(1).getTarget();
            }
          }
        else {
=======
        } else if(eingabe.equals("sleep")){ // TODO sleep zu sleep und wake up 
>>>>>>> 2446ced753ca24bfdde585ef488fbfd5bfecd6ca
          try{
            result = diaticaCo.sendPost(xapiuser, xapikey,"https://habitica.com/api/v3/user/sleep"); //für POST

            String varName = this.getProperty(RESULT_VAR).toString();
            Slot var = getSlot(varName);
            var.setValue(new StringValue(result));

            return getEdge(0).getTarget();
          } catch(Exception e) {
            System.out.println("Fehler: Bitte überprüfe deine Internetverbindung!");
            return getEdge(1).getTarget();
          }
        }else if (eingabe.equals("exp")){
          try{
            result = diaticaCo.sendGet(xapiuser, xapikey,"https://habitica.com/api/v3/user?userFields=stats.exp"); //für GET
            String varName = this.getProperty(RESULT_VAR).toString();
            Slot var = getSlot(varName);
            var.setValue(new StringValue(result));

            return getEdge(0).getTarget();
          } catch(Exception e) {
              System.out.println("Fehler: Bitte überprüfe deine Internetverbindung!");
              return getEdge(1).getTarget();
            }
        }
        else {

          return getEdge(1).getTarget();
        }
    }

    @Override
    public JComponent createEditorComponent(Map<String, Object> properties) {
        JPanel p = new JPanel();
        JPanel horiz = new JPanel();
        horiz.add(new JLabel("eingangsVar:"));
        horiz.add(NodePropertiesDialog.createComboBox(properties, Eingang_VAR,
                this.getGraph().getAllVariables(Graph.LOCAL)));
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
        Graph.printAtt(out, RESULT_VAR, this.getProperty(RESULT_VAR).toString());
        Graph.printAtt(out, Eingang_VAR, this.getProperty(Eingang_VAR).toString());
    }

    @Override
    public void readAttribute(XMLReader r, String name, String value, IdMap uid_map) throws SAXException {
        if (name.equals(RESULT_VAR)|| name.equals(Eingang_VAR)) {
            this.setProperty(name, value);
        } else {
            super.readAttribute(r, name, value, uid_map);
        }
    }


    @Override
    public void writeVoiceXML(XMLWriter xmlWriter, IdMap idMap) {
    }
}
