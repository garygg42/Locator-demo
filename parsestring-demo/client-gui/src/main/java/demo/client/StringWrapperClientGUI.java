package demo.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.common.StringWrapper;
import demo.common.StringWrapperService;
import javax.swing.JTextArea;

public class StringWrapperClientGUI {

    private JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StringWrapperClientGUI window = new StringWrapperClientGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public StringWrapperClientGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 330);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("Input");
        panel.add(lblNewLabel);

        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(30);

        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        frame.getContentPane().add(textArea, BorderLayout.CENTER);

        JButton btnNewButton = new JButton("Parse");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClassPathXmlApplicationContext context = null;

                context = new ClassPathXmlApplicationContext(
                        new String[] { "META-INF/spring/beans.xml" });

                StringWrapperService client = (StringWrapperService) context
                        .getBean("stringWrapperClient");

                JAXBContext jaxbContext;
                final StringWriter stringWriter = new StringWriter();
                try {
                    jaxbContext = JAXBContext.newInstance(StringWrapper.class);

                    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                    jaxbMarshaller.setProperty(
                            Marshaller.JAXB_FORMATTED_OUTPUT, true);

                    try {
                        client.getParsedString(textField.getText());
                    } catch (javax.xml.ws.soap.SOAPFaultException qwe) {
                        textArea.setText("Exception " + qwe.getMessage());
                    }

                    jaxbMarshaller.marshal(
                            client.getParsedString(textField.getText()),
                            stringWriter);

                    textArea.setText(stringWriter.toString());
                } catch (JAXBException je) {
                    je.printStackTrace();
                }

            }
        });
        panel.add(btnNewButton);

    }

}
