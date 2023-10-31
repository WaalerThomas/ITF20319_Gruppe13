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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.UUID;

public class TourDetailPage extends UIPage {
    public static final String NAME = "TourDetailPage";
    private static UUID currentTourId;
    private static String previousPage;
    private static boolean isInViewMode;

    private JPanel mainPanel;
    private JLabel titleLbl;
    private JButton backBtn;
    private JButton logoutBtn;
    private JPanel contentPanel;
    private JLabel countryLbl;
    private JLabel cityLbl;
    private JTextArea descriptionLbl;
    private JLabel meetPointLbl;
    private JLabel idLbl;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JSpinner adultTicketAmount;
    private JButton bookButton;
    private JLabel adultPriceLbl;
    private JLabel childPriceLbl;
    private JLabel infantPriceLbl;
    private JLabel totalCostLbl;
    private JSpinner childTicketAmount;
    private JSpinner infantTicketAmount;
    private JPanel bookingPanel;

    private final DataHandler dataHandler;
    private Tour tourData;

    public TourDetailPage(MainWindow mainWindow, DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        isInViewMode = false;

        backBtn.addActionListener(actionEvent -> {
            // If no previous page has been given, then return to the login page
            String page = (previousPage.isBlank()) ? LoginPage.NAME : previousPage;
            mainWindow.setPage(page);
        });
        logoutBtn.addActionListener(actionEvent -> mainWindow.setPage(LoginPage.NAME));

        bookButton.addActionListener(e -> {
            PayForTourPage.setTour(currentTourId);
            PayForTourPage.setTicketAmounts((int) adultTicketAmount.getValue(), (int) childTicketAmount.getValue(), (int) infantTicketAmount.getValue());
            mainWindow.setPage(PayForTourPage.NAME);
        });

        adultTicketAmount.addChangeListener(changeEvent -> calculateTotalPrice());
        childTicketAmount.addChangeListener(changeEvent -> calculateTotalPrice());
        infantTicketAmount.addChangeListener(changeEvent -> calculateTotalPrice());

        SpinnerModel spinnerModel1 = new SpinnerNumberModel(0, 0, 1_000_000_000, 1);
        SpinnerModel spinnerModel2 = new SpinnerNumberModel(0, 0, 1_000_000_000, 1);
        SpinnerModel spinnerModel3 = new SpinnerNumberModel(0, 0, 1_000_000_000, 1);
        adultTicketAmount.setModel(spinnerModel1);
        childTicketAmount.setModel(spinnerModel2);
        infantTicketAmount.setModel(spinnerModel3);
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void setup() {
        idLbl.setText("Current Tour ID: " + currentTourId);

        adultTicketAmount.setValue(0);
        childTicketAmount.setValue(0);
        infantTicketAmount.setValue(0);

        // Gather information from the database about the given tour
        // and change the elements on the screen.
        tourData = dataHandler.getTourById(currentTourId);
        if (tourData == null)
            throw new RuntimeException("Cannot find a Tour with the ID: " + currentTourId);

        titleLbl.setText(tourData.getTitle());
        countryLbl.setText(tourData.getCountry());
        cityLbl.setText(tourData.getCity());
        descriptionLbl.setText(tourData.getDescription());
        meetPointLbl.setText(tourData.getMeetingPoint());

        adultPriceLbl.setText("NOK " + tourData.getAdultTicketPrice());
        childPriceLbl.setText("NOK " + tourData.getChildTicketPrice());
        infantPriceLbl.setText("NOK " + tourData.getInfantTicketPrice());

        calculateTotalPrice();
        controlElementsViewMode();
    }

    @Override
    public void teardown() {
        if (isInViewMode) {
            setIsInViewMode(false);
            controlElementsViewMode();
        }
    }

    public static void setTour(UUID id) {
        currentTourId = id;
    }

    public static void setPreviousPage(String prevPage) {
        if (prevPage.isBlank() || prevPage.isEmpty()) return;

        previousPage = prevPage;
    }

    public static void setIsInViewMode(boolean state) {
        isInViewMode = state;
    }

    private void calculateTotalPrice() {
        int totalPrice = tourData.getAdultTicketPrice() * (int) adultTicketAmount.getValue()
                + tourData.getChildTicketPrice() * (int) childTicketAmount.getValue()
                + tourData.getInfantTicketPrice() * (int) infantTicketAmount.getValue();
        totalCostLbl.setText("NOK " + totalPrice);
    }

    private void controlElementsViewMode() {
        //adultTicketAmount.setEnabled(!isInViewMode);
        //childTicketAmount.setEnabled(!isInViewMode);
        //infantTicketAmount.setEnabled(!isInViewMode);
        //bookButton.setEnabled(!isInViewMode);
        bookingPanel.setVisible(!isInViewMode);
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
        mainPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 3, new Insets(10, 10, 5, 10), -1, -1));
        mainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        backBtn = new JButton();
        backBtn.setText("Tilbake");
        panel1.add(backBtn, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logoutBtn = new JButton();
        logoutBtn.setText("Logg ut");
        panel1.add(logoutBtn, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayoutManager(1, 2, new Insets(5, 5, 5, 5), 5, -1));
        contentPanel.setBackground(new Color(-2960942));
        mainPanel.add(contentPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bookingPanel = new JPanel();
        bookingPanel.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        contentPanel.add(bookingPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bookingPanel.setBorder(BorderFactory.createTitledBorder(null, "Billetter og priser", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, 16, bookingPanel.getFont()), null));
        comboBox1 = new JComboBox();
        comboBox1.setEnabled(false);
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("2023");
        comboBox1.setModel(defaultComboBoxModel1);
        comboBox1.setToolTipText("NOT IMPLEMENTED");
        bookingPanel.add(comboBox1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox2 = new JComboBox();
        comboBox2.setEnabled(false);
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Januar");
        comboBox2.setModel(defaultComboBoxModel2);
        comboBox2.setToolTipText("NOT IMPLEMENTED");
        bookingPanel.add(comboBox2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox3 = new JComboBox();
        comboBox3.setEnabled(false);
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("1");
        comboBox3.setModel(defaultComboBoxModel3);
        comboBox3.setToolTipText("NOT IMPLEMENTED");
        bookingPanel.add(comboBox3, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Inter", Font.BOLD, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Søk etter ledige billetter ut fra dato");
        bookingPanel.add(label1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(9, 2, new Insets(0, 0, 0, 0), -1, -1));
        bookingPanel.add(panel2, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Inter", Font.BOLD, 14, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Voksen (16-95 år)");
        panel2.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        adultTicketAmount = new JSpinner();
        panel2.add(adultTicketAmount, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 45), null, null, 0, false));
        adultPriceLbl = new JLabel();
        Font adultPriceLblFont = this.$$$getFont$$$("Inter", -1, 12, adultPriceLbl.getFont());
        if (adultPriceLblFont != null) adultPriceLbl.setFont(adultPriceLblFont);
        adultPriceLbl.setForeground(new Color(-7631471));
        adultPriceLbl.setText("NOK ???");
        panel2.add(adultPriceLbl, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Inter", Font.BOLD, 14, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Barn (5-15 år)");
        panel2.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        childPriceLbl = new JLabel();
        Font childPriceLblFont = this.$$$getFont$$$("Inter", -1, 12, childPriceLbl.getFont());
        if (childPriceLblFont != null) childPriceLbl.setFont(childPriceLblFont);
        childPriceLbl.setForeground(new Color(-7631471));
        childPriceLbl.setText("NOK ???");
        panel2.add(childPriceLbl, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        childTicketAmount = new JSpinner();
        panel2.add(childTicketAmount, new GridConstraints(2, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 45), null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Inter", Font.BOLD, 14, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Spedbarn (0-4 år)");
        panel2.add(label4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        infantPriceLbl = new JLabel();
        Font infantPriceLblFont = this.$$$getFont$$$("Inter", -1, 12, infantPriceLbl.getFont());
        if (infantPriceLblFont != null) infantPriceLbl.setFont(infantPriceLblFont);
        infantPriceLbl.setForeground(new Color(-7631471));
        infantPriceLbl.setText("NOK ???");
        panel2.add(infantPriceLbl, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        infantTicketAmount = new JSpinner();
        panel2.add(infantTicketAmount, new GridConstraints(4, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 45), null, null, 0, false));
        bookButton = new JButton();
        bookButton.setText("Book");
        panel2.add(bookButton, new GridConstraints(7, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 45), null, null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Inter", -1, 12, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-7631471));
        label5.setText("Totalt");
        panel2.add(label5, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalCostLbl = new JLabel();
        Font totalCostLblFont = this.$$$getFont$$$("Inter", Font.BOLD, 14, totalCostLbl.getFont());
        if (totalCostLblFont != null) totalCostLbl.setFont(totalCostLblFont);
        totalCostLbl.setText("NOK ???");
        panel2.add(totalCostLbl, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Inter", Font.BOLD, 16, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setText("Hvor mange billetter?");
        bookingPanel.add(label6, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(6, 3, new Insets(0, 0, 0, 0), -1, -1));
        contentPanel.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        titleLbl = new JLabel();
        Font titleLblFont = this.$$$getFont$$$(null, Font.BOLD, 24, titleLbl.getFont());
        if (titleLblFont != null) titleLbl.setFont(titleLblFont);
        titleLbl.setText("<Title>");
        panel3.add(titleLbl, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel3.add(spacer3, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        countryLbl = new JLabel();
        countryLbl.setText("<Country>");
        panel3.add(countryLbl, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        idLbl = new JLabel();
        idLbl.setText("Label");
        panel3.add(idLbl, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cityLbl = new JLabel();
        cityLbl.setText("<City>");
        panel3.add(cityLbl, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setHorizontalScrollBarPolicy(31);
        scrollPane1.setVerticalScrollBarPolicy(22);
        panel3.add(scrollPane1, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        descriptionLbl = new JTextArea();
        descriptionLbl.setEditable(false);
        descriptionLbl.setLineWrap(true);
        descriptionLbl.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris auctor sem lorem, ac tincidunt nunc dignissim elementum. Praesent nec gravida elit, id porttitor ligula. Sed ligula diam, interdum eget est sed, finibus imperdiet velit. Aenean euismod, arcu ac pharetra rutrum, lorem dui convallis nulla, quis auctor mi risus at sapien. Etiam sit amet risus vestibulum, scelerisque nunc eu, venenatis arcu. Vestibulum malesuada, risus eu vehicula tristique, tortor odio iaculis eros, eget cursus nisl sapien sed elit. Suspendisse eu accumsan dui, ac mattis leo. ");
        scrollPane1.setViewportView(descriptionLbl);
        final JLabel label7 = new JLabel();
        label7.setText("Møtepunkt:");
        panel3.add(label7, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        meetPointLbl = new JLabel();
        meetPointLbl.setText("<Meeting Point>");
        panel3.add(meetPointLbl, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Lokasjon:");
        panel3.add(label8, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
