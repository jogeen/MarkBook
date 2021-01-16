package icu.jogeen.markbook.dialog;

import com.intellij.openapi.ui.MessageDialogBuilder;
import icu.jogeen.markbook.data.DataCenter;
import icu.jogeen.markbook.data.DataConvert;
import icu.jogeen.markbook.data.NoteData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NoteDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea taCode;
    private JTextField tfTitle;
    private JTextArea taMark;
    private JLabel lbFileName;

    public NoteDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Mark");
        setLocation(400, 200);//距离屏幕左上角的其实位置
        setSize(1000,800);

        lbFileName.setText(DataCenter.FILE_NAME);
        taCode.setText(DataCenter.SELECT_TEXT);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String title = tfTitle.getText();
        String mark = taMark.getText();
        String fileType = DataCenter.FILE_NAME.substring(DataCenter.FILE_NAME.lastIndexOf(".") + 1);
        NoteData noteData = new NoteData(title, mark, DataCenter.SELECT_TEXT, DataCenter.FILE_NAME, fileType);
        DataCenter.NOTE_LIST.add(noteData);
        DataCenter.TABLE_MODEL.addRow(DataConvert.convert(noteData));
        MessageDialogBuilder.yesNo("Result","Success");
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        NoteDialog dialog = new NoteDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
