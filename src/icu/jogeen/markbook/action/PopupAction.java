package icu.jogeen.markbook.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import icu.jogeen.markbook.data.DataCenter;
import icu.jogeen.markbook.dialog.NoteDialog;

public class PopupAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        SelectionModel selectionModel = editor.getSelectionModel();
        String selectedText = selectionModel.getSelectedText();
        DataCenter.SELECT_TEXT = selectedText;
        String name = e.getRequiredData(CommonDataKeys.PSI_FILE).getViewProvider().getVirtualFile().getName();
        DataCenter.FILE_NAME = name;
/*        AddNoteDialog addNoteDialog = new AddNoteDialog();*/
        NoteDialog dialog = new NoteDialog();
        dialog.setVisible(true);
    }
}
