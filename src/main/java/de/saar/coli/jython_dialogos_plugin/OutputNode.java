/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saar.coli.jython_dialogos_plugin;

import com.clt.diamant.graph.nodes.AbstractOutputNode;
import com.clt.diamant.graph.nodes.AbstractOutputNode.DefaultPromptType;
import com.clt.speech.SpeechException;
import com.clt.speech.tts.VoiceName;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author koller
 */
public class OutputNode extends AbstractOutputNode {
    @Override
    public String getResourceString(String string) {
        return string;
    }

    @Override
    public List<VoiceName> getAvailableVoices() {
        return Collections.singletonList(new VoiceName("", null));
    }

    @Override
    public void speak(String string, Map<String, Object> map) throws SpeechException {
        System.err.println("speak: " + string);
        System.err.println(" -> with map: " + map);
    }

    @Override
    public void stopSynthesis() {
    }
    
}
