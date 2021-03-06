/*
 * Contains all classes pertaining to the GUI
 */
package GUI;

//imports
import Files.*;
import java.util.HashMap;
import javax.swing.JTextArea;
import PanelLinking.SendChangeSignal;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Score Panel is a Panel which displays the scores stored in the file
 *
 * @author Richard Tu
 */
public class ScorePanel extends javax.swing.JPanel {

    //stores a hashmap of all accounts
    HashMap<String, Account> findName;
    SendChangeSignal updateTopScores;

    /**
     * Creates new form ScorePanel
     *
     * @param updateTopScoresCopy - a copy of a property change interface from
     *
     */
    public ScorePanel(SendChangeSignal updateTopScoresCopy) {
        initComponents();

        //outputs all names by win order
        TopScoreOutput("");

        //copies over the PropertyChangeListener from control panel and adds a 
        //PropertyChangeListener
        updateTopScores = updateTopScoresCopy;
        updateTopScores.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                TopScoreOutput(filterName.getText());
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        LeaderBoardDisplay = new javax.swing.JTextArea();
        TopScoreTitle = new javax.swing.JLabel();
        filterName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setBackground(new java.awt.Color(200, 200, 200));

        LeaderBoardDisplay.setEditable(false);
        LeaderBoardDisplay.setColumns(20);
        LeaderBoardDisplay.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        LeaderBoardDisplay.setRows(5);
        jScrollPane1.setViewportView(LeaderBoardDisplay);

        TopScoreTitle.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        TopScoreTitle.setText("Top Scores");

        filterName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        filterName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterNameActionPerformed(evt);
            }
        });
        filterName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filterNameKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Filter ->");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(TopScoreTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(filterName, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(TopScoreTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(filterName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Outputs all the names which start with filter text in
     * win(high)-loss(low)-tie(high) order
     *
     * @param filterText - stores the key which entries are matched against
     */
    public void TopScoreOutput(String filterText) {
        //updates the hashMap findName
        findName = FileManipFunctions.GetAccountHashMap();

        //clears the leader board
        LeaderBoardDisplay.setText("");

        //gets all the accounts which start with the inputted filterText
        Account[] accounts = SortSearchFunctions.nameFinder(filterText, findName);

        //gets a sorted list of the filtered list
        accounts = SortSearchFunctions.scoreSorter(accounts);

        //outputs the headings
        LeaderBoardDisplay.append("Name      \tWins      \tLosses    \tTies    \n");

        //outputs all accounts in the list
        for (int i = 0; i < accounts.length; i++) {
            TableOutput(accounts[i], LeaderBoardDisplay);

        }
    }

    /**
     * outputs any additionally spaces needed for formatting
     *
     * @param length - stores the length of the String
     * @param outputted - stores the JTextArea the spaces are appended tp
     */
    void outputSpaces(int length, JTextArea outputted) {
        //adds spaces until there are 10 characters
        for (int i = 0; i < (10 - length); i++) {
            outputted.append(" ");
        }
        //adds a tab to preserve tabular format
        outputted.append("\t");
    }

    /**
     * outputs the the inputted string as a box of the table
     *
     * @param lines - stores a String of characters
     * @param outputted - stores the JTextArea lines is appended to
     */
    void outputBox(String lines, JTextArea outputted) {
        //ensures no name is greater than 10
        for (int i = 0; i < lines.length() && i < 11; i++) {
            outputted.append(Character.toString(lines.charAt(i)));
        }
        //adds additional spaces
        outputSpaces(lines.length(), outputted);

    }

    /**
     * outputs the the inputted Account into tabular format
     *
     * @param account - stores the account to be printed out
     * @param outputted - stores the JTextArea that the account is appended to
     */
    void TableOutput(Account account, JTextArea outputted) {
        //converts the inputted account into 4 strings
        String name = account.getName();
        String wins = Integer.toString(account.getWins());
        String losses = Integer.toString(account.getLosses());
        String ties = Integer.toString(account.getTies());

        //appends the four strings to the table
        outputBox(name, outputted);
        outputBox(wins, outputted);
        outputBox(losses, outputted);
        outputBox(ties, outputted);
        //goes to the next line
        outputted.append("\n");

    }

    private void filterNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterNameActionPerformed

    /**
     * updates the leader boards each time a key is entered
     *
     * @param evt - not used
     */
    private void filterNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filterNameKeyReleased
        TopScoreOutput(filterName.getText());

    }//GEN-LAST:event_filterNameKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea LeaderBoardDisplay;
    private javax.swing.JLabel TopScoreTitle;
    private javax.swing.JTextField filterName;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
