package no.booking.GUI;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import no.booking.logic.Tour;
import no.booking.persistence.DataHandler;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.List;

public class TouristMainPage extends UIPage {
    public static final String NAME = "TouristMainPage";

    private JPanel mainPanel;
    private JLabel headerLbl;
    private JButton logoutBtn;
    private JPanel contentPanel;
    private JList<Tour> tourList;
    private JComboBox<String> landComboBox;
    private JComboBox<String> cityComboBox;
    private JButton bookingHistoryBtn;

    private final DefaultListModel<Tour> tourListModel;
    private final DataHandler dataHandler;

    public TouristMainPage(MainWindow mainWindow, DataHandler dataHandler) {
        this.dataHandler = dataHandler;

        tourListModel = new DefaultListModel<>();
        tourList.setModel(tourListModel);
        tourList.setCellRenderer(new TourCellRenderer());
        tourList.setDragEnabled(false);

        logoutBtn.addActionListener(actionEvent -> mainWindow.setPage(LoginPage.NAME));
        bookingHistoryBtn.addActionListener(actionEvent -> mainWindow.setPage(TouristBookingsPage.NAME));

        tourList.addListSelectionListener(listSelectionEvent -> {
            int selectionIndex = tourList.getSelectedIndex();

            // Wait to change the selected index to when the value has finished adjusting
            if (listSelectionEvent.getValueIsAdjusting()) return;
            if (selectionIndex == -1) return;

            // Navigate to the detail screen for the tour
            TourDetailPage.setTour(tourListModel.get(selectionIndex).getId());
            TourDetailPage.setPreviousPage(NAME);
            mainWindow.setPage(TourDetailPage.NAME);
        });
        cityComboBox.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.DESELECTED) return;
            populateToursFromFilters();
        });
    }

    @Override
    public void setup() {
        populateToursFromFilters();
    }

    @Override
    public void teardown() {
        tourListModel.removeAllElements();
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void populateToursFromFilters() {
        String selectedCity = (cityComboBox.getSelectedIndex() == 0) ? "" : cityComboBox.getItemAt(cityComboBox.getSelectedIndex());

        List<Tour> tours = dataHandler.getToursByCity(selectedCity);
        tourListModel.removeAllElements();
        tourListModel.addAll(tours);
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
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 4, new Insets(10, 10, 5, 10), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        headerLbl = new JLabel();
        headerLbl.setText("Alle omvisninger");
        panel2.add(headerLbl, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        logoutBtn = new JButton();
        logoutBtn.setText("Logg ut");
        panel2.add(logoutBtn, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bookingHistoryBtn = new JButton();
        bookingHistoryBtn.setText("Booking historikk");
        panel2.add(bookingHistoryBtn, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayoutManager(2, 1, new Insets(5, 5, 5, 5), -1, -1));
        contentPanel.setBackground(new Color(-2960942));
        mainPanel.add(contentPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 3, new Insets(5, 5, 5, 5), -1, -1));
        contentPanel.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder(null, "Filter", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Lokasjon:");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        landComboBox = new JComboBox();
        landComboBox.setEnabled(false);
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("<Land>");
        defaultComboBoxModel1.addElement("Danmark");
        defaultComboBoxModel1.addElement("Finland");
        defaultComboBoxModel1.addElement("Norge");
        defaultComboBoxModel1.addElement("Sverige");
        landComboBox.setModel(defaultComboBoxModel1);
        landComboBox.setToolTipText("NOT IMPLEMENTED");
        panel3.add(landComboBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cityComboBox = new JComboBox();
        cityComboBox.setEditable(false);
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("<By>");
        defaultComboBoxModel2.addElement("Oslo");
        defaultComboBoxModel2.addElement("Halden");
        defaultComboBoxModel2.addElement("Moss");
        defaultComboBoxModel2.addElement("Roma");
        defaultComboBoxModel2.addElement("København");
        defaultComboBoxModel2.addElement("Faro");
        defaultComboBoxModel2.addElement("Athen");
        cityComboBox.setModel(defaultComboBoxModel2);
        panel3.add(cityComboBox, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        contentPanel.add(scrollPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tourList = new JList();
        tourList.setSelectionMode(0);
        scrollPane1.setViewportView(tourList);
        label1.setLabelFor(landComboBox);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
