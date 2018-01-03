import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Comparator;

import net.miginfocom.swing.*;

public class mainFrame extends JFrame {

    private int userPosition;
    private ArrayList<user> users;
    private dbReader db;
    private static final Comparator<user> comparator = new descendingUserComparator();

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane mainPanel;
    private JPanel showUserPanel;
    private JLabel idLabel;
    private JTextField idText;
    private JLabel imageLabel;
    private JSeparator separator1;
    private JLabel nameLabel;
    private JTextField nameText;
    private JSeparator separator2;
    private JLabel surnameLabel;
    private JTextField surnameText;
    private JButton backButton;
    private JButton nextButton;
    private JLabel processLabel;
    private JLabel countLabel;
    private JPanel addUserPanel;
    private JLabel idLabel2;
    private JTextField idText2;
    private JSeparator separator3;
    private JLabel nameLabel2;
    private JTextField nameText2;
    private JSeparator separator4;
    private JLabel surnameLabel2;
    private JTextField surnameText2;
    private JButton addButton;
    private JSeparator separator5;
    private JLabel pictureLabel;
    private JTextField filenameText;
    private JLabel processLabel2;
    private JButton searchButton;
    private JPanel deletePanel;
    private JLabel idDeleteLabel;
    private JComboBox idBox;
    private JButton delButton;
    private JLabel nameDelText;
    private JTextField nameFieldDel;
    private JLabel surnameDelText;
    private JTextField surnameFieldText;
    private JLabel processLabel3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private user getUserById(int id) throws empException {
        int i = 0;
        while (i < users.size() && users.get(i).getId() != id) i++;
        if (i == users.size()) throw new empException("ERROR. User not found on db");
        return users.get(i);
    }

    private void setUserInfoDelete() throws empException {
        setUserInfoDelete(-1);
    }

    private void setUserInfoDelete(int pos) throws empException {
        if (users.size() == 0) {
            nameFieldDel.setText("");
            surnameFieldText.setText("");
        } else {
            user selected = (pos == -1) ? getUserById((int) idBox.getSelectedItem()) : users.get(pos);
            nameFieldDel.setText(selected.getName());
            surnameFieldText.setText(selected.getSurname());
        }
    }

    private void readSortUsers() throws empException {
        users = db.readAllData();
        users.sort(comparator);
    }

    private void backButtonActionPerformed(ActionEvent e) {
        if (userPosition == 0) return ;
        else {
            userPosition--;
            setUser();
        }
    }

    private void setError(String error) {
        processLabel.setForeground(Color.red);
        processLabel.setText(error);
        processLabel2.setForeground(Color.red);
        processLabel2.setText(error);
        processLabel3.setForeground(Color.red);
        processLabel3.setText(error);
    }

    private void setMessage(String message) {
        processLabel.setForeground(Color.GREEN);
        processLabel.setText(message);
        processLabel2.setForeground(Color.GREEN);
        processLabel2.setText(message);
        processLabel3.setForeground(Color.GREEN);
        processLabel3.setText(message);
    }

    private void setUser() {
        if(users.size() == 0) {
            idText.setText("");
            nameText.setText("");
            surnameText.setText("");
            imageLabel.setIcon(null);
        } else {
            user currentUser = users.get(userPosition);
            idText.setText(Integer.toString(currentUser.getId()));
            nameText.setText(currentUser.getName());
            surnameText.setText(currentUser.getSurname());

            try {
                blib.blobToFile(currentUser.getProfPic().getProfPic(),
                        currentUser.getProfPic().getExtension(),
                        idText.getText());
                imageLabel.setIcon(blib.fileToIcon(blib.getLocation() +
                        idText.getText() + "." + currentUser.getProfPic().getExtension()));
                setMessage("Set user succesfully");
            } catch (empException e) {
                setError(e.getMessage());
                imageLabel.setIcon(null);
            } finally {
                countLabel.setText((userPosition + 1) + "/" + users.size());
            }
        }
    }

    private void nextButtonActionPerformed(ActionEvent e) {
        if (userPosition >= users.size() - 1) return ;
        else {
            userPosition++;
            setUser();
        }
    }

    public JTabbedPane getMainPanel() {
        return this.mainPanel;
    }

    private void searchButtonActionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        int opValue = fc.showOpenDialog(mainPanel);
        if (opValue == fc.APPROVE_OPTION) filenameText.setText(fc.getSelectedFile().getPath());
    }

    private void addButtonActionPerformed(ActionEvent e) {
        try {
            if (idText2.getText().isEmpty())
                setError("ERROR. Id field is empty");
            else {
                int newIndex = Integer.parseInt(idText2.getText());
                db.insertData(newIndex,
                        nameText2.getText(),
                        surnameText2.getText(),
                        filenameText.getText());

                readSortUsers();
                setUser();
                idBox.addItem(newIndex);

                setMessage("User added to database succesfully");
            }
        } catch (empException error) {
            setError(error.getMessage());
        }
    }

    private void delButtonActionPerformed(ActionEvent e) {
       try {
           db.deleteUser((int)idBox.getSelectedItem());
           readSortUsers();
           int index = idBox.getSelectedIndex();
           idBox.removeItemAt(index);
           if(index == userPosition || index >= users.size()) userPosition = 0;
           setMessage("User deleted succesfully");
       } catch (empException error) {
           setError(error.getMessage());
       } finally { setUser(); }
    }

    private void idBoxItemStateChanged(ItemEvent e) {
        try {
            setUserInfoDelete();
        } catch (empException error) {
            setError(error.getMessage());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        mainPanel = new JTabbedPane();
        showUserPanel = new JPanel();
        idLabel = new JLabel();
        idText = new JTextField();
        imageLabel = new JLabel();
        separator1 = new JSeparator();
        nameLabel = new JLabel();
        nameText = new JTextField();
        separator2 = new JSeparator();
        surnameLabel = new JLabel();
        surnameText = new JTextField();
        backButton = new JButton();
        nextButton = new JButton();
        processLabel = new JLabel();
        countLabel = new JLabel();
        addUserPanel = new JPanel();
        idLabel2 = new JLabel();
        idText2 = new JTextField();
        separator3 = new JSeparator();
        nameLabel2 = new JLabel();
        nameText2 = new JTextField();
        separator4 = new JSeparator();
        surnameLabel2 = new JLabel();
        surnameText2 = new JTextField();
        addButton = new JButton();
        separator5 = new JSeparator();
        pictureLabel = new JLabel();
        filenameText = new JTextField();
        processLabel2 = new JLabel();
        searchButton = new JButton();
        deletePanel = new JPanel();
        idDeleteLabel = new JLabel();
        idBox = new JComboBox();
        delButton = new JButton();
        nameDelText = new JLabel();
        nameFieldDel = new JTextField();
        surnameDelText = new JLabel();
        surnameFieldText = new JTextField();
        processLabel3 = new JLabel();

        //======== mainPanel ========
        {
            mainPanel.setMaximumSize(new Dimension(410, 230));
            mainPanel.setPreferredSize(new Dimension(410, 230));
            mainPanel.setMinimumSize(new Dimension(410, 230));

            //======== showUserPanel ========
            {
                showUserPanel.setMinimumSize(new Dimension(410, 204));
                showUserPanel.setMaximumSize(new Dimension(410, 204));

                showUserPanel.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[85,fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[2]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]"));

                //---- idLabel ----
                idLabel.setText("Id.");
                showUserPanel.add(idLabel, "pad 0 10 0 0,cell 0 0");

                //---- idText ----
                idText.setEditable(false);
                idText.setMinimumSize(new Dimension(140, 26));
                idText.setMaximumSize(new Dimension(140, 26));
                showUserPanel.add(idText, "cell 1 0 7 1");
                showUserPanel.add(imageLabel, "cell 9 0 3 6,wmax 100,hmax 100");

                //---- separator1 ----
                separator1.setMaximumSize(new Dimension(240, 1));
                separator1.setMinimumSize(new Dimension(240, 1));
                showUserPanel.add(separator1, "cell 0 1 8 1");

                //---- nameLabel ----
                nameLabel.setText("Name");
                showUserPanel.add(nameLabel, "pad 0 10 0 0,cell 0 2");

                //---- nameText ----
                nameText.setEditable(false);
                nameText.setMinimumSize(new Dimension(140, 26));
                nameText.setMaximumSize(new Dimension(140, 26));
                showUserPanel.add(nameText, "cell 1 2 7 1");

                //---- separator2 ----
                separator2.setMaximumSize(new Dimension(240, 1));
                separator2.setMinimumSize(new Dimension(240, 1));
                showUserPanel.add(separator2, "cell 0 3 8 1");

                //---- surnameLabel ----
                surnameLabel.setText("Surname");
                showUserPanel.add(surnameLabel, "pad 0 10 0 0,cell 0 4");

                //---- surnameText ----
                surnameText.setEditable(false);
                surnameText.setMinimumSize(new Dimension(140, 26));
                surnameText.setMaximumSize(new Dimension(140, 26));
                showUserPanel.add(surnameText, "cell 1 4 7 1");

                //---- backButton ----
                backButton.setText("Back");
                backButton.addActionListener(e -> backButtonActionPerformed(e));
                showUserPanel.add(backButton, "cell 0 7");

                //---- nextButton ----
                nextButton.setText("Next");
                nextButton.addActionListener(e -> nextButtonActionPerformed(e));
                showUserPanel.add(nextButton, "cell 1 7 3 1");
                showUserPanel.add(processLabel, "cell 0 8 8 2");
                showUserPanel.add(countLabel, "cell 0 8 8 2");
            }
            mainPanel.addTab("Show", showUserPanel);

            //======== addUserPanel ========
            {
                addUserPanel.setMaximumSize(new Dimension(410, 230));
                addUserPanel.setMinimumSize(new Dimension(410, 230));
                addUserPanel.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[85,fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]"));

                //---- idLabel2 ----
                idLabel2.setText("Id.");
                addUserPanel.add(idLabel2, "pad 0 10 0 0,cell 0 0 2 1");

                //---- idText2 ----
                idText2.setMaximumSize(new Dimension(140, 26));
                idText2.setMinimumSize(new Dimension(140, 26));

                addUserPanel.add(idText2, "cell 2 0 17 1");
                addUserPanel.add(separator3, "cell 0 1 22 1");

                //---- nameLabel2 ----
                nameLabel2.setText("Name");
                addUserPanel.add(nameLabel2, "pad 0 10 0 0,cell 0 2 2 1");

                //---- nameText2 ----
                nameText2.setMaximumSize(new Dimension(140, 26));
                nameText2.setMinimumSize(new Dimension(140, 26));
                addUserPanel.add(nameText2, "cell 2 2 17 1");
                addUserPanel.add(separator4, "cell 0 3 22 1");

                //---- surnameLabel2 ----
                surnameLabel2.setText("Surname");
                addUserPanel.add(surnameLabel2, "pad 0 10 0 0,cell 0 4 2 1");

                //---- surnameText2 ----
                surnameText2.setMaximumSize(new Dimension(140, 26));
                surnameText2.setMinimumSize(new Dimension(140, 26));
                addUserPanel.add(surnameText2, "cell 2 4 17 1");

                //---- addButton ----
                addButton.setText("Add");
                addButton.addActionListener(e -> addButtonActionPerformed(e));
                addUserPanel.add(addButton, "cell 20 4 2 1");
                addUserPanel.add(separator5, "cell 0 5 22 1");

                //---- pictureLabel ----
                pictureLabel.setText("Picture");
                addUserPanel.add(pictureLabel, "pad 0 10 0 0,cell 0 6 12 2");

                //---- filenameText ----
                filenameText.setMaximumSize(new Dimension(140, 26));
                filenameText.setMinimumSize(new Dimension(140, 26));
                addUserPanel.add(filenameText, "cell 2 6 17 1");
                addUserPanel.add(processLabel2, "cell 0 7 22 2");

                //---- searchButton ----
                searchButton.setText("Search");
                searchButton.addActionListener(e -> searchButtonActionPerformed(e));
                addUserPanel.add(searchButton, "cell 20 6 2 1");
            }
            mainPanel.addTab("Add", addUserPanel);

            //======== deletePanel ========
            {
                deletePanel.setMaximumSize(new Dimension(410, 230));
                deletePanel.setMinimumSize(new Dimension(410, 230));
                deletePanel.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]"));

                //---- idDeleteLabel ----
                idDeleteLabel.setText("Id.");
                deletePanel.add(idDeleteLabel, "cell 0 1 2 1");

                //---- idBox ----
                idBox.setMinimumSize(new Dimension(140, 26));
                idBox.setMaximumSize(new Dimension(140, 26));
                idBox.addItemListener(e -> idBoxItemStateChanged(e));
                deletePanel.add(idBox, "cell 2 1 18 1");

                //---- delButton ----
                delButton.setText("Delete");
                delButton.addActionListener(e -> delButtonActionPerformed(e));
                deletePanel.add(delButton, "cell 20 1");

                //---- nameDelText ----
                nameDelText.setText("Name");
                deletePanel.add(nameDelText, "cell 0 2");

                //---- nameFieldDel ----
                nameFieldDel.setEditable(false);
                nameFieldDel.setMaximumSize(new Dimension(140, 26));
                nameFieldDel.setMinimumSize(new Dimension(140, 26));
                deletePanel.add(nameFieldDel, "cell 2 2 18 1");

                //---- surnameDelText ----
                surnameDelText.setText("Surname");
                deletePanel.add(surnameDelText, "cell 0 3");

                //---- surnameFieldText ----
                surnameFieldText.setEditable(false);
                surnameFieldText.setMaximumSize(new Dimension(140, 26));
                surnameFieldText.setMinimumSize(new Dimension(140, 26));
                deletePanel.add(surnameFieldText, "cell 2 3 18 1");
                deletePanel.add(processLabel3, "cell 0 5 23 1");
            }
            mainPanel.addTab("Delete", deletePanel);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public mainFrame() {
        try {
            initComponents();
            db = new dbReader("db/simpleDatabase.db");

            /*Executed first time to insert values into .db

            db.insertData(1, "John", "Cena", "profilePics/first.png");
            db.insertData(2, "Micaela", "Tavares", "profilePics/second.jpeg");
            db.insertData(3, "Zlatan", "Ibrahimovic", "profilePics/third.png");
            db.insertData(400, "Giuseppe", "Taradellas", "profilePics/fourth.jpeg");
            */

            userPosition = 0;
            readSortUsers();
            for(user u : users) {
                idBox.addItem(u.getId());
            }
            setUser();
            setMessage("GUI created succesfully");

        } catch (empException e) {
            setError(e.getMessage());
        }
    }
}
