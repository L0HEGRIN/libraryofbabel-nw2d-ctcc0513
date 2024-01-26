/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dsa.casestudydsa;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

class BookListCellRenderer extends DefaultListCellRenderer {
    private ArrayList<Book> bookList;
   

    public BookListCellRenderer(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Check if the value is a book title
        if (value instanceof String) {
            // Display only the title
            setText((String) value);
        } else {
            // Display full details only when selected
            setText("");
        }

        return this;
    }

    public void showFullDetails(int selectedIndex) {
        // Check if the selected index is valid
        if (selectedIndex >= 0 && selectedIndex < bookList.size()) {
            // Display the full details of the selected book
            Book selectedBook = bookList.get(selectedIndex);
            setText(selectedBook.toString());
        }
    }
}

class Book implements Comparable<Book> {
    private int id;
    private String title;
    private String author;
    private String releaseDate;

    public Book(int id, String title, String author, String releaseDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int compareTo(Book other) {
        return this.author.compareTo(other.author);
    }

    @Override
    public String toString() {
    return id + ". " + title + "";    }
}
public class Homepage extends javax.swing.JFrame {
    private ArrayList<Book> bookList;
    private ArrayList<Book> userBook;
    private BookListCellRenderer cellRenderer;
    private Jlogin loginFrame;
    
    
    public Homepage() {
        initComponents();
        initializeAndSortBooks();
        userBook = new ArrayList<>(); // Initialize userBook ArrayList
        updateUBList(userBook); // Update UBList when initializing
        loginFrame = new Jlogin();
    }
    
        void updateUBList(ArrayList<Book> userBook1) {
        // Update UBList based on userBook ArrayList
        DefaultListModel<String> userListModel = new DefaultListModel<>();
        for (Book book : userBook1) {
            userListModel.addElement(book.toString());
        }
        UBList.setModel(userListModel);
    }
    
     private void initializeAndSortBooks() {
        // Initialize the book list
    bookList = new ArrayList<>();
    bookList.add(new Book(1, "And Then There Were None", "Agatha Christie", "2005-08-14"));
    bookList.add(new Book(2, "I Am Number Four", "Pittacus Lore", "2010-08-03"));
    bookList.add(new Book(3, "The Power of Six", "Pittacus Lore", "2011-08-23"));
    bookList.add(new Book(4, "The Rise of Nine", "Pittacus Lore", "2012-08-21"));
    bookList.add(new Book(5, "The Fall of Five", "Pittacus Lore", "2013-08-27"));
    bookList.add(new Book(6, "The Revenge of Seven", "Pittacus Lore", "2014-08-26"));
    bookList.add(new Book(7, "The Fate of Ten", "Pittacus Lore", "2015-09-01"));
    bookList.add(new Book(8, "United as One", "Pittacus Lore", "2016-06-28"));
    bookList.add(new Book(9, "The Giver", "Lois Lowry", "1993-04-26"));
    bookList.add(new Book(10, "Gathering Blue", "Lois Lowry", "2000-09-25"));
    bookList.add(new Book(11, "Messenger", "Lois Lowry", "2004-04-26"));
    bookList.add(new Book(12, "Son", "Lois Lowry", "2012-10-02"));
    bookList.add(new Book(13, "The 5TH Wave", "Rick Yancey", "2013-05-07"));
    bookList.add(new Book(14, "The Infinite Sea", "Rick Yancey", "2014-09-16"));
    bookList.add(new Book(15, "The Last Star", "Rick Yancey", "2016-05-24"));
    bookList.add(new Book(16, "The Darkest Minds", "Alexandra Bracken", "2012-12-18"));
    bookList.add(new Book(17, "Never Fade", "Alexandra Bracken", "2013-10-15"));
    bookList.add(new Book(18, "In the Afterlight", "Alexandra Bracken", "2014-10-28"));
    bookList.add(new Book(19, "Cinder", "Marissa Meyer", "2012-01-03"));
    bookList.add(new Book(20, "Scarlet", "Marissa Meyer", "2013-02-05"));
    bookList.add(new Book(21, "Cress", "Marissa Meyer", "2014-02-04"));
    bookList.add(new Book(22, "Winter", "Marissa Meyer", "2015-11-10"));
    ArrayList<String> bookTitles = new ArrayList<>();

    // Add book titles to the list
    for (Book book : bookList) {
        bookTitles.add(book.toString());
    }

    // Create a DefaultListModel to update the HBList
    DefaultListModel<String> listModel = new DefaultListModel<>();

    // Add book titles to the list model
    for (String title : bookTitles) {
        listModel.addElement(title);
    }

    // Set the list model to the HBList
    HBList.setModel(listModel);
    cellRenderer = new BookListCellRenderer(bookList);
    HBList.setCellRenderer(cellRenderer);
    HBList.setSelectedIndex(0);
    
    
    // Add ListSelectionListener to HBList
        HBList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) {
                    // Book is selected, get the selected index
                    int selectedIndex = HBList.getSelectedIndex();

                    // Ensure a valid index is selected
                    if (selectedIndex != -1) {
                        // Retrieve the selected book from the bookList
                        Book selectedBook = bookList.get(selectedIndex);

                        // Display book details in a JOptionPane
                        String bookDetails = "Title: " + selectedBook.getTitle() + "\n"
                                + "Author: " + selectedBook.getAuthor() + "\n"
                                + "Release Date: " + selectedBook.getReleaseDate();

                        JOptionPane.showMessageDialog(null, bookDetails, "Book Details", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        UBList = new javax.swing.JList<>();
        rentbook = new javax.swing.JButton();
        returnbook = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        HBList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        signout = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(400, 150));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        UBList.setFont(new java.awt.Font("Lucida Calligraphy", 1, 14)); // NOI18N
        UBList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(UBList);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addContainerGap())
        );

        rentbook.setBackground(new java.awt.Color(0, 0, 204));
        rentbook.setFont(new java.awt.Font("Felix Titling", 1, 10)); // NOI18N
        rentbook.setForeground(new java.awt.Color(255, 255, 255));
        rentbook.setText("Rent book");
        rentbook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentbookActionPerformed(evt);
            }
        });

        returnbook.setBackground(new java.awt.Color(255, 0, 0));
        returnbook.setFont(new java.awt.Font("Felix Titling", 1, 10)); // NOI18N
        returnbook.setForeground(new java.awt.Color(255, 255, 255));
        returnbook.setText("return book");
        returnbook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnbookActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Pristina", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("YOUR LIBRARY");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rentbook)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(returnbook))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1)))
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rentbook)
                    .addComponent(returnbook))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        HBList.setFont(new java.awt.Font("Lucida Calligraphy", 1, 18)); // NOI18N
        HBList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(HBList);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Pristina", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("HOME");

        signout.setBackground(new java.awt.Color(255, 0, 0));
        signout.setFont(new java.awt.Font("Felix Titling", 1, 12)); // NOI18N
        signout.setForeground(new java.awt.Color(255, 255, 255));
        signout.setText("SIGN OUT");
        signout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(signout)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(signout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Felix Titling", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("LIBRARY OF BABEL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rentbookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rentbookActionPerformed
        rentbook rentBookFrame = new rentbook(bookList, userBook, Homepage.this);
        rentBookFrame.setUBList(UBList);
        rentBookFrame.setVisible(true);
    }//GEN-LAST:event_rentbookActionPerformed

    private void returnbookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnbookActionPerformed
        returnbook returnBookFrame = new returnbook(bookList, userBook, Homepage.this);
        returnBookFrame.setUBList(UBList);
        returnBookFrame.setVisible(true);
    }//GEN-LAST:event_returnbookActionPerformed

    private void signoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signoutActionPerformed
    dispose();
    loginFrame.setVisible(true);
    }//GEN-LAST:event_signoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Homepage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> HBList;
    private javax.swing.JList<String> UBList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton rentbook;
    private javax.swing.JButton returnbook;
    private javax.swing.JButton signout;
    // End of variables declaration//GEN-END:variables
}
