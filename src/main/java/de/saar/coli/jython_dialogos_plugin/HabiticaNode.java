/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saar.coli.jython_dialogos_plugin;


import java.util.LinkedList;
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
import com.clt.script.exp.values.ListValue;
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
        Slot apiuser = new Slot();
        Slot apikey = new Slot();
        Slot searchtag = new Slot();

        try{
          for (int b = 0; b<slotlist.size(); b++){
            if ((slotlist.get(b)).getName().equals("apiuser")){
              apiuser = slotlist.get(b);
            }else if ((slotlist.get(b)).getName().equals("apikey")){
              apikey = slotlist.get(b);
            }else if ((slotlist.get(b)).getName().equals("tag")){
              searchtag = slotlist.get(b);
            }
          }
        } catch (IndexOutOfBoundsException e){
            throw new NodeExecutionException(this, "unable to find all necessary variables (apiuser, apikey, tag) ");
          }

        //Reading the Variables
        String xapiuser = (apiuser.getValue()).getReadableValue().toString();
        String xapikey = (apikey.getValue()).getReadableValue().toString();
        String eingabe = getSlot(this.getProperty(Eingang_VAR).toString()).getValue().getReadableValue().toString();
        HttpURLConnectionExample diaticaCo = new HttpURLConnectionExample();
        String result = "";
        System.out.println(eingabe);
        //making Requests based on Eingabe
        if (eingabe.equals("hp")){
          try{
            result = diaticaCo.sendGet(xapiuser, xapikey,"https://habitica.com/api/v3/user?userFields=stats.hp", null); //für GET
            String varName = this.getProperty(RESULT_VAR).toString();
            Slot var = getSlot(varName);
            var.setValue(new StringValue(result));

            return getEdge(0).getTarget();
          } catch(Exception e) {
            System.out.println("Fehler: Bitte überprüfe deine Internetverbindung!");
            return getEdge(1).getTarget();
          }
        } else if(eingabe.equals("sleep")){
          try{
            String sleepstatus = diaticaCo.sendPost(xapiuser, xapikey,"https://habitica.com/api/v3/user/sleep", null); //für POST
            if (sleepstatus.equals("wach")){
              diaticaCo.sendPost(xapiuser, xapikey,"https://habitica.com/api/v3/user/sleep", null);
              result = "Du schläfst bereits.";
            } else{
              result = "Du schläfst nun. ";
            }
            String varName = this.getProperty(RESULT_VAR).toString();
            Slot var = getSlot(varName);
            var.setValue(new StringValue(result));

            return getEdge(0).getTarget();
          } catch(Exception e) {
            System.out.println("Fehler: Bitte überprüfe deine Internetverbindung!");
            return getEdge(1).getTarget();
          }
        } else if(eingabe.equals("wake_up")){
            try{
              String sleepstatus = diaticaCo.sendPost(xapiuser, xapikey,"https://habitica.com/api/v3/user/sleep",null); //für POST
              if (sleepstatus.equals("schlaf")){
                 diaticaCo.sendPost(xapiuser, xapikey,"https://habitica.com/api/v3/user/sleep",null);
                 result = "Du bist bereits wach.";
              } else{
                result = "Du bist nun wach.";
              }
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
            result = diaticaCo.sendGet(xapiuser, xapikey,"https://habitica.com/api/v3/user?userFields=stats.exp", null); //für GET
            String varName = this.getProperty(RESULT_VAR).toString();
            Slot var = getSlot(varName);
            var.setValue(new StringValue(result));

            return getEdge(0).getTarget();
          } catch(Exception e) {
              System.out.println("Fehler: Bitte überprüfe deine Internetverbindung!");
              return getEdge(1).getTarget();
            }
        }
        //all due Task
        else if (eingabe.equals("all_due_tasks")){
          try{
            result = diaticaCo.sendGet(xapiuser, xapikey,"https://habitica.com/api/v3/tasks/user?type=dailys", ""); //für GET
            String varName = this.getProperty(RESULT_VAR).toString();
            Slot var = getSlot(varName);
            var.setValue(new StringValue(result));

            return getEdge(0).getTarget();

          }catch(Exception e) {
              System.out.println("Fehler: Bitte überprüfe deine Internetverbindung!");
              return getEdge(1).getTarget();
            }
        }
        //asking for specific Task
        else if (eingabe.equals("spec_task")){
          String spectag = new String();
          String idtag = new String();
          try {
            idtag = (searchtag.getValue()).getReadableValue().toString();
            spectag = diaticaCo.sendGet(xapiuser,xapikey, "https://habitica.com/api/v3/tags",idtag);
            result = diaticaCo.sendGet(xapiuser, xapikey,"https://habitica.com/api/v3/tasks/user?type=dailys", spectag);

            String varName = this.getProperty(RESULT_VAR).toString();
            Slot var = getSlot(varName);
            var.setValue(new StringValue(result));

            return getEdge(0).getTarget();

          }catch(Exception e) {
              System.out.println("Fehler: Bitte überprüfe deine Internetverbindung!");
              return getEdge(1).getTarget();
            }
          }
        //Tags are added
        else if (eingabe.equals("add_tags")){
          try{
              LinkedList tags = new LinkedList<String>();
              tags.add("1h");
              tags.add("30min");
              tags.add("15min");

              for (int i=0; i< tags.size(); i++){
                diaticaCo.sendPost(xapiuser, xapikey,"https://habitica.com/api/v3/tags",(tags.get(i)).toString()); //für GET
              }
              result = "Ich habe die Tags für dich hinzugefügt";
              String varName = this.getProperty(RESULT_VAR).toString();
              Slot var = getSlot(varName);
              var.setValue(new StringValue(result));

              return getEdge(0).getTarget();

          }catch(Exception e) {
              System.out.println("Fehler: Bitte überprüfe deine Internetverbindung!");
              return getEdge(1).getTarget();
            }
        }
        else if (eingabe.equals("get_taglist")){
          try{
            result = diaticaCo.sendGet(xapiuser, xapikey,"https://habitica.com/api/v3/tags", "taglist"); //für GET
            String varName = this.getProperty(RESULT_VAR).toString();
            Slot var = getSlot(varName);

            var.setValue(new StringValue(result));

            return getEdge(0).getTarget();

          }catch(Exception e) {
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
        horiz.add(new JLabel("eingang:"));
        horiz.add(NodePropertiesDialog.createComboBox(properties, Eingang_VAR,
                this.getGraph().getAllVariables(Graph.LOCAL)));
        p.add(horiz);
        horiz = new JPanel();
        horiz.add(new JLabel("ausgabe:"));
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
