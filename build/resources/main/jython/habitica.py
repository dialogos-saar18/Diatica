
from com.clt.diamant.graph import Node
from javax.swing import JLabel, BorderFactory, JTabbedPane, JPanel
from java.awt import GridBagLayout, GridBagConstraints, Insets

from com.clt.diamant.gui import NodePropertiesDialog


class Habitica(Node):
    def __init__(self, java_node):
        # remember Java object for this node, so we can access it later
        self.java_node = java_node

        # output nodes have one port for an outgoing edge
        java_node.addEdge()

        #
        self.API_USER = "api-user"
        self.API_KEY = "api-key"
        self.WAIT = "wait"

        java_node.setProperty(self.API_USER, "")
        java_node.setProperty(self.API_KEY, "")
        java_node.setProperty(self.WAIT, True)


    def execute(self, wozInterface, inputCenter, executionLogger):
        print(self.java_node.getProperty(self.API_USER))
        print(self.java_node.getProperty(self.API_KEY))

        return self.java_node.getEdge(0).getTarget() # jump to next connected node

    def createEditorComponent(self, properties):
        p = JPanel(GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3))

        gbc = GridBagConstraints()
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.weightx = 0.0
        gbc.weighty = 0.0
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = Insets(3, 3, 3, 3)

        p.add(JLabel("api_user:"), gbc)
        gbc.gridx = 1
        editor = NodePropertiesDialog.createTextArea(properties, self.API_USER)
        p.add(editor, gbc)

        p.add(JLabel("api_key:"), gbc)
        gbc.gridx = 1
        editor = NodePropertiesDialog.createTextArea(properties, self.API_KEY)
        p.add(editor, gbc)

        gbc.gridx, gbc.gridy = 1,0
        p.add(NodePropertiesDialog.createCheckBox(properties, self.WAIT, "Wait until done"), gbc)


        jtp = JTabbedPane()
        jtp.addTab("Credentials", p)
        return jtp



        #return JLabel("hello from TestNode")
