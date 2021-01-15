package icu.jogeen.markbook.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.ui.EditorTextField;
import icu.jogeen.markbook.data.DataCenter;
import icu.jogeen.markbook.data.DataConvert;
import icu.jogeen.markbook.data.NoteData;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class AddNoteDialog extends DialogWrapper {
    private EditorTextField tfTitle;
    private EditorTextField tfMark;

    public AddNoteDialog() {
        super(true);
        setTitle("添加笔记注释");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        tfTitle = new EditorTextField("笔记标题");
        tfMark = new EditorTextField("笔记内容");
        tfMark.setPreferredSize(new Dimension(200, 100));
        panel.add(tfTitle, BorderLayout.NORTH);
        panel.add(tfMark, BorderLayout.CENTER);
        return panel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton("添加笔记到列表");
        button.addActionListener(e -> {
            String title = tfTitle.getText();
            String mark = tfMark.getText();
            String fileType = DataCenter.FILE_NAME.substring(DataCenter.FILE_NAME.lastIndexOf(".") + 1);
            NoteData noteData = new NoteData(title, mark, DataCenter.SELECT_TEXT, DataCenter.FILE_NAME, fileType);
            DataCenter.NOTE_LIST.add(noteData);
            DataCenter.TABLE_MODEL.addRow(DataConvert.convert(noteData));
            MessageDialogBuilder.yesNo("操作结果","添加成功");
            AddNoteDialog.this.dispose();
        });
        panel.add(button);
        return panel;
    }
}
