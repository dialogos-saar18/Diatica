/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saar.coli.jython_dialogos_plugin;

import com.clt.dialogos.plugin.Plugin;
import com.clt.dialogos.plugin.PluginRuntime;
import com.clt.dialogos.plugin.PluginSettings;
import com.clt.diamant.IdMap;
import com.clt.diamant.graph.Node;
import com.clt.xml.XMLReader;
import com.clt.xml.XMLWriter;
import java.awt.Component;
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.UIManager;
import org.xml.sax.SAXException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author koller
 */
public class JythonDialogosPlugin implements Plugin {

    @Override
    public String getId() {
        return "de.saar.coli.jython_dialogos_plugin";
    }

    @Override
    public String getName() {
        return "Habitica Plugin";
    }

    @Override
    public Icon getIcon() {
        return UIManager.getIcon("FileView.computerIcon");
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public void initialize() {
        Plugin.super.initialize(); //To change body of generated methods, choose Tools | Templates.

        Node.registerNodeTypes(com.clt.speech.Resources.getResources().createLocalizedString("IONode"),
                Arrays.asList(HabiticaNode.class));

        // This initializes all of Jython; do it now so we have it out of the way.
        new HabiticaNode();
    }

    @Override
    public void terminate() {
        Plugin.super.terminate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PluginSettings createDefaultSettings() {
        return new PluginSettings() {
            @Override
            public void writeAttributes(XMLWriter writer, IdMap idmap) {
            }

            @Override
            protected void readAttribute(XMLReader reader, String string, String string1, IdMap idmap) throws SAXException {
            }

            @Override
            public JComponent createEditor() {
                return new JLabel();
            }

            @Override
            protected PluginRuntime createRuntime(Component cmpnt) throws Exception {
                return () -> {

                };
            }
        };
    }

}
