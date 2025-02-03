/*
 * Created by JFormDesigner on Wed Dec 04 12:42:21 EET 2024
 */

package com.westums.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.*;
import com.jgoodies.forms.factories.*;
import com.westums.views.admindashboard.AddCourseCard;
import com.westums.views.admindashboard.AddEnrollmentCard;
import com.westums.views.admindashboard.AddProfessorCard;
import com.westums.views.admindashboard.AddStudentCard;
import net.miginfocom.swing.*;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.swing.FontIcon;

/**
 * @author Raul
 */
public class AdminDashboard extends JPanel {

    public AdminDashboard() {
        initComponents();
    }

    public void showCard(String cardName) {
        cardLayout.show(formPanel, cardName);
    }

    /**
     * Switches the view in the card layout to the one with the given name.
     */
    public void showView(String viewName) {
        cardLayout.show(formPanel, viewName);
        revalidate();
        repaint();
    }

    /**
     * Instantiates the appropriate view based on the given view name,
     * adds it to the card layout, and returns the view object.
     * @param viewName The name of the view to add to the card layout (e.g. "Add Student Card").
     * @return The view object that was added to the card layout.
     */
    public Object addView(String viewName) throws Exception {

        Object view = View.getViewClass(viewName).getConstructor().newInstance();
        formPanel.add((Component) view, viewName);
        return view;
    }

    /**
     * Removes the current card (index 1) from the card layout,
     * leaving only the default card (index 0) visible.
     */
    public void removeCurrentView() {
        // Only one view will be in the card layout at a time
        if (formPanel.getComponentCount() > 1)
            formPanel.remove(1);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Raul Ariton (raul.ariton05)
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        menuPanel = new JPanel();
        menuHeader = new JLabel();
        separator1 = new JPopupMenu.Separator();
        optionPane = new JScrollPane();
        optionTree = new JTree();
        separator2 = new JPopupMenu.Separator();
        logoutPanel = new JPanel();
        FontIcon fontIcon = FontIcon.of(FontAwesome.ARROW_CIRCLE_LEFT);
        fontIcon.setIconSize(13);
        fontIcon.setIconColor(new Color(0x900B09));
        logoutIconLabel = new JLabel(fontIcon);
        logoutClickableLabel = new JLabel();
        formPanel = new JPanel();
        defaultCard = new JPanel();
        chooseOptionMessage = new JTextPane();


        cardLayout = new CardLayout();

        //======== this ========
        setBackground(new Color(0x1e1e34));
        setMinimumSize(new Dimension(320, 206));
        setPreferredSize(new Dimension(320, 245));
        setLayout(new MigLayout(
                "fillx,insets 0,hidemode 3,align center center,gap 0 0",
                // columns
                "[fill]",
                // rows
                "[fill]" +
                        "[]"));

        //======== menuPanel ========
        {
            menuPanel.setBackground(new Color(0xf5f5f5));
            menuPanel.setLayout(new MigLayout(
                "fill,insets 8 8 4 8,novisualpadding,hidemode 3,gapy 0",
                // columns
                "[0,fill]",
                // rows
                "[shrink 0]" +
                "[22]" +
                "[]" +
                "[]" +
                "[]"));

            //---- menuHeader ----
            menuHeader.setText("Admin");
            menuHeader.setFont(new Font("Inter 18pt", Font.BOLD, 14));
            menuHeader.setForeground(new Color(0x1e1e1e));
            menuPanel.add(menuHeader, "pad 0 10 0 0,cell 0 0,aligny center,gapy null -4,wrap");
            menuPanel.add(separator1, "pad 0 8 0 -8,cell 0 1,aligny center,growy 0");

            //======== optionPane ========
            {
                optionPane.setForeground(new Color(0x1e1e1e));
                optionPane.setBackground(new Color(0x1e1e1e));
                optionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                optionPane.setBorder(null);
                optionPane.setMaximumSize(new Dimension(32767, 400));
                optionPane.setPreferredSize(new Dimension(115, 250));

                //---- optionTree ----
                DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)optionTree.getCellRenderer();
                renderer.setForeground(new Color(0x1E1E1E));
                renderer.setTextSelectionColor(new Color(0x1e1e1e));
                renderer.setTextNonSelectionColor(new Color(0x1e1e1e));
                renderer.setOpaque(false);
                renderer.setBackgroundSelectionColor(new Color(0xD9D9D9));
                renderer.setBackgroundNonSelectionColor(new Color(0xF5F5F5));
                optionTree.setCellRenderer(renderer);
                optionTree.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                optionTree.setModel(new DefaultTreeModel(
                    new DefaultMutableTreeNode("(root)") {
                        {
                            add(new DefaultMutableTreeNode("Add Student"));
                            add(new DefaultMutableTreeNode("Add Professor"));
                            add(new DefaultMutableTreeNode("Add Course"));
                            add(new DefaultMutableTreeNode("Add Enrollment"));
                            add(new DefaultMutableTreeNode("Import..."));
                        }
                    }));
                optionTree.setBackground(new Color(0xf5f5f5));
                optionTree.setBorder(null);
                optionTree.setForeground(new Color(0x1e1e1e));
                optionTree.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                optionTree.setRootVisible(false);
                optionTree.setRowHeight(48);
                optionTree.setPreferredSize(new Dimension(115, 100));
                optionPane.setViewportView(optionTree);
            }
            menuPanel.add(optionPane, "pad 0 5 0 0,cell 0 2,grow");
            menuPanel.add(separator2, "pad 0 8 0 -8,cell 0 3,aligny center,growy 0,wrap");

            //======== logoutPanel ========
            {
                logoutPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                //======== logoutIconLabel ========
                {
                    logoutIconLabel.setHorizontalAlignment(JLabel.CENTER);
                    logoutIconLabel.setVerticalAlignment(JLabel.CENTER);
                    logoutIconLabel.setSize(16,16);
                }
                logoutPanel.setOpaque(false);
                logoutPanel.setLayout(new FlowLayout());
                logoutPanel.add(logoutIconLabel);

                //---- logoutClickableLabel ----
                logoutClickableLabel.setText("Logout");
                logoutClickableLabel.setFont(new Font("Inter 18pt", Font.PLAIN, 14));
                logoutClickableLabel.setForeground(new Color(0x900b09));
                logoutClickableLabel.setHorizontalAlignment(SwingConstants.CENTER);
                logoutPanel.add(logoutClickableLabel);
            }
            menuPanel.add(logoutPanel, "cell 0 4,align center bottom,grow 0 0");
        }
        add(menuPanel, "width 190:190:190");
        // add(menuPanel, "width 190:190:190,height 400:400:400");

        //======== formPanel ========
        {
            formPanel.setBackground(new Color(0x1e1e34));
            formPanel.setOpaque(false);
            formPanel.setMinimumSize(new Dimension(300, 300));
            formPanel.setPreferredSize(new Dimension(410, 400));
            formPanel.setLayout(cardLayout);

            //======== defaultCard ========
            {
                defaultCard.setOpaque(false);
                defaultCard.setMinimumSize(new Dimension(410, 400));
                defaultCard.setLayout(new MigLayout(
                        "fill,insets 0,hidemode 3,align center center",
                        // columns
                        "[fill]",
                        // rows
                        "[fill]"));

                //---- chooseOptionMessage ----
                chooseOptionMessage.setText("No option chosen. \nPlease choose one of the options from the " +
                        "menu on the left.");
                chooseOptionMessage.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                chooseOptionMessage.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

                StyledDocument doc = chooseOptionMessage.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);

                chooseOptionMessage.setEditable(false);
                chooseOptionMessage.setBorder(null);
                chooseOptionMessage.setEnabled(false);
                chooseOptionMessage.setDisabledTextColor(new Color(0xf3f3f3));
                chooseOptionMessage.setOpaque(false);
                chooseOptionMessage.setFont(new Font("Inter 18pt", Font.PLAIN, 14));
                chooseOptionMessage.setFocusable(false);
                defaultCard.add(chooseOptionMessage, "cell 0 0,align center center,grow 0 0,wmax 400");
            }
            formPanel.add(defaultCard, "Default Card");
        }
        add(formPanel, "cell 1 0,width 410:410:410");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Raul Ariton (raul.ariton05)
    private JPanel menuPanel;
    private JLabel menuHeader;
    private JPopupMenu.Separator separator1;
    private JScrollPane optionPane;
    public JTree optionTree;
    private JPopupMenu.Separator separator2;
    public JPanel logoutPanel;
    private Icon logoutIcon;
    private JLabel logoutIconLabel;
    private JLabel logoutClickableLabel;
    public JPanel formPanel;
    public CardLayout cardLayout;
    private JPanel defaultCard;
    private JTextPane chooseOptionMessage;

//    public JPanel addEnrollmentCard;
//    private JPanel studentEnrollmentEmailPanel;
//    private JLabel studentEnrollmentEmailLabel;
//    public JXFormattedTextField studentEnrollmentEmailField;
//    public JLabel studentEnrollmentEmailInvalidLabel;
//    public JLabel studentEnrollmentEmailNotFoundLabel;
//    public JLabel studentEnrollmentEmailFoundLabel;
//    private JPanel refreshCourseListButtonPanel;
//    public JButton refreshCourseListButton;
//    public JScrollPane courseScrollPane;
//    public DefaultListModel<Course> courseListModel;
//    public JList<Course> courseList;
//    private JPanel addEnrollmentButtonPanel;
//    public JButton addEnrollmentButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
