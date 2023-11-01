package no.booking.GUI;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import no.booking.logic.Tour;
import no.booking.persistence.DataHandler;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;
import java.util.stream.Stream;

import static javax.swing.JOptionPane.showMessageDialog;

public class CreateTourPage extends UIPage {
    public static final String NAME = "CreateTourPage";

    private JButton cancelBtn;
    private JPanel mainPanel;
    private JComboBox<String> countryComboBox;
    private JComboBox<String> cityComboBox;
    private JLabel pris;
    private JButton addTourBtn;
    private JTextPane descriptionTextPane;
    private JButton failedCreationBtn;
    private JButton logoutBtn;
    private JPanel contentPanel;
    private JSpinner adultPriceSpinner;
    private JSpinner childPriceSpinner;
    private JSpinner infantPriceSpinner;
    private JSpinner ticketAmountSpinner;
    private JTextField titleTextField;
    private JTextField meetingPointTextField;

    private MainWindow mainWindow;
    private DataHandler dataHandler;

    public CreateTourPage(MainWindow mainWindow, DataHandler dataHandler) {
        this.mainWindow = mainWindow;
        this.dataHandler = dataHandler;

        cancelBtn.addActionListener(actionEvent -> mainWindow.setPage(LoginGuide.NAME));
        logoutBtn.addActionListener(actionEvent -> mainWindow.setPage(LoginPage.NAME));

        addTourBtn.addActionListener(actionEvent -> mainWindow.setPage(TourConfirmedPage.Name));

        failedCreationBtn.addActionListener(actionEvent -> {
            showMessageDialog(null, "Opprettelse av omvisning feilet", "Error", JOptionPane.ERROR_MESSAGE);
        });

        adultPriceSpinner.setModel(new SpinnerNumberModel(0, 0, 1_000_000_000, 1));
        childPriceSpinner.setModel(new SpinnerNumberModel(0, 0, 1_000_000_000, 1));
        infantPriceSpinner.setModel(new SpinnerNumberModel(0, 0, 1_000_000_000, 1));
        ticketAmountSpinner.setModel(new SpinnerNumberModel(0, 0, 1_000_000_000, 1));
    }

    // Genererer liste med land
    public String[] getAllCountries() {
        String[] countries = new String[Locale.getISOCountries().length];
        String[] countryCodes = Locale.getISOCountries();
        for (int i = 0; i < countryCodes.length; i++) {
            Locale obj = new Locale("", countryCodes[i]);
            countries[i] = obj.getDisplayCountry();
        }
        countries = Stream.of(countries).sorted().toArray(String[]::new);
        return countries;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void setup() {
        // Insert dummy data into the form
        countryComboBox.addItem("Hellas");
        cityComboBox.addItem("Athen");
        titleTextField.setText("Opplev vakre omstendigheter i Athen");
        descriptionTextPane.setText("""
                Utforsk antikken i hjertet av Athen, fra Parthenon til lokale smaker. En reise gjennom tusenvis av år med historie og autentisk gresk kultur.
                Alder: 3-99. Maks 25 pr gruppe
                Varighet: 6 timer
                Språk: Engelsk
                """);
        meetingPointTextField.setText("Athen sentrum");
        adultPriceSpinner.setValue(5000);
        childPriceSpinner.setValue(2500);
        infantPriceSpinner.setValue(0);
        ticketAmountSpinner.setValue(50);

        // Add countries into the combobox
        String[] countries = {"Norge", "Sverige", "Danmark", "Portugal", "Italia"};
        for (String item : countries)
            countryComboBox.addItem(item);

        // Add cities into the combobox
        String[] cities = {"Oslo", "Halden", "Moss", "Roma", "København", "Faro"};
        for (String item : cities)
            cityComboBox.addItem(item);
    }

    @Override
    public void teardown() {
    }


    private void createTour() {
        // TODO: Check that all the fields are filled
        Tour newTour = dataHandler.createTour(
                "GeorgGuide",
                titleTextField.getText(),
                countryComboBox.getSelectedItem().toString(),
                cityComboBox.getSelectedItem().toString(),
                descriptionTextPane.getText(),
                "2023-10-10 17:00:00",
                (int) adultPriceSpinner.getValue(),
                (int) childPriceSpinner.getValue(),
                (int) infantPriceSpinner.getValue(),
                meetingPointTextField.getText(),
                (int) ticketAmountSpinner.getValue()
        );

        int result = JOptionPane.showConfirmDialog(null, "Omvisning er opprettet", "Suksess", JOptionPane.OK_CANCEL_OPTION);
        // Not checking the result
        // Redirect the user to the main window after creation
        mainWindow.setPage(LoginGuide.NAME);
    }



    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 4, new Insets(10, 10, 5, 10), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        logoutBtn = new JButton();
        logoutBtn.setText("Logg ut");
        panel2.add(logoutBtn, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        cancelBtn = new JButton();
        cancelBtn.setText("Tilbake");
        panel2.add(cancelBtn, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Inter", Font.BOLD, 18, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Legg til omvisning");
        panel2.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayoutManager(1, 1, new Insets(5, 5, 5, 5), -1, -1));
        contentPanel.setBackground(new Color(-2960942));
        mainPanel.add(contentPanel, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(8, 2, new Insets(5, 5, 5, 5), -1, -1));
        contentPanel.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Land");
        panel3.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        countryComboBox = new JComboBox();
        countryComboBox.setEditable(false);
        panel3.add(countryComboBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("By");
        panel3.add(label3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cityComboBox = new JComboBox();
        panel3.add(cityComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Beskrivelse");
        panel3.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel3.add(scrollPane1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        descriptionTextPane = new JTextPane();
        descriptionTextPane.setText("");
        scrollPane1.setViewportView(descriptionTextPane);
        pris = new JLabel();
        pris.setText("Billett priser");
        panel3.add(pris, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addTourBtn = new JButton();
        addTourBtn.setText("Legg til");
        panel4.add(addTourBtn, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel4.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        failedCreationBtn = new JButton();
        failedCreationBtn.setText("Opprettelse feilet");
        panel4.add(failedCreationBtn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(2, 3, new Insets(3, 3, 3, 3), -1, -1));
        panel3.add(panel5, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label5 = new JLabel();
        label5.setText("Voksen (16-95 år)");
        panel5.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        adultPriceSpinner = new JSpinner();
        panel5.add(adultPriceSpinner, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Barn (5-15 år)");
        panel5.add(label6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        childPriceSpinner = new JSpinner();
        panel5.add(childPriceSpinner, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Spedbarn (0-4)");
        panel5.add(label7, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        infantPriceSpinner = new JSpinner();
        panel5.add(infantPriceSpinner, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Antall billetter");
        panel3.add(label8, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ticketAmountSpinner = new JSpinner();
        panel3.add(ticketAmountSpinner, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Tittel");
        panel3.add(label9, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        titleTextField = new JTextField();
        panel3.add(titleTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Møtepunkt");
        panel3.add(label10, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        meetingPointTextField = new JTextField();
        panel3.add(meetingPointTextField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label2.setLabelFor(countryComboBox);
        label3.setLabelFor(cityComboBox);
        label4.setLabelFor(scrollPane1);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
