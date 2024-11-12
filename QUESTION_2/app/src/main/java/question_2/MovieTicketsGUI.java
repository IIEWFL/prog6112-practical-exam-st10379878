/*
 * This source file was generated by the Gradle 'init' task
 */
package question_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MovieTicketsGUI extends JFrame {
    private JComboBox<String> movieComboBox;
    private JTextField numTicketsField;
    private JTextField tickPriceField;
    private JTextArea reportArea;

    public MovieTicketsGUI() {
        // Set up the frame
        setTitle("MOVIE TICKETS");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Close the application
            }
        });
        fileMenu.add(exitMenuItem);

        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem processReportMenuItem = new JMenuItem("Process");
        processReportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processReport();
                saveReport();
            }
        });
        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
      

        editMenu.add(processReportMenuItem);
        editMenu.add(clearMenuItem);
       

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        // Panel for the input fields and combo box
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        // Movie combo box
        JLabel movieLabel = new JLabel("MOVIE:");
        movieComboBox = new JComboBox<>(new String[] { "Napoleon", "Oppenheimer", "Damsel" });
        inputPanel.add(movieLabel);
        inputPanel.add(movieComboBox);


        // Number of tickets
        JLabel numTicketLabel = new JLabel("NUMBER OF TICKETS:");
        numTicketsField = new JTextField();
        inputPanel.add(numTicketLabel);
        inputPanel.add(numTicketsField);

        // Ticket price
        JLabel ticPriceLabel = new JLabel("TICKET PRICE:");
        tickPriceField = new JTextField();
        inputPanel.add(ticPriceLabel);
        inputPanel.add(tickPriceField);

        // Text area for the report
        JLabel reportlabel = new JLabel("TICKET REPORT:");
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);

        // Add components to the frame
        inputPanel.add(reportlabel);
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to process the report and display it
    private void processReport() {
        String movie = (String) movieComboBox.getSelectedItem();
        String numberOfTickets = numTicketsField.getText();
        String ticketPrice = tickPriceField.getText();
        
        

        // Validate the input for numeric fields
        int numberOfTicketsVal;
        double priceOfTicketsVal;
        try {
            numberOfTicketsVal = Integer.parseInt(numberOfTickets);
            priceOfTicketsVal = Double.parseDouble(ticketPrice);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for number of ticket and price", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create an instance of Movietickets with the entered data
        MovieTickets moT = new MovieTickets(movie, numberOfTicketsVal, priceOfTicketsVal);

        // Validate the data
        if (!moT.ValidateData()) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please ensure all fields are correct:\n" +
                    "1. Movie is not empty.\n" +
                    "2. Ticket price and number of tickets are greater than zero.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        double totalTicketPrice = moT.CalculateTotalTicketPrice(numberOfTicketsVal, priceOfTicketsVal);

      

        String report =
                "MOVIE NAME: " + movie + "\n" +
                "MOVIE TICKET PRICE: R" + ticketPrice + "\n" +
                "NUMBER OF TICKETS: " + numberOfTickets + "\n" +
                "TOTAL TICKET PRICE: " + totalTicketPrice;

        // Display report in the text area
        reportArea.setText(report);
    }

    // Method to clear the fields and text area
    private void clearFields() {
        numTicketsField.setText("");
        tickPriceField.setText("");
        reportArea.setText("");
        movieComboBox.setSelectedIndex(0);
    }

    // Method to save the report to a file
        private void saveReport() {
            String report = reportArea.getText();
            if (report.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No report to save. Process a report first.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String filePath = "C:\\Users\\krees\\OneDrive\\Desktop\\QUESTION_2\\txt File\\report.txt";
        
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write("MOVIE TICKET REPORT");
                writer.newLine();
                writer.write("\n**********************************************************");
                writer.newLine();
                writer.write(report);
                writer.newLine();
                writer.write("\n**********************************************************");
                writer.newLine();  // Add a new line after the report
                JOptionPane.showMessageDialog(this, "Report saved successfully at: " + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving report to the predefined location", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MovieTicketsGUI().setVisible(true);
            }
        });
    }
}
