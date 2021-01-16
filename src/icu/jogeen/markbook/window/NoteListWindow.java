package icu.jogeen.markbook.window;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import icu.jogeen.markbook.data.DataCenter;
import icu.jogeen.markbook.processor.DefaultSourceNoteData;
import icu.jogeen.markbook.processor.MDFreeMarkProcessor;
import icu.jogeen.markbook.processor.Processor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteListWindow {
    private JTextField tfTopic;
    private JTable tbConent;
    private JButton btnCreate;
    private JButton btnClear;
    private JButton btnClose;
    private JPanel contentPanel;

    private void init() {
        tbConent.setModel(DataCenter.TABLE_MODEL);
        tbConent.setEnabled(true);
    }

    public NoteListWindow(Project project, ToolWindow toolWindow) {
        init();
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String topic = tfTopic.getText();
                String fileName = topic + ".md";
                if (topic == null || "".equals(topic)) {
                    tfTopic.requestFocus();
                    JOptionPane.showMessageDialog(null, "Please add the document title first!", "tips",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
                if (virtualFile != null) {
                    String path = virtualFile.getPath();
                    String fileFullPath = path + "/" + fileName;
                    Processor processor = new MDFreeMarkProcessor();
                    try {
                        processor.process(new DefaultSourceNoteData(fileFullPath, topic, DataCenter.NOTE_LIST));
                        NotificationGroup notificationGroup = new NotificationGroup("markbook_id", NotificationDisplayType.BALLOON, true);
                        Notification notification = notificationGroup.createNotification("Generate Document Success：" + fileFullPath, MessageType.INFO);
                        Notifications.Bus.notify(notification);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataCenter.reset();
            }
        });
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindow.hide(null);
            }
        });
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
