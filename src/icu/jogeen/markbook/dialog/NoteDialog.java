package icu.jogeen.markbook.dialog;

import icu.jogeen.markbook.data.DataCenter;
import icu.jogeen.markbook.data.NoteData;

import javax.swing.*;
import java.awt.event.*;

public class NoteDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea taCode;
    private JTextField tfTitle;
    private JTextArea taMark;
    private JLabel lbFileName;
    private boolean update = false;
    private Integer editIndex;

    public NoteDialog() {
        editIndex = DataCenter.NOTE_LIST.size();
        NoteData noteData = new NoteData("title", "note", DataCenter.SELECT_TEXT, DataCenter.FILE_NAME, null);
        init(noteData);
    }

    public NoteDialog(Integer index, NoteData noteData) {
        init(noteData);
        this.editIndex = index;
        update = true;
    }

    private void init(NoteData noteData) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Mark");
        setLocation(400, 200);//距离屏幕左上角的其实位置
        setSize(800, 600);

        lbFileName.setText(noteData.getFileName());
        taCode.setText(noteData.getContent());
        tfTitle.setText(noteData.getTitle());
        taMark.setText(noteData.getMark());

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
        if (!update) {
            addNote(editIndex);
        } else {
            updateNote(editIndex);
        }
    }

    private void updateNote(Integer index) {
        String title = tfTitle.getText();
        String mark = taMark.getText();
        String code = taCode.getText();
        String fileType = DataCenter.FILE_NAME.substring(DataCenter.FILE_NAME.lastIndexOf(".") + 1);
        NoteData noteData = new NoteData(title, mark, code, DataCenter.FILE_NAME, fileType);
        DataCenter.update(index, noteData);
        dispose();
    }

    private void addNote(Integer index) {
        String title = tfTitle.getText();
        String mark = taMark.getText();
        String code = taCode.getText();
        String fileType = DataCenter.FILE_NAME.substring(DataCenter.FILE_NAME.lastIndexOf(".") + 1);
        NoteData noteData = new NoteData(title, mark, code, DataCenter.FILE_NAME, fileType);
        DataCenter.add(noteData);
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
