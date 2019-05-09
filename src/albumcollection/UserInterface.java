package albumcollection;

import albumcollection.album.Album;
import albumcollection.album.CassetteTape;
import albumcollection.album.CompactDisc;
import albumcollection.album.MusicGenre;
import albumcollection.album.track.Track;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class UserInterface {

    //width and height of the monitor
    private static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    //width and height of the window (JFrame)
    private static int windowsWidth = 800;
    private static int windowsHeight = 600;

    //Components for the layout
    private final JPanel topPanel = new JPanel();
    private final JPanel bottomPanel = new JPanel();
    private final JLabel commandLabel = new JLabel("Please select the command",JLabel.RIGHT);
    private final JComboBox comboBox = new JComboBox();
    //Output area. Set as global to be edit in different methods.
    private final JTextArea outputArea = new JTextArea();
    private final JScrollPane outputScrollPane = new JScrollPane(outputArea);

    public void createAndShowGUI() {

        createTopPanel();
        createBottomPanel();

        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new GridLayout(2,0));
        panelContainer.add(topPanel);
        panelContainer.add(bottomPanel);

        JFrame frame = new JFrame("Album Collection");

        // Save on quit.
        frame.addWindowListener(new MainWindowAdapter());

        //panelContainer.setOpaque(true);
        frame.setBounds((width - windowsWidth) / 2, (height - windowsHeight) / 2, windowsWidth, windowsHeight);
        frame.setContentPane(panelContainer);

        frame.setVisible(true);
    }

    private void createTopPanel() {

        comboBox.addItem("Please select...");
        comboBox.addItem(" 1. Display list of all albums.");
        comboBox.addItem(" 2. Add a new album to collection.");
        comboBox.addItem(" 3. Search for an album.");
        comboBox.addItem(" 4. Delete an album from collection.");
        comboBox.addItem(" 5. Exit.");
        comboBox.setSelectedIndex(0);

        comboBox.addItemListener(new MainComboBoxListener());

        GridLayout gl = new GridLayout(0,2,10,10);

        topPanel.setLayout(gl);

        resetTopPanel();
    }

    private void createBottomPanel() {

        outputArea.setText("Album Collection.\n\n");
        outputArea.setEditable(false);

        outputScrollPane.createVerticalScrollBar();
        outputScrollPane.createHorizontalScrollBar();

        bottomPanel.setLayout(new GridLayout(1,0));
        bottomPanel.add(outputScrollPane);
    }

    private void resetTopPanel() {

        topPanel.removeAll();

        topPanel.add(commandLabel);
        topPanel.add(comboBox);
        //Keep the comboBox at the first line.
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());

        topPanel.updateUI();
    }

    private void displayAlbums(ArrayList<Album> albums, boolean numbered) {

        topPanel.removeAll();

        topPanel.add(commandLabel);
        topPanel.add(comboBox);
        //Keep the comboBox at the first line.
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());

        topPanel.updateUI();

        for (int i = 0; i < albums.size(); i++) {

            String numberingString;

            if (numbered) {
                numberingString = "   " + Integer.toString(i + 1) + ". ";
            } else {
                numberingString = "";
            }

            Album album = albums.get(i);
            outputArea.append(numberingString + album.toString() + "\n");
        }

        if (albums.size() > 0) {
            outputArea.append("\n");
        } else {
            outputArea.append("There are no albums in the collection.\n\n");
        }

        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    private void addNewAlbumToCollection() {

        topPanel.removeAll();

        topPanel.add(commandLabel);
        topPanel.add(comboBox);

        JLabel albumTypeLabel = new JLabel("Album Type", JLabel.RIGHT);
        topPanel.add(albumTypeLabel);

        JComboBox albumTypeComboBox = new JComboBox();
        albumTypeComboBox.addItem("Compact Disc");
        albumTypeComboBox.addItem("Cassette Tape");
        topPanel.add(albumTypeComboBox);

        JLabel artistNameLabel = new JLabel("Artist", JLabel.RIGHT);
        topPanel.add(artistNameLabel);

        JTextField artistNameInputField = new JTextField("");
        topPanel.add(artistNameInputField);

        JLabel albumTitleLabel = new JLabel("Album Title", JLabel.RIGHT);
        topPanel.add(albumTitleLabel);

        JTextField albumTitleInputField = new JTextField("");
        topPanel.add(albumTitleInputField);

        JLabel albumReleaseYearLabel = new JLabel("Release Year", JLabel.RIGHT);
        topPanel.add(albumReleaseYearLabel);

        JComboBox albumReleaseYearComboBox = new JComboBox();
        for (int i = 2020; i > 1900; i--) {
            albumReleaseYearComboBox.addItem(Integer.toString(i));
        }
        topPanel.add(albumReleaseYearComboBox);

        JLabel albumGenreLabel = new JLabel("Album Genre", JLabel.RIGHT);
        topPanel.add(albumGenreLabel);

        JComboBox albumGenreComboBox = new JComboBox();
        albumGenreComboBox.addItem(MusicGenre.ROCK.toString());
        albumGenreComboBox.addItem(MusicGenre.RAP.toString());
        albumGenreComboBox.addItem(MusicGenre.COUNTRY.toString());
        albumGenreComboBox.addItem(MusicGenre.POP.toString());
        topPanel.add(albumGenreComboBox);

        JLabel cassetteLengthLabel = new JLabel("Length of Album in Seconds", JLabel.RIGHT);
        topPanel.add(cassetteLengthLabel);
        cassetteLengthLabel.setVisible(false);

        JTextField cassetteLenghtInputField = new JTextField("");
        topPanel.add(cassetteLenghtInputField);
        cassetteLenghtInputField.setVisible(false);

        topPanel.add(new JLabel());
        topPanel.add(new JLabel());
        topPanel.add(new JLabel());

        JButton confirmButton = new JButton("Confirm");
        topPanel.add(confirmButton);

        albumTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    if (e.getItem().equals("Compact Disc")) {
                        cassetteLengthLabel.setVisible(false);
                        cassetteLenghtInputField.setVisible(false);
                    } else if (e.getItem().equals("Cassette Tape")) {
                        cassetteLengthLabel.setVisible(true);
                        cassetteLenghtInputField.setVisible(true);
                    }
                }
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Album album;

                String artistName = artistNameInputField.getText();
                String title = albumTitleInputField.getText();
                int releaseYear = Integer.parseInt((String)albumReleaseYearComboBox.getSelectedItem());
                MusicGenre genre = MusicGenre.fromString((String)albumGenreComboBox.getSelectedItem());

                if (albumTypeComboBox.getSelectedItem() == "Compact Disc") {
                    album = new CompactDisc(title, artistName, releaseYear, genre);
                } else {
                    double cassetteLengthInSeconds = Double.parseDouble(cassetteLenghtInputField.getText());
                    album = new CassetteTape(title, artistName, releaseYear, genre, cassetteLengthInSeconds);
                }

                AlbumCollectionManager.addAlbumToCollection(album);

                outputArea.append(title + " by " + artistName + " added to collection.\n\n");
                outputArea.setCaretPosition(outputArea.getDocument().getLength());
            }
        });

        topPanel.updateUI();
    }
    /*
        This class removes a specified album from the collection that the user chooses
     */
    private void removeAlbumFromCollection() {
        //
    }

    private void searchForAlbum() {
        //
    }

    class MainWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            AlbumCollectionManager.saveAlbumCollectionToDatabase();
        }
    }

    class MainComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == 1) {
                if (e.getItem().equals("Please select...")) {
                    outputArea.append("Please select a command to continue.\n\n");
                    resetTopPanel();
                } else if (e.getItem().equals(" 1. Display list of all albums.")) {
                    displayAlbums(AlbumCollectionManager.getAlbumCollection(), false);
                } else if (e.getItem().equals(" 2. Add a new album to collection.")) {
                    addNewAlbumToCollection();
                } else if (e.getItem().equals(" 3. Search for an album.")) {
                    searchForAlbum();
                } else if (e.getItem().equals(" 4. Delete an album from collection.")) {
                    removeAlbumFromCollection();
                } else if (e.getItem().equals(" 5. Exit.")) {
                    AlbumCollectionManager.saveAlbumCollectionToDatabase();
                }
            }
        }
    }
}
